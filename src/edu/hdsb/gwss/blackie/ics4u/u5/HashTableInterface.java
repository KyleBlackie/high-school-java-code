package edu.hdsb.gwss.blackie.ics4u.u5;

import edu.hdsb.gwss.blackie.ics4u.u4.Book;



/**
 * Lesson: 6.04 - (Closed) Hash Table
 */
public interface HashTableInterface {
    
    public int size();
    
    public int capacity();
    
    public double loadFactor();
    
    public void makeEmpty();
    
    public boolean isEmpty();
    
    public void resize();
    
    public Student get( int key );
    
    public void put( int key, Student value);
    
    public boolean contains( Student value );
    
    public boolean containsKey( int key );
    
    public int hash( int key );

}
