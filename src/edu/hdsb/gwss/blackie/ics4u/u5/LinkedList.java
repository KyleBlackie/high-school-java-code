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
public class LinkedList implements LinkListInterface {
    
    //nodes
    private Node head;
    private Node tail;
    
    //empty constructor
    public LinkedList() {

    }
    
    //show the contents of the linked list
    public void display() {
        if (head == null) {
            System.out.println("null");
        } else if (size() == 1) {
            System.out.println(head.getValue());
        } else {
            Node n = head;
            while (n.getNext() != null) {
                System.out.print(n.getValue());
                System.out.print("-->");
                n = n.getNext();
            }
            System.out.println(n.getValue());
        }
    }

    @Override
    public int size() {
        if (isEmpty()) {
            return 0;
        }
        Node n = head;
        int counter = 1;
        //loop through list while keeping count
        while (n.getNext() != null) {
            counter++;
            n = n.getNext();
        }
        return counter;
    }

    @Override
    public void makeEmpty() {
        this.head = null;
        this.tail = null;
    }

    @Override
    public boolean isEmpty() {
        //if there is no head and no tail, the list is empty
        if (this.head == null && this.tail == null) {
            return true;
        }
        return false;
    }

    @Override
    public void addAtFront(String str) {
        //create and add a node at the head
        Node n = new Node(str, this.head);
        if (size() == 0) {
            this.tail = n;
        }
        this.head = n;
    }

    @Override
    public void addAtEnd(String str) {
        //create and add a node at the tail
        Node n = new Node(str, null);
        if (isEmpty()) {
            this.head = n;
        } else {
            this.tail.setNext(n);
        }
        this.tail = n;
    }

    @Override
    public void remove(String str) {
        Node n = head;
        //check if there is nothing in the list to remove
        if (isEmpty()) {
            System.out.println("List is empty");
            //special case if the size is one
        } else if (size() == 1) {
            if (n.getValue().equals(str)) {
                makeEmpty();
            }
        } else {
            //loop through list looking for value
            while (n.getNext() != null) {
                if (n.getNext().getValue().equals(str)) {
                    n.setNext(n.getNext());
                    n.getNext().setNext(null);
                }
                n = n.getNext();
            }
        }
    }

    @Override
    public String removeHead() {
        //Check if the list is empty
        if (isEmpty()) {
            System.out.println("List is empty");
            return null;
        }
        String value;
        //special case size is one
        if (size() == 1) {
            value = head.getValue();
            head = null;
            tail = null;
            return value;
        }
        Node toDelete = head;
        head = head.getNext();
        toDelete.setNext(null);
        return toDelete.getValue();
    }

    @Override
    public String removeTail() {
        //Check if the list is empty
        if (isEmpty()) {
            System.out.println("List is empty");
            return null;
        }
        String value;
        //special case size is one
        if (size() == 1) {
            value = tail.getValue();
            tail = null;
            head = null;
            return value;
        }
        Node n = head;
        while (n.getNext().getNext() != null) {
            n = n.getNext();
        }
        this.tail = n;
        value = n.getNext().getValue();
        n.setNext(null);
        return value;
    }

    @Override
    public String head() {
        if (head == null) {
            return null;
        }
        return head.getValue();
    }

    @Override
    public String tail() {
        if (tail == null) {
            return null;
        }
        return tail.getValue();
    }

}
