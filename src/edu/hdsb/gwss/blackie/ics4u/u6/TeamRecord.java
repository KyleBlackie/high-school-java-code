package edu.hdsb.gwss.blackie.ics4u.u6;

import java.util.Objects;

/**
 *
 * @author 1blackiekyl
 */
public class TeamRecord {

    //constants
    public static final int RECORD_SIZE = 110;
    public static final int NAME_LENGTH = 20;
    public static final int SPORT_LENGTH = 10;
    public static final int LOCATION_LENGTH = 15;

    //constants for gender choices
    public static final char MALE = 'm';
    public static final char FEMALE = 'f';

    /*
     one char == two bytes
     one int == four bytes
     one double == eight bytes
     one boolean == one byte
     */
    //one boolean to see if file is deleted
    private long id;
    private String name;
    private String sport;
    private String location;
    private char gender;
    private double winRatio;
    private boolean titleDefenders;
    private int titlesWon;
    private int players;

    public TeamRecord(long id) {
        this.id = id;
    }

    public TeamRecord(String name, String sport, String location, char gender, double winRatio, boolean titleDefenders, int titlesWon, int players) {
        this.setName(name);
        this.setSport(sport);
        this.setLocation(location);
        this.setGender(gender);
        this.setWinRatio(winRatio);
        this.setTitleDefenders(titleDefenders);
        this.setTitlesWon(titlesWon);
        this.setPlayers(players);
        this.id = -1;
    }

    public void setName(String name) {
        StringBuilder temp = new StringBuilder();

        if (name != null) {
            temp.append(name.trim());
        } else {
            temp.append("TBD");
        }

        // trucates or pads the string
        temp.setLength(NAME_LENGTH);
        this.name = temp.toString();
    }

    public void setSport(String sport) {
        StringBuilder temp = new StringBuilder();

        if (sport != null) {
            temp.append(sport.trim());
        } else {
            temp.append("TBD");
        }

        // trucates or pads the string
        temp.setLength(SPORT_LENGTH);
        this.sport = temp.toString();
    }

    public void setLocation(String location) {
        StringBuilder temp = new StringBuilder();

        if (location != null) {
            temp.append(location.trim());
        } else {
            temp.append("TBD");
        }

        // trucates or pads the string
        temp.setLength(LOCATION_LENGTH);
        this.location = temp.toString();
    }

    public void setGender(char gender) {
        if (gender != TeamRecord.MALE && gender != TeamRecord.FEMALE) {
            System.out.println("Invalid Gender");
        } else {
            this.gender = gender;
        }
    }

    public void setPlayers(int players) {
        if (players < 0) {
            System.out.println("Too Few Players");
        } else if (players > 25) {
            System.out.println("Too Many Players");
        } else {
            this.players = players;
        }
    }

    public void setWinRatio(double winRatio) {
        if (winRatio < 0) {
            System.out.println("Win Ratio Can Not Be Less Than Zero");
        } else if (winRatio > 1) {
            System.out.println("Win Ratio Can Not Be Greater Than One");
        } else {
            this.winRatio = winRatio;
        }
    }

    public void setTitleDefenders(boolean titleDefenders) {
        this.titleDefenders = titleDefenders;
    }

    public void setTitlesWon(int titlesWon) {
        if (titlesWon < 0) {
            System.out.println("Titles Won Can Not Be Less Than Zero");
        } else {
            this.titlesWon = titlesWon;
        }
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSport() {
        return sport;
    }

    public long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public char getGender() {
        return gender;
    }

    public double getWinRatio() {
        return winRatio;
    }

    public boolean isTitleDefenders() {
        return titleDefenders;
    }

    public int getTitlesWon() {
        return titlesWon;
    }

    public int getPlayers() {
        return players;
    }

    public long getID() {
        return id;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TeamRecord other = (TeamRecord) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.sport, other.sport)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Team Record: " + "The Team's Identification Is: " + this.id + ", The Team Name Is: " + this.name.trim() + ", sport = " + this.sport.trim() + ", The Team Is Located At: " + this.location.trim() + ", The Gender of The Players Is: " + this.gender + ", The Team's Win Ratio Is: " + this.winRatio + ", The Team Is The Title Defender" + this.titleDefenders + ", The Team Has Won " + this.titlesWon + "Titles" + ", There Are " + this.players + "Players On The Team.";
    }

}
