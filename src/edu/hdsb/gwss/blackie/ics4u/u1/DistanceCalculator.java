/*
 * Name: Kyle Blackie
 * Date: Feb 08 ,2017
 * Description: Calculates the distance between two points using lattitudes and longitudes
 */
package edu.hdsb.gwss.blackie.ics4u.u1;

import java.util.Scanner;

/**
 *
 * @author 1blackiekyl
 */
public class DistanceCalculator {

    //constants
    static final double DEG_RAD = 180.0 / Math.PI;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //objects
        Scanner input = new Scanner(System.in);
        //variables
        double lat1,lon1, lat2, lon2;
        //user input
        System.out.println("Enter Lattitude 1:");
        lat1 = input.nextDouble();
        System.out.println("Enter Longitude 1:");
        lon1 = input.nextDouble();
        System.out.println("Enter Lattitude 2:");
        lat2 = input.nextDouble();
        System.out.println("Enter Longitude 2:");
        lon2 = input.nextDouble();
        //calculations
        double distance = distanceFormula(lat1 / DEG_RAD, lon1 / DEG_RAD, lat2 / DEG_RAD, lon2 / DEG_RAD);
        //output
        System.out.println("The distance is " + distance + "km");
    }

    public static double distanceFormula(double lat1, double lon1, double lat2, double lon2) {
        double distance;
        distance = 6378.8 * Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1));
        return distance;
        //6378.8 * arccos[sin(lat1/57.2958) * sin(lat2/57.2958) + cos(lat1/57.2958) * cos(lat2/57.2958) * cos(lon2/57.2958 - lon1/57.2958)]
    }
}
