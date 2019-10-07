/*
 * Name: Kyle Blackie
 * Date: Feb 10 ,2017
 * Description: checks if the sentence entered is a palindrome itself
 */
package edu.hdsb.gwss.blackie.ics4u.u1;

import java.util.Scanner;

/**
 *
 * @author 1blackiekyl
 */
public class Palindrome3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //objects 
        Scanner input = new Scanner(System.in);
        String phrase;
        //get data
        System.out.println("Enter a phrase");
        phrase = input.nextLine();
        //remove spaces
        phrase = phrase.replaceAll("\\s", "");
        //output statements
        System.out.print("This phrase is ");
        if(Palindrome.palindromeCheck(phrase)){
            System.out.println("a palindrome");
        }else System.out.println("not a palindrome");
        
        
        
    }
    
}
