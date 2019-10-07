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
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LinkedList l = new LinkedList();
        l.addAtEnd("a");
        l.addAtEnd("b");
        l.addAtEnd("c");

        l.remove("b");
        l.remove("c");

        Queue q = new Queue();
        q.enqueue(0);
        q.enqueue(0);
        q.enqueue(0);
        q.enqueue(0);
        q.enqueue(0);
        q.makeEmpty();
        System.out.println(q.size());

        HashTableLP h = new HashTableLP();

        h.put(0, new Student(123));
        h.put(0, new Student(222));
        h.put(0, new Student(333));

        System.out.println(h.capacity());
        h.resize();
        System.out.println(h.capacity());

        
    }

}
