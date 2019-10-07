package edu.hdsb.gwss.blackie.ics4u.u6;

import java.io.File;

/**
 * @author 1blackiekyl
 */
public class DataBaseTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //DELETE FILE
        File file = new File("team_info.dat");
        file.delete();

        Database d = new Database();

        TeamRecord t1 = new TeamRecord("Toronto Raptors", "Basketball", "Toronto", TeamRecord.MALE, 0.6, false, 0, 20);
        TeamRecord t2 = new TeamRecord("Leicester City", "Soccer", "Leicester", TeamRecord.MALE, 0.7, true, 1, 22);
        TeamRecord t3 = new TeamRecord("Canadian Women's", "Soccer", "Canada", TeamRecord.FEMALE, 0.65, false, 0, 22);
        TeamRecord t4 = new TeamRecord("Toronto Argonauts", "Football", "Toronto", TeamRecord.MALE, 0.6, false, 3, 20);
        
        //TEST 1 - Saving Records
        assert (d.size() == 0);
        d.save(t1);
        assert (d.size() == 1);
        d.save(t2);
        assert (d.size() == 2);
        d.save(t3);
        assert (d.size() == 3);
        d.save(t4);
        assert (d.size() == 4);

        t1.setName("Raptors");
        //TEST 2 SAVING ONTOP OF A PREEXISTING RECORD
        d.save(t1);
        System.out.println(d.get(0).getName());

        //TEST GET OBJECT
        TeamRecord t5 = d.get(0);

        assert (t1.equals(t5));

        assert (d.get(0).getName().equals(t1.getName()));
        assert (d.get(1).getName().equals(t2.getName()));
        assert (d.get(2).getName().equals(t3.getName()));
        assert (d.get(3).getName().equals(t4.getName()));

        assert (d.size() == 4);

        //TEST DELETING OBJECTS
        d.delete(0);
        assert (d.get(0) == null);
        assert (d.delete(0) == null);
        assert (d.size() == 3);
        d.delete(1);
        assert (d.get(1) == null);
        assert (d.delete(1) == null);
        assert (d.size() == 2);
        d.delete(2);
        assert (d.get(2) == null);
        assert (d.delete(2) == null);
        assert (d.size() == 1);
        d.delete(3);
        assert (d.get(3) == null);
        assert (d.delete(3) == null);
        assert (d.size() == 0);

        //TEST SAVING OBJECTS
        d.save(t1);
        assert (d.size() == 1);
        d.save(t2);
        assert (d.size() == 2);
        d.save(t3);
        assert (d.size() == 3);
        d.save(t4);
        assert (d.size() == 4);

        //TEST UPDATING OBJECTS
        t1.setName("Toronto Raptors");
        d.save(t1);
        System.out.println(d.get(0).getName());
        assert (d.get(0).getName().trim().equals("Toronto Raptors"));

        d.close();

        file.delete();
        
        d.open();
        
        d.save(t1);
        d.save(t2);
        d.delete(1);
        assert (d.size() == 1);
        d.save(t1);
        assert (d.size() == 1);
        t1.setName("TorontoRaptorsBasketBallTeam");
        d.save(t1);
        assert (d.size() == 1);
        d.delete(0);
        assert(d.size() == 0);

        
        d.close();
    }

}
