package edu.hdsb.gwss.blackie.ics4u.u5;

/**
 * Lesson: 6.01 - Stacks
 */
public interface StackInterface {

    public int top();

    public int pop();

    public void push( int value );

    public int size();
    
    public int capacity();

    public boolean isEmpty();
    
    public boolean isFull();

    public void makeEmpty();

}
