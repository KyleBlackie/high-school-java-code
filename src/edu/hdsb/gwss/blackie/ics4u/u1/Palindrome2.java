/*
 * Name: Kyle Blackie
 * Date: Feb 10 ,2017
 * Description: checks if the sentence contains words that are spelled the same 
 *              forwards and backwards
 */
package edu.hdsb.gwss.blackie.ics4u.u1;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author 1blackiekyl
 */
public class Palindrome2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //objects
        Scanner input = new Scanner(System.in);
        String phrase, token;
        StringTokenizer tokens;
        String palindromes = "";
        //variables
        int counter = 0;
        //get data
        System.out.println("Enter a sentence/phrase");
        phrase = input.nextLine();
        //tokenize string
        tokens = new StringTokenizer(phrase);
        //check if there are palindromes in the tokens
        while(tokens.hasMoreTokens()){
            token = tokens.nextToken();
            if(Palindrome.palindromeCheck( token )){
                counter++;
                palindromes += token + ", ";
            }
        }
        //output
        if(counter == 1){
            System.out.println("There is one palindrome in the sentence");
        }else  System.out.println("There are " + counter + " palindromes in the sentence");
        if(counter > 0){
        System.out.println("The palindrome(s) is/are " + palindromes);
        }
        
    }
    
}
