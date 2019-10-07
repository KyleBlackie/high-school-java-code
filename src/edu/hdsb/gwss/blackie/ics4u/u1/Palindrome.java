/*
 * Name: Kyle Blackie
 * Date: Feb 10 ,2017
 * Description: checks if the word is spelled the same forwards and backwards
 */
package edu.hdsb.gwss.blackie.ics4u.u1;

import java.util.Scanner;

/**
 *
 * @author 1blackiekyl
 */
public class Palindrome {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //objects
        Scanner input = new Scanner(System.in);
        String word;
        //get data
        System.out.println("Enter a word");
        word = input.nextLine();
        //print out a final statement
        System.out.print("This word is ");
        //check if palindrome
        if(palindromeCheck(word)){
            System.out.println("a palinrome.");
        }else System.out.println("not a palindrome");
        
    }
    
    public static boolean palindromeCheck(String s){
        String forwards,backwards = "";
        boolean palindrome = false;
        forwards = s.substring(0);
        for(int i = s.length()-1; i >= 0; i--){
            backwards += s.charAt(i);
        }
        if(forwards.equalsIgnoreCase(backwards)){
            palindrome = true;
        }
        return palindrome;
    }
    
}
