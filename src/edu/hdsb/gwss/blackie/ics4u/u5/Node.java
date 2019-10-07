/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hdsb.gwss.blackie.ics4u.u5;

/**
 *
 * @author kyle
 */
public class Node implements NodeInterface {

    //class values 
    private Node next;
    private String value;

    //constructor
    public Node() {

    }

    public Node(String value, Node next) {
        this.value = value;
        this.next = next;
    }

    public Node getNext() {
        return next;
    }

    public String getValue() {
        return value;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setValue(String value) {
        this.value = value;
    }

    
}