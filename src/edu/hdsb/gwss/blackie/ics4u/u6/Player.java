/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hdsb.gwss.blackie.ics4u.u6;

/**
 *
 * @author kyle
 */
public class Player {
    
    private String name;
    private String team;
    private int age;
    
    
    public Player(){
        
    }
    
    
    public Player(String name, String team, int age){
        this.name = name;
        this.team = team;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", team=" + team + ", age=" + age + '}';
    }
    
    
    
    
    
    
}
