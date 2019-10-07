package edu.hdsb.gwss.blackie.ics4u.u5;

/**
 *
 * @author 1blackiekyl
 */
public class HashTableLP implements HashTableInterface {

    //constants
    public static final int DEFAULT_CAPACITY = 53;
    //variables
    private Student[] students;
    private int capacity;
    private int counter;

    //empty constructor
    public HashTableLP() {
        this.capacity = DEFAULT_CAPACITY;
        this.students = new Student[this.capacity];
        this.counter = 0;
    }

    @Override
    public int size() {
        return this.counter;
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public double loadFactor() {
        return (double) this.size() / (double) this.capacity();
    }

    @Override
    public void makeEmpty() {
        this.students = new Student[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
        this.counter = 0;
    }

    @Override
    public boolean isEmpty() {
        //check if size is zero
        if (this.size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void resize() {
        //increase capacity by 3 times to reduce load to 25%
        this.capacity *= 3;
        //create a temporary copy of the array
        Student[] temp = this.students.clone();
        //set array to new array with new capacity 
        this.students = new Student[this.capacity];
        //reinitialise counter
        this.counter = 0;
        for (Student student : temp) {
            //add all non-null objects to the new student array
            if (student != null) {
                this.put(student.hashCode(), student);
            }
        }

    }

    @Override
    public Student get(int key) {
        //get the index to start at
        int index = hash(key);
        //keep track of that index
        int temp = index;

        //check if the key is at that index
        if (students[index] != null && this.students[index].hashCode() == key) {
            return this.students[index];
        }

        //go to next index
        index = (index + 1) % this.capacity;
        //continue to search for it until either it is found or the index returns to the start
        while (temp != index) {
            //if object is not null and has the same key, return said object
            if (students[index] != null && students[index].hashCode() == key) {
                return this.students[index];
            }
            index = (index + 1) % this.capacity;
        }
        //key is not in hash table
        System.out.println("Key Not Found");
        return null;
    }

    @Override
    public void put(int key, Student value) {
        if (this.contains(value)) {
            System.out.println("Error Duplicate Values Are Not Allowed");
        } else {
            //keep track of collisions
            int collisions = 0;
            //get index of key
            int index = hash(key);

            boolean placed = false;
            while (!placed) {
                //find the closest index that doesn't hold any objects
                if (this.students[index] == null) {
                    this.students[index] = value;
                    placed = true;
                } else {
                    //collision, linear probe to next index
                    collisions++;
                    index = (index + 1) % this.capacity();
                }

            }
            //show collisions
            System.out.println("collisions: " + collisions);
            //increase size
            this.counter++;
            //check if a resize is necessary
            if (this.loadFactor() > 0.75) {
                this.resize();
            }
        }

    }

    @Override
    public boolean contains(Student value) {
        //boolean variable to be returned
        boolean found = false;
        //check if the object is null
        if (value == null) {
            System.out.println("Error Object Is Null");
        } else {
            //get the index based off of the objects hash code
            int index = hash(value.hashCode());
            //keep track of the starting index
            int temp = index;
            //check if the object is at this starting index
            if (students[index] != null && students[index].equals(value)) {
                found = true;
            }
            //go to next index
            index = (index + 1) % this.capacity;

            //while the object is not found or a complete search of the array has not occurred, search
            while (!found && temp != index) {
                if (students[index] != null && students[index].equals(value)) {
                    found = true;
                }
                index = (index + 1) % this.capacity;
            }
        }
        return found;
    }

    @Override
    public boolean containsKey(int key) {

        //variables
        boolean found = false;
        int index = hash(key);
        int temp = index;

        //check if start index contains correct key
        if (this.students[index].hashCode() == key) {
            found = true;
        }

        //go to next index
        index = (index + 1) % this.capacity;

        //search for key until it is found or index returns to starting point
        while (!found && temp != index) {
            if (students[index] != null && students[index].hashCode() == key) {
                found = true;
            }
            index = (index + 1) % this.capacity;
        }
        return found;
    }

    @Override
    public int hash(int key) {
        //generate hash key based off of the capacity
        return key % this.capacity;
    }

}
