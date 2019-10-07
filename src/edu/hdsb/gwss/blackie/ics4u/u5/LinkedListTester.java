/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hdsb.gwss.blackie.ics4u.u5;

/**
 *
 * @author 1blackiekyl
 */
public class LinkedListTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LinkedList l = new LinkedList();

        //TEST 1
        assert (l.size() == 0);
        assert (l.head() == null);
        assert (l.tail() == null);
        assert (l.isEmpty());
        assert (l.removeHead() == null);
        assert (l.removeTail() == null);
        l.display();

        //TEST 2
        for (int i = 0; i < 10; i++) {
            l.addAtFront("" + i);
            assert (!l.isEmpty());
            assert (l.head().equals("" + i));
            assert (l.tail().equals("" + 0));
            assert (l.size() == i + 1);
            l.display();
        }

        //TEST 3
        for (int i = l.size(); i > 0; i--) {
            l.removeHead();
            if (i == 1) {
                assert (l.isEmpty());
            } else {
                assert (!l.isEmpty());
            }
            assert (l.size() == i - 1);
            l.display();
        }

        //TEST 4
        for (int i = 0; i < 10; i++) {
            l.addAtEnd("" + i);
            assert (!l.isEmpty());
            assert (l.tail().equals("" + i));
            assert (l.head().equals("" + 0));
            assert (l.size() == i + 1);
            l.display();
        }

        //TEST 5
        for (int i = l.size(); i > 0; i--) {
            l.removeTail();
            if (i == 1) {
                assert (l.isEmpty());
            } else {
                assert (!l.isEmpty());
            }
            assert (l.size() == i - 1);
            l.display();
        }
        
        
        //TEST 6
        for (int i = 0; i < 10; i++) {
            l.addAtFront("" + i);
            l.addAtFront("" + i);
            l.removeHead();
            assert (!l.isEmpty());
            assert (l.head().equals("" + i));
            assert (l.tail().equals("" + 0));
            assert (l.size() == i + 1);
            l.display();
        }
        
    }

}
