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
public class Student {
    
    private long studentNumber;
    private String name;
    private int age;
    private double average;
    
    public Student(){
        this.age = -1;
        this.average = -1;
    }
    
    public Student(long studentNumber){
        this.studentNumber = studentNumber;
    }
    
    public Student(long studentNumber, String name, int age, double average){
        this.studentNumber = studentNumber;
        this.name = name;
        this.age = age;
        this.average = average;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (int) (this.studentNumber ^ (this.studentNumber >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (this.studentNumber != other.studentNumber) {
            return false;
        }
        return true;
    }

 

         
    
}
