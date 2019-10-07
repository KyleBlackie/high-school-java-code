/*
 * Name:    Kyle Blackie
 * Date:    May 3, 2017
 * Version: 1.0
 * Description: Stack class
 */
package edu.hdsb.gwss.blackie.ics4u.u5;

/**
 *
 * @author 1blackiekyl
 */
public class Stack implements StackInterface {
    //constants
    public static final int DEFAULT_SIZE = 20;
    
    //variables
    private int[] data;
    private int top;
    
    //empty constructor
    public Stack() {
        this(DEFAULT_SIZE);
    }
    
    public Stack(int capacity) {
        this.top = -1;
        this.data = new int[capacity];
    }
    
    //display the contents of the array
    public void showValues(){
        for(int i = 0; i < data.length; i++){
            System.out.println(data[i]);
        }
    }
    
    @Override
    public boolean isFull() {
        if(top + 1 >= data.length ){
            return true;
        }
        return false;
    }

    @Override
    public void push(int n) {
        //check if full before trying to push
        if (!this.isFull()) {
            this.top++;
            this.data[top] = n;
        }
    }

    @Override
    public int top() {
        if(top < 0 || top > data.length){
            return -1;
        }
        return this.data[top];
    }

    @Override
    public int size() { 
        return top + 1;
    }

    @Override
    public int capacity() {
        return data.length;
    }

    @Override
    public boolean isEmpty() {
        if(top < 0){
            return true;
        }
        return false;
    }

    @Override
    public void makeEmpty() {
        for (int i = 0; i < top + 1; i++) {
            data[i] = 0;
        }
        top = -1;        
    }

    @Override
    public int pop() {
        if(top < 0 || top > data.length){
            return -1;
        }
        //shift around values
        int ret = this.data[top];
        this.data[top] = 0;
        this.top--;
        return ret;
    }

}
