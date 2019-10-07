package edu.hdsb.gwss.blackie.ics4u.u6;

/*
 * ICS4U.2015.16.S2
 */

public class TypeConversion {

    public static void main( String[] args ) {

        longConversions();
        intConversions();
        booleanConversions();
        charConversions();
        doubleConversions();
        stringConversions();
    }

    public static void stringConversions() {
        System.out.println( "------------------" );
        System.out.println( "string conversions" );

        // STRING
        String s = "true";

        // STRING --> BOOLEAN: NOT POSSIBLE
        boolean b = Boolean.parseBoolean( s );
        System.out.println( s + " --> " + b );

        // STRING --> INT
        s = "123";
        int i = Integer.parseInt( s );
        System.out.println( s + " --> " + i );

        // STRING  --> LONG
        long l = Long.parseLong( s );
        System.out.println( s + " --> " + l );

        // STRING  --> LONG
        s = "123.456";
        double d = Double.parseDouble( s );
        System.out.println( s + " --> " + d );

        // STRING --> CHAR: NOT POSSIBLE
        char c = (char) s.charAt( 0 );
        System.out.println( s + " --> " + c );

    }

    public static void doubleConversions() {
        System.out.println( "------------------" );
        System.out.println( "double conversions" );

        // DOUBLE
        double d = 123.456;

        // DOUBLE --> BOOLEAN: NOT POSSIBLE
        // DOUBLE --> INT
        int i = (int) d;
        System.out.println( d + " --> " + i );

        // DOUBLE  --> LONG
        long l = (long) d;
        System.out.println( d + " --> " + l );

        // DOUBLE --> CHAR
        char c = (char) d;
        System.out.println( d + " --> " + c );

        // DOUBLE --> STRING
        String s = "" + d;
        System.out.println( d + " --> " + s );

        s = Double.toString( d );
        System.out.println( d + " --> " + s );

    }

    public static void charConversions() {
        System.out.println( "----------------" );
        System.out.println( "char conversions" );

        // CHAR
        char c = 'C';

        // CHAR --> BOOLEAN: NOT POSSIBLE
        // CHAR --> INT
        int i = (int) c;
        System.out.println( c + " --> " + i );

        // CHAR  --> LONG
        long l = (long) c;
        System.out.println( c + " --> " + l );

        // CHAR --> DOUBLE
        double d = (double) c;
        System.out.println( c + " --> " + d );

        // INT --> STRING
        String s = "" + c;
        System.out.println( c + " --> " + s );

        s = Character.toString( c );
        System.out.println( c + " --> " + s );

    }

    public static void booleanConversions() {
        System.out.println( "-------------------" );
        System.out.println( "boolean conversions" );

        // BOOLEAN
        boolean b = true;

        // BOOLEAN --> INT:     NOT POSSIBLE
        // BOOLEAN --> CHAR:    NOT POSSIBLE
        // BOOLEAN --> LONG:    NOT POSSIBLE
        // BOOLEAN --> DOUBLE:  NOT POSSIBLE
        // BOOLEAN --> STRING
        String s = "" + b;
        System.out.println( b + " --> " + s );

        s = Boolean.toString( b );
        System.out.println( b + " --> " + s );

    }

    public static void intConversions() {

        System.out.println( "---------------" );
        System.out.println( "int conversions" );

        // INTEGER
        int i = 123;

        // INT --> BOOLEAN: NOT POSSIBLE
        // INT --> CHAR
        char c = (char) i;
        System.out.println( i + " --> " + c );

        // INT  --> LONG
        long l = (long) i;
        System.out.println( i + " --> " + l );

        // INT --> DOUBLE
        double d = (double) i;
        System.out.println( i + " --> " + d );

        // INT --> STRING
        String s = "" + i;
        System.out.println( i + " --> " + s );

        s = Integer.toString( i );
        System.out.println( i + " --> " + s );

    }

    public static void longConversions() {

        System.out.println( "----------------" );
        System.out.println( "long conversions" );

        // LONG
        long l = 123l;

        // LONG --> BOOLEAN: NOT POSSIBLE
        // LONG --> CHAR
        char c = (char) l;
        System.out.println( l + " --> " + c );

        // LONG  --> INT
        int i = (int) l;
        System.out.println( l + " --> " + i );

        // LONG --> DOUBLE
        double d = (double) l;
        System.out.println( l + " --> " + i );

        // LONG --> STRING
        String s = "" + l;
        System.out.println( l + " --> " + s );

        s = Long.toString( l );
        System.out.println( l + " --> " + s );

    }

}
