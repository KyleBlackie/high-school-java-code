/*
 * Name:    Kyle Blackie
 * Date:    May 5, 2017
 * Version: 1.0
 * Description: Queue class
 */
package edu.hdsb.gwss.blackie.ics4u.u5;

/**
 *
 * @author kyle
 */
public class Queue implements QueueInterface {

    //class variables
    public static final int DEFAULT_SIZE = 20;

    //object variables
    private int[] data;
    private int front;
    private int back;
    private int counter;

    //constructors 
    public Queue() {
        //if a size is not specified, use the default size
        this(DEFAULT_SIZE);
    }

    public Queue(int capacity) {
        //create a new int array with the given capacity
        data = new int[capacity];
        //init variables
        front = -1;
        back = -1;
        counter = 0;
    }

    // b = (b + 1) % capacity
     


    //show the values stored in the Queue 
    public void displayQueue() {
        for (int i = 0; i < capacity(); i++) {
            System.out.format("%4s", data[i]);
        }
        System.out.println("");
        for (int i = 0; i < capacity(); i++) {
            //format the output
            if (front == i && back == i) {
                System.out.format("%4s\n", "F");
                for (int j = i; j >= 1; j--) {
                    System.out.format("%4s", "");
                }
                System.out.format("%4s", "B");
            } else if (front == i) {
                System.out.format("%4s", "F");
            } else if (back == i) {
                System.out.format("%4s", "B");
            } else {
                System.out.format("%4s", "");
            }
        }
        System.out.println("");
    }

    //getters for testing
    public int getFront() {
        return front;
    }

    public int getBack() {
        return back;
    }

    //return the value at the front of the queue
    @Override
    public int front() {
        if (front < 0 || front > data.length) {
            return -1;
        } else {
            return data[front];
        }
    }

    //return the value at the back of the queue
    @Override
    public int back() {
        if (back < 0 || back > data.length) {
            return -1;
        } else {
            return data[back];
        }
    }

    //add a value to the back of the queue
    @Override
    public void enqueue(int value) {
        if (isFull()) {
            System.out.println("Error Queue Is Already Full");
        } else {
            if (isEmpty()) {
                front = 0;
                back = 0;
            } else {
                back = (back + 1) % capacity();
            }
            data[back] = value;
            counter++;
        }
    }

    //remove a value from the front of the queue
    @Override
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Error Queue is Empty");
            return -1;
        } else {
            counter--;
            int deq = data[front];
            data[front] = 0;
            if (front == back) {
                front = -1;
                back = -1;
            } else {
                front = (front + 1) % capacity();
            }
            return deq;
        }
    }

    //return the size/value of the counter
    @Override
    public int size() {
        return counter;
    }

    @Override
    public int capacity() {
        return data.length;
    }

    @Override
    public boolean isEmpty() {
        if (counter == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isFull() {
        if (counter == capacity()) {
            return true;
        }
        return false;
    }

    @Override
    public void makeEmpty() {
        //loop through array setting all values to -1
        for (int i = 0; i < data.length; i++) {
            data[i] = -1;
        }
        counter = 0;
    }

}
