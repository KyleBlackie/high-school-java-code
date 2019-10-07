/*
 * Name: Kyle Blackie
 * Date: Feb 08 ,2017
 * Description: Calculates when easter is based off of the year given
 */
package edu.hdsb.gwss.blackie.ics4u.u1;

import java.util.Scanner;

/**
 *
 * @author 1blackiekyl
 */
public class EasterComes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //objects
        Scanner input = new Scanner(System.in);
        
        //variables
        int year;
        int month;
        int day;
        //remainder variables
        int a,c,e,h,k,j,p;
        //quotient variables
        int b,d,f,g,i,m;
        
        //take input from user
        System.out.println("What year is it?");
        year = input.nextInt();
        
        //calculations
        a = year % 19;
        b = year / 100;
        c = year % 100;
        d = b / 4;
        e = b % 4;
        f = (b + 8) / 25;
        g = (b - f + 1)/3;
        h = (19 * a + b - d - g + 15) % 30;
        i = c / 4;
        k = c % 4;
        j = (32 + 2 * e + 2 * i - h - k) % 7;
        m = (a + 11 * h + 22 * j) / 451;
        month = (h + j - 7 * m + 114)/31;
        p = (h + j - 7 * m + 114)%31;
        day = p + 1;
        
        //print results 
        System.out.println("The Month is " + month + "\nThe Day is " + day);
        
        
    }

}
