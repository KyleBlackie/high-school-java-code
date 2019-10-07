/*
 * Name: Kyle Blackie
 * Date: February 13 ,2017
 * Description: finds ratings for movies 
 */
package edu.hdsb.gwss.blackie.ics4u.u1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1blackiekyl
 */
public class RottenTomatoes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //objects
        File reviews = new File("MovieReview/MovieReviews.txt");
        File userFile, negativeFile, positiveFile;
        PrintWriter positiveOutput, negativeOutput;
        Scanner input = new Scanner(System.in);
        String fileName, search;
        String positiveWord = "", negativeWord = "";
        //variables 
        boolean menu = true;
        int options;
        int lines = 0;
        double positiveScore = 0, negativeScore = 4, averageScore = 0;
        double wordCounter, totalAverageScore;
        //loop for menu
        do {
            //menu procedure
            menuScreen();
            //get input for menu options
            options = input.nextInt();
            //case switch for the user's choice
            switch (options) {
                case 1:
                    //ask user for input
                    System.out.print("\nEnter a Word: ");
                    search = input.next();
                    //calculate the average score and how many times it appears
                    averageScore = averageWordScore(search, reviews);
                    wordCounter = wordUseCounter(search, reviews);
                    //output
                    System.out.println(search + " appears " + wordCounter + " times.");
                    System.out.println("The average score for reviews containing " + search + " is " + averageScore);
                    break;
                case 2:
                    //input
                    System.out.print("Enter the name/location of the file with words you "
                            + "want to find the average score for: ");
                    fileName = input.next();
                    userFile = new File(fileName);
                    //read from file
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(userFile));
                        String word;
                        while ((word = br.readLine()) != null) {
                            lines++;
                            //get sum of average score
                            averageScore += averageWordScore(word, reviews);
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(RottenTomatoes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //calculate total average score
                    totalAverageScore = averageScore / lines;
                    System.out.print("The average score of the words in " + fileName + " is " + totalAverageScore
                            + "\nThe overall sentiment of " + fileName + " is ");
                    //check if positive or negative
                    if (isPositive(totalAverageScore)) {
                        System.out.println("positive.");
                    } else {
                        System.out.println("negative.");
                    }
                    break;
                case 3:
                    //input
                    System.out.print("Enter the name of the file with words you "
                            + "want to score: ");
                    fileName = input.next();
                    userFile = new File(fileName);
                    //read from file
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(userFile));
                        String word;
                        while ((word = br.readLine()) != null) {
                            //check score of word
                            averageScore = averageWordScore(word, reviews);
                            //do a max min check
                            if (averageScore > positiveScore) {
                                positiveScore = averageScore;
                                positiveWord = word;
                            } else if (averageScore < negativeScore) {
                                negativeScore = averageScore;
                                negativeWord = word;
                            }
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(RottenTomatoes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //output
                    System.out.println("The most positive word, with a score of " + positiveScore
                            + " is " + positiveWord + "\nThe most negative word, with a "
                            + "score of " + negativeScore + " is " + negativeWord);
                    break;
                case 4:
                    //create files and print writers
                    positiveFile = new File("positive.txt");
                    negativeFile = new File("negative.txt");
                    positiveOutput = new PrintWriter(positiveFile);
                    negativeOutput = new PrintWriter(negativeFile);
                    //input
                    System.out.print("Enter the name of the file with words you "
                            + "want to sort: ");
                    fileName = input.next();
                    userFile = new File(fileName);
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(userFile));
                        String word;
                        while ((word = br.readLine()) != null) {
                            //check score of word
                            averageScore = averageWordScore(word, reviews);
                            //sort words
                            if (isPositive(averageScore)) {
                                positiveOutput.println(word);
                            } else {
                                negativeOutput.println(word);
                            }
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(RottenTomatoes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    positiveOutput.close();
                    negativeOutput.close();
                    System.out.println("The words with positive values have been "
                            + "written to positive.txt and the words with negative"
                            + " value have been written to negative.txt");
                    break;
                case 5:
                    //break do-while loop to end program
                    menu = false;
                    break;
                default:
                    break;
            }
        } while (menu);
    }

    public static boolean isPositive(double averageScore) {
        //variables
        boolean positive = true;
        //check if score is negative
        if (averageScore < 1.99) {
            positive = false;
        }
        return positive;
    }

    public static double wordUseCounter(String search, File file) throws IOException {
        //variable
        double counter = 0;
        //set search to lower case
        search = search.toLowerCase();
        //read from file
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            //check each line for search value
            while ((line = br.readLine()) != null) {
                if (line.toLowerCase().contains(" " +search + " ")) {
                    counter++;
                }

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RottenTomatoes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return counter;
    }

    public static double averageWordScore(String search, File file) throws IOException {
        //variables
        double totalScore = 0;
        double lines = wordUseCounter(search, file);
        search = search.toLowerCase();
        //read from given file
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            //read each line
            while ((line = br.readLine()) != null) {
                //check for search value
                if (line.toLowerCase().contains(" " + search + " ")) {
                    //get score of review if it contains the search word
                    totalScore += Integer.parseInt(line.substring(0, 1));
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RottenTomatoes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalScore / lines;
    }

    public static void menuScreen() {
        //simple menu procedure
        System.out.println("\nWhat would you like to do?\n1: Get the score of a word"
                + "\n2: Get the average score of words in a file(one word per line)"
                + "\n3: Find the highest/lowest scoring words in a file"
                + "\n4: Sort words from a file into positive.txt and negative.txt"
                + "\n5: Exit the program");
        System.out.print("Enter a number 1-5: ");
    }

}
