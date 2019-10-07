package edu.hdsb.gwss.blackie.ics4u.u6;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1blackiekyl
 */
public class Database {

    private RandomAccessFile raf;

    //constructor
    public Database() {
        this.open();
    }

    public void open() {
        try {
            //create random access file
            raf = new RandomAccessFile("team_info.dat", "rw");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param team a valid TeamRecord Object
     */
    public void save(TeamRecord team) {

        try {
            //check if team has an id
            if (team.getID() == -1) {
                //start at end
                this.raf.seek(this.raf.length());
            } else {
                //write overtop of team id
                this.raf.seek(team.getID() * TeamRecord.RECORD_SIZE);
            }

            //WRITE DATA
            raf.writeBoolean(false); // 1
            raf.writeChars(team.getName()); //40
            raf.writeChars(team.getSport()); //20
            raf.writeChars(team.getLocation()); //30 
            raf.writeChar(team.getGender()); //2
            raf.writeDouble(team.getWinRatio()); //8
            raf.writeBoolean(team.isTitleDefenders()); // 1
            raf.writeInt(team.getTitlesWon()); // 4
            raf.writeInt(team.getPlayers()); // 4

            //110
            //set the id if it does not have one
            if (team.getId() == -1) {
                team.setId((raf.length() / TeamRecord.RECORD_SIZE) - 1);
            }

            //display new size of random access file
            int location = 0;
            int size = 0;
            while (location * TeamRecord.RECORD_SIZE < raf.length()) {
                raf.seek(location * TeamRecord.RECORD_SIZE);
                if (!raf.readBoolean()) {
                    size += TeamRecord.RECORD_SIZE;
                }
                location++;
            }

            System.out.println("SIZE: " + size);

        } catch (IOException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //close
    /**
     * close the random access file
     */
    public void close() {
        try {
            //close the random access file
            this.raf.close();
        } catch (IOException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //get
    /**
     *
     * @param id identification of object
     * @return
     */
    public TeamRecord get(long id) {
        //variable to return
        TeamRecord result = null;

        try {
            //check for a valid id
            if (id < 0 || id > this.raf.length() / TeamRecord.RECORD_SIZE) {
                System.out.println("Error: Index Out Of Bounds");
                result = null;
            } else {

                // find the location of file in the raf
                this.raf.seek(id * TeamRecord.RECORD_SIZE);

                //check if it has been deleted
                if (this.raf.readBoolean()) {
                    System.out.println("Error: Data At Index " + id + " Has Been Deleted.");
                } else {

                    //read from it and return a TeamRecord
                    result = new TeamRecord(id);

                    //read name
                    char[] name = new char[TeamRecord.NAME_LENGTH];
                    for (int i = 0; i < TeamRecord.NAME_LENGTH; i++) {
                        name[i] = raf.readChar();
                    }

                    result.setName(new String(name));

                    //read sport 
                    char[] sport = new char[TeamRecord.SPORT_LENGTH];
                    for (int i = 0; i < TeamRecord.SPORT_LENGTH; i++) {
                        sport[i] = raf.readChar();
                    }

                    result.setSport(new String(sport));

                    //read location 
                    char[] location = new char[TeamRecord.LOCATION_LENGTH];
                    for (int i = 0; i < TeamRecord.LOCATION_LENGTH; i++) {
                        location[i] = raf.readChar();
                    }

                    result.setLocation(new String(location));

                    //read gender
                    result.setGender(raf.readChar());

                    //read win ratio
                    result.setWinRatio(raf.readDouble());

                    //read title defenders 
                    result.setTitleDefenders(raf.readBoolean());

                    //read titles won
                    result.setTitlesWon(raf.readInt());

                    //read players
                    result.setPlayers(raf.readInt());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    //delete
    /**
     *
     * @param id identification of object
     * @return
     */
    public TeamRecord delete(long id) {
        TeamRecord result = null;

        try {
            //set a boolean at start of container to false to rep a deleted file

            //check if id is valid
            if (id < 0 || id > this.raf.length() / TeamRecord.RECORD_SIZE) {
                System.out.println("Error: Index Out Of Bounds");
            } else {
                //get the TeamRecord at that id and set the boolean for deleted to true
                result = this.get(id);
                this.raf.seek(id * TeamRecord.RECORD_SIZE);
                this.raf.writeBoolean(true);
            }

            //display new size of random access file
            int location = 0;
            int size = 0;
            while (location * TeamRecord.RECORD_SIZE < raf.length()) {
                raf.seek(location * TeamRecord.RECORD_SIZE);
                if (!raf.readBoolean()) {
                    size += TeamRecord.RECORD_SIZE;
                }
                location++;
            }

            System.out.println("SIZE: " + size);
            
        } catch (IOException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    /**
     *
     * @return get the size/number of non-deleted objects in the database
     */
    public int size() {
        try {
            //keep track of location
            int location = 0;
            //count the records
            int counter = 0;
            //loop through records in raf
            while (location * TeamRecord.RECORD_SIZE < this.raf.length()) {
                this.raf.seek(location * TeamRecord.RECORD_SIZE);
                //if not deleted, add one to counter
                if (!this.raf.readBoolean()) {
                    counter++;
                }
                location++;
            }
            return counter;
        } catch (IOException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        //otherwise return -1
        return -1;
    }

}
