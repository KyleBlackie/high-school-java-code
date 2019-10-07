/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hdsb.gwss.blackie.ics4u.u3;

/**
 *
 * @author 1blackiekyl
 */
public class Recursion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int m = 150, n = 345;

        System.out.println(gcd(m, n));

    }

    public static long gcd(int m, int n) {
        if (m == n) {
            return m;
        }

        if (m > n) {
            return gcd(n, m - n);
        }
        
        return gcd(n,m);

    }
    public static long f(int n) {
        if (n < 0) {
            return -1;
        } else if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * f(n - 1);
        }
    }

}
