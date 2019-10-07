package edu.hdsb.gwss.blackie.ics4u.u5;

/**
 * Lesson: 6.02 - Queue
 */
public interface QueueInterface {
    
    public int front();
    
    public int back();
    
    public void enqueue( int value );

    public int dequeue();

    public int size();
    
    public int capacity();

    public boolean isEmpty();
    
    public boolean isFull();

    public void makeEmpty();

}
