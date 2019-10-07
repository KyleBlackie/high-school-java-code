/*
 * Name: Kyle Blackie
 * Date: February 25 ,2017
 * Description: Find resistor values
 */
package edu.hdsb.gwss.blackie.ics4u.u2;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author kyle
 */
public class Resistors {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //objects
        Scanner input = new Scanner(System.in);
        StringTokenizer st;
        String colours;
        String first;
        String second;
        String multiplier;
        //variables
        double value;

        //input
        colours = input.nextLine();
        colours = colours.toLowerCase();
        st = new StringTokenizer(colours, "-");
        //get colours
        first = st.nextToken();
        second = st.nextToken();
        multiplier = st.nextToken();
        //get values
        value = colourCode(first) * 10;
        value += colourCode(second);
        value *= Math.pow(10,colourCode(multiplier));
        //output
        System.out.println("You Entered " + colours);
        System.out.print("The value of the resistor is ");
        if(value > 1000){
            value /= 1000;
            System.out.println((int)value + "K ohms.");
        }else System.out.println(value + " ohms.");
        
    }

    public static int colourCode(String colour) {
        int code = -1;
        switch (colour) {
            case "black":
                code = 0;
                break;
            case "brown":
                code = 1;
                break;
            case "red":
                code = 2;
                break;
            case "white":
                code = 9;
                break;
            case "orange":
                code = 3;
                break;
            case "yellow":
                code = 4;
                break;
            case "green":
                code = 5;
                break;
            case "blue":
                code = 6;
                break;
            case "violet":
                code = 7;
                break;
            case "grey":
                code = 8;
                break;

        }
        return code;
    }
}

