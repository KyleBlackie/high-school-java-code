package edu.hdsb.gwss.blackie.ics4u.u2;

/**
 *
 * @author 1blackiekyl
 */
public class TwoDArrays {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[][] marks = {
            {44, 55, 66, 77},
            {88, 99, 88, 55},
            {33, 44, 56},};
        perimeter(marks);
    }

    public static void simpleArray(int[][] marks) {
        int max = marks[0][0];

        for (int i = 0; i < marks.length; i++) {
            for (int x = 0; x < marks[i].length; x++) {
                if (marks[i][x] > max) {
                    max = marks[i][x];
                }
            }
        }
        System.out.println(max);
    }

    public static void perimeter(int[][] marks) {
        int sum = 0;
        for(int col = 0; col < marks[0].length; col++){
            sum += marks[0][col];
        }
        System.out.println(sum);
    }
}
