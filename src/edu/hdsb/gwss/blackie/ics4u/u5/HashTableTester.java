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
public class HashTableTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        HashTableLP h = new HashTableLP();
        //test empty Hash Table
        assert (h.size() == 0);
        assert (h.capacity() == HashTableLP.DEFAULT_CAPACITY);
        assert (h.isEmpty());
        assert (h.loadFactor() == 0);

        //test adding Student objects 
        for (int i = 0; i < 1000; i++) {
            assert (h.size() == i);
            assert (h.loadFactor() <= 0.8);
            Student s1 = new Student(i);
            h.put(s1.hashCode(), s1);
            assert (h.get(s1.hashCode()).equals(s1));
            assert (h.contains(s1));
            assert (!h.isEmpty());
        }

        h.makeEmpty();
        assert (h.size() == 0);
        assert (h.capacity() == HashTableLP.DEFAULT_CAPACITY);
        assert (h.isEmpty());
        assert (h.loadFactor() == 0);

    }

}
