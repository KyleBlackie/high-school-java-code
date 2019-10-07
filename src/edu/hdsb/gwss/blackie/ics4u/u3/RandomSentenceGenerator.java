/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hdsb.gwss.blackie.ics4u.u3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1blackiekyl
 */
public class RandomSentenceGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Objects
        File file = new File("data/random.sentence.generator/Programming-bug.g");
        HashMap<String, ArrayList<String>> grammar = new HashMap<>();
        ArrayList<String> options = new ArrayList();
        Scanner input;
        String line;
        String tag;
        String sentence;
        //try to read from the file
        try {
            input = new Scanner(file);
            //read while there is more lines
            while (input.hasNextLine()) {
                line = input.nextLine();
                //check for opening brace
                if (line.equals("{")) {
                    //get tag while also going to next line
                    tag = input.nextLine();
                    tag = tag.trim();
                    tag = tag.toLowerCase();
                    line = input.nextLine();
                    //do this until the end brace is found
                    while (!line.equals("}")) {
                        //add up lines until ; is found
                        while (!line.contains(";")) {
                            line += input.nextLine();
                        }
                        //get words up until the semi-colon
                        line = line.substring(0, line.indexOf(";"));
                        //remove blank space
                        line = line.trim();
                        options.add(line);
                        line = input.nextLine();
                    }
                    //add to hash map
                    grammar.put(tag, options);
                    options = new ArrayList();

                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RandomSentenceGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        //create the sentence and print it out
        sentence = getWord("<start>", grammar);
        System.out.println(formatPrint(sentence)); 

    }

    public static String getWord(String tag, HashMap<String, ArrayList<String>> grammar) {
        //variable key
        int key;
        //objects
        ArrayList<String> list;
        String word = "";
        //set tag to lower case
        tag = tag.toLowerCase();
        list = grammar.get(tag);
        //pick a random key
        key = (int) (Math.random() * list.size());
        //loop through chars in key
        for (int i = 0; i < list.get(key).length(); i++) {
            //if another tag is found 
            if (list.get(key).charAt(i) == '<') {
                //search for end of tag
                for (int x = i; x < list.get(key).length(); x++) {
                    if (list.get(key).charAt(x) == '>') {
                        //call method again for new tag
                        word += getWord(list.get(key).substring(i, x + 1), grammar);
                        //continue from where you left off after the tag
                        i = x;
                        break;
                    }
                }
            } else {
                //add chars to word
                word += list.get(key).charAt(i);
            }
        }
        return word;
    }
    
    public static String formatPrint(String sentence){
        //Get rid of extra spaces
        sentence = sentence.trim();
        //variable for line to cut at
        int line = 80;
        //Object for output
        String format = "";
        //better format the sentences
        sentence = sentence.replaceAll("  ", " ");
        sentence = sentence.replaceAll(" ,", ",");
        sentence = sentence.replaceAll(" \\.", ".");
        //loop through chars
        for(int i = 0; i < sentence.length();i++){
            if(i > line && sentence.charAt(i) == ' '){
                format += "\n";
                line += 80;
            }else{
                format += sentence.charAt(i);
            }
        }
        return format;
        
    }

}
