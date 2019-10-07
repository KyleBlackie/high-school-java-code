/*
 * Mountain Paths - Greedy Algorithm
 * Mr. Muir
 * 2017.01.24 - v1.0
 */
package edu.hdsb.gwss.blackie.ics4u.u2;

import java.util.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MountainPaths {

    /**
     * Mount Paths
     */
    public static void main(String[] args) throws Exception {

        // ***********************************
        // TASK 1:  read data into a 2D Array
        // 
        System.out.println("TASK 1: READ DATA");
        int[][] data = read("Colorado.844x480.dat");
        //int[][] data = read("Test.40x20.dat");
        // ***********************************
        // Construct DrawingPanel, and get its Graphics context
        //
        DrawingPanel panel = new DrawingPanel(data[0].length, data.length);
        Graphics g = panel.getGraphics();

        // ***********************************
        // TASK 2:  find HIGHEST & LOWEST elevation; for GRAY SCALE
        //
        System.out.println("TASK 2: HIGHEST / LOWEST ELEVATION");
        int min = findMinValue(data);
        System.out.println("\tMin: " + min);

        int max = findMaxValue(data);
        System.out.println("\tMax: " + max);

        // ***********************************
        // TASK 3:  Draw The Map
        //
        System.out.println("TASK 3: DRAW MAP");
        drawMap(g, data, min, max);

        // ***********************************
        // TASK 4:  implement indexOfMinInCol
        //
        System.out.println("TASK 4: INDEX OF MIN IN COL 0");
        int minRow = indexOfMinInCol(data, 0);
        System.out.println("\tRow with lowest Col 0 Value: " + minRow);

        // ***********************************
        // TASK 4:  use minRow as starting point to draw path
        //
        System.out.println("TASK 5: PATH from LOWEST STARTING ELEVATION");
        g.setColor(Color.RED);
        int totalChange = drawLowestElevPath(g, data, minRow);
        System.out.println("\tLowest-Elevation-Change Path starting at row " + minRow + " gives total change of: " + totalChange);

        // ***********************************
        // TASK 5:  determine the BEST path
        //
        g.setColor(Color.RED);
        int bestRow = indexOfLowestElevPath(g, data);

        // ***********************************
        // TASK 6:  draw the best path
        //
        System.out.println("TASK 6: DRAW BEST PATH");
        //drawMap.drawMap(g); //use this to get rid of all red lines
        g.setColor(Color.GREEN); //set brush to green for drawing best path
        totalChange = drawLowestElevPath(g, data, bestRow);
        System.out.println("\tThe Lowest-Elevation-Change Path starts at row: " + bestRow + " and gives a total change of: " + totalChange);
    }

    /**
     * This method reads a 2D data set from the specified file. The Graphics'
     * industry standard is width by height (width x height), while programmers
     * use rows x cols / (height x width).
     *
     * @param fileName the name of the file
     * @return a 2D array (rows x cols) of the data from the file read
     */
    public static int[][] read(String fileName) {
        int[][] data = null;

        //Objects
        StringTokenizer tokenizer;
        Scanner input;
        //variables
        int y = 1, x;
        //try to read from file
        try {
            //scan file
            input = new Scanner(new File(fileName));
            //tokenize one line
            tokenizer = new StringTokenizer(input.nextLine());
            //x = width / number of columns
            x = tokenizer.countTokens();
            //y equals the number of lines 
            while (input.hasNextLine()) {
                y++;
                input.nextLine();
            }
            //scan file again to input all data
            input = new Scanner(new File(fileName));
            //set the size of 2d array
            data = new int[y][x];
            //input data to the array with nested for loop
            for (int[] data1 : data) {
                for (int col = 0; col < data1.length; col++) {
                    data1[col] = input.nextInt();
                }
            }
            //close file
            input.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MountainPaths.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    /**
     * @param grid a 2D array from which you want to find the smallest value
     * @return the smallest value in the given 2D array
     */
    public static int findMinValue(int[][] grid) {

        //set min to first value
        int min = grid[0][0];
        //loop through array to find the min value
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                //compare value to the min
                if (grid[row][col] < min) {
                    min = grid[row][col];
                }
            }
        }
        return min;

    }

    /**
     * @param grid a 2D array from which you want to find the largest value
     * @return the largest value in the given 2D array
     */
    public static int findMaxValue(int[][] grid) {

        //set max to first value
        int max = grid[0][0];
        //loop through array to find the max value
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                //compare value to the max
                if (grid[row][col] > max) {
                    max = grid[row][col];
                }
            }
        }
        return max;

    }

    /**
     * Given a 2D array of elevation data create a image of size rows x cols,
     * drawing a 1x1 rectangle for each value in the array whose color is set to
     * a a scaled gray value (0-255). Note: to scale the values in the array to
     * 0-255 you must find the min and max values in the original data first.
     *
     * @param g a Graphics context to use
     * @param grid a 2D array of the data
     */
    public static void drawMap(Graphics g, int[][] data, int min, int max) {

        //constants
        final double scale = (max - min) / 255;
        final double yInt = min / scale;
        //variables
        int grey;
        //loop through 2d array
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) {
                //determine grey scale value (0-255)
                grey = (int) ((data[row][col] / scale) - yInt);
                //set the colour to the new grey scale
                g.setColor(new Color(grey, grey, grey));
                //draw pixel at given col and row
                g.drawRect(col, row, 0, 0);
            }
        }
    }

    /**
     * Scan a single column of a 2D array and return the index of the row that
     * contains the smallest value
     *
     * @param grid a 2D array
     * @col the column in the 2D array to process
     * @return the index of smallest value from grid at the given col
     */
    public static int indexOfMinInCol(int[][] grid, int col) {
        //set min col to 0
        int min = 0;
        //loop through rows to find min value at the given column
        for (int row = 1; row < grid.length; row++) {
            //compare value with min
            if (grid[row][col] < grid[min][col]) {
                min = row;
            }
        }
        return min;
    }

    /**
     * Find the minimum elevation-change route from West-to-East in the given
     * grid, from the given starting row, and draw it using the given graphics
     * context
     *
     * @param g - the graphics context to use
     * @param grid - the 2D array of elevation values
     * @param row - the starting row for traversing to find the min path
     * @return total elevation of the route
     */
    public static int drawLowestElevPath(Graphics g, int[][] data, int row) {
        //variables
        int newRow = row;
        int lowElev, elevChange, random, min, totalElevChange = 0;
        int[] minChange = new int[3];
        //draw starting pixel
        g.drawRect(0, newRow, 0, 0);
        //loop through columns with a variable row
        for (int col = 0; col < data[newRow].length - 1; col++) {
            //get data for fwd
            lowElev = Math.abs(data[newRow][col] - data[newRow][col + 1]);
            //check if at top row
            if (newRow == 0) {
                elevChange = Math.abs(data[newRow][col] - data[newRow + 1][col + 1]);
                if (elevChange < lowElev) {
                    lowElev = elevChange;
                    newRow++;
                }
                //check for bottom row
            } else if (newRow == data.length - 1) {
                elevChange = Math.abs(data[newRow][col] - data[newRow - 1][col + 1]);
                if (elevChange < lowElev) {
                    lowElev = elevChange;
                    newRow--;
                }
                //all other cases
            } else {
                //get data
                minChange[0] = Math.abs(data[newRow][col] - data[newRow - 1][col + 1]);
                minChange[1] = Math.abs(data[newRow][col] - data[newRow][col + 1]);
                minChange[2] = Math.abs(data[newRow][col] - data[newRow + 1][col + 1]);
                min = 0;
                //find min value
                for (int i = 1; i < minChange.length; i++) {
                    if (minChange[i] < minChange[min]) {
                        min = i;
                    }
                }
                //check if min is not fwd
                if (min != 1) {
                    //check if fwd-up and fwd-down values are the same
                    if (minChange[0] == minChange[2]) {
                        //choose randomly between fwd-up/down
                        random = (int) (Math.random() * 2) + 1;
                        if (random == 1) {
                            min = 2;
                        } else {
                            min = 0;
                        }
                    }
                }
                //change newRow and lowElev based off of value of min variable
                if (min == 0) {
                    newRow--;
                    lowElev = minChange[0];
                } else if (min == 2) {
                    newRow++;
                    lowElev = minChange[2];
                }
            }
            //add onto total elevation change
            totalElevChange += lowElev;
            //draw pixel for column ahead
            g.drawRect(col + 1, newRow, 0, 0);
        }
        return totalElevChange;
    }
        
    /**
     * Generate all west-to-east paths, find the one with the lowest total
     * elevation change, and return the index of the row that path starts on.
     *
     * @param g - the graphics context to use
     * @param grid - the 2D array of elevation values
     * @return the index of the row where the lowest elevation-change path
     * starts.
     */
    public static int indexOfLowestElevPath(Graphics g, int[][] data) {
        //variables
        int min = 0, currentValue;
        //initialise minValue using min = 0
        int minValue = drawLowestElevPath(g, data, min);
        //loop through rows
        for (int row = 1; row < data.length; row++) {
            //draw path at current row and store its value
            currentValue = drawLowestElevPath(g, data, row);
            //compare currentValue to minValue
            if (currentValue < minValue) {
                //set min to current row
                min = row;
                //set minValue to the value for the current row
                minValue = currentValue;

            }

        }
        //return min index
        return min;
    }

}
