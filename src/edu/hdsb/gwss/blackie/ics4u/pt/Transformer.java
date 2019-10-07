package edu.hdsb.gwss.blackie.ics4u.pt;

import becker.xtras.imageTransformation.ITransformations;
import java.util.Stack;

public class Transformer extends Object implements ITransformations {

    public static final int MIN_NUM_TRANS = 11;
    public static final String DARKEN = "Darken";
    public static final String BRIGHTEN = "Brighten";
    public static final String INVERT = "Invert";
    public static final String ROTATE = "Rotate";
    public static final String FLIPX = "Flip X";
    public static final String FLIPY = "Flip Y";
    public static final String MIRROR = "Mirror";
    public static final String SCALE = "Scale 50";
    public static final String BLUR = "Blur";
    public static final String UNDO = "Undo";
    public static final String RESET = "Reset";
    private String[] transformations;
    private Stack pixelsTrace; //for undo
    private static final int MAX_INTENSITY = 255;   // the value for pure white
    private static final int MIN_INTENSITY = 0;     // the value for pure black
    private static final int INTENSITY_STEP = 10;   // the value used to increase or decrease the brightness
    private int[][] pictureOriginal;
    private int[][] picture;

    /**
     * Construct a Transformer object by setting the possible transformations
     * available.
     */
    public Transformer() {
        super();
        this.transformations = new String[MIN_NUM_TRANS];
        this.transformations[0] = DARKEN;
        this.transformations[1] = BRIGHTEN;
        this.transformations[2] = INVERT;
        this.transformations[3] = FLIPX;
        this.transformations[4] = FLIPY;
        this.transformations[5] = ROTATE;
        this.transformations[6] = MIRROR;
        this.transformations[7] = SCALE;
        this.transformations[8] = BLUR;
        this.transformations[9] = UNDO;
        this.transformations[10] = RESET;
        this.pixelsTrace = new Stack();

    }

    /**
     * Construct a Transformer object by setting the possible transformations
     * available and initializing the state of the image.
     *
     * @param originalPic A 2-D array representing a grey scale image. The array
     * contains values from 0 - 255.
     */
    public Transformer(int[][] originalPic) {
        this();
        this.setPixels(originalPic);
        this.pictureOriginal = originalPic;
    }

    /**
     * Get the image that was transformed.
     *
     * @return The pixels representing the image.
     */
    @Override
    public int[][] getPixels() {
        return this.picture;
    }

    /**
     * Set the image to be transformed to a new set of pixels.
     *
     * @param newPix The new image to be used for subsequent transformations.
     */
    @Override
    public void setPixels(int[][] newPix) {
        this.pictureOriginal = newPix;
        this.picture = this.copyArray(newPix);
        this.pixelsTrace.push(this.copyArray(this.picture));
    }

    /**
     * A array filled with the names of the transformations implemented by this
     * class.
     *
     * @return The array of transformation names.
     */
    @Override
    public String[] getTransformationNames() {
        return transformations;
    }

    public static void display(int[][] twoDArray) {

        for (int row = 0; row < twoDArray.length; row++) {
            for (int col = 0; col < twoDArray[row].length; col++) {
                if (twoDArray[row][col] == 0) {
                    System.out.print(" .");
                } else {
                    System.out.print(" O");
                }
            }
            System.out.println("");
        }
    }

    /**
     * Perform the transformation indicated.
     *
     * @param transformationName The name of the transformation to perform. Must
     * be one of the transformation names returned by {@link #getTransformationNames
     * getTransformationNames}.
     */
    public void performTransformation(String transformationName) {

        this.pixelsTrace.push(this.copyArray(this.picture));

        if (DARKEN.equals(transformationName)) {
            this.picture = changeIntensity(this.picture, -1 * INTENSITY_STEP);
        } else if (BRIGHTEN.equals(transformationName)) {
            this.picture = changeIntensity(this.picture, INTENSITY_STEP);
        } else if (INVERT.equals(transformationName)) {
            this.picture = invert(this.picture);
        } else if (FLIPX.equals(transformationName)) {
            this.picture = flipX(this.picture);
        } else if (FLIPY.equals(transformationName)) {
            this.picture = flipY(this.picture);
        } else if (ROTATE.equals(transformationName)) {
            this.picture = rotate(this.picture);
        } else if (MIRROR.equals(transformationName)) {
            this.picture = mirror(this.picture);
        } else if (SCALE.equals(transformationName)) {
            this.picture = scale50(this.picture);
        } else if (UNDO.equals(transformationName)) {
            if (this.pixelsTrace.size() > 2) {
                this.pixelsTrace.pop();
                this.picture = (int[][]) this.pixelsTrace.pop();
            }
        } else if (RESET.equals(transformationName)) {
            this.picture = this.pictureOriginal;
        } else if (BLUR.equals(transformationName)) {
            this.picture = blur(this.picture);
        } else {
            throw new Error("Invalid transformation requested.");
        }

    }

    /**
     * TODO: ICS4U PERFORMANCE TASK
     */
    private int[][] copyArray(int[][] a) {

        //create new 2d array
        int[][] copy = new int[a.length][a[0].length];

        //loop through and set values to equal those in a
        for (int r = 0; r < a.length; r++) {
            for (int c = 0; c < a[0].length; c++) {
                copy[r][c] = a[r][c];
            }
        }

        return copy;
    }

    /**
     * TODO: ICS4U PERFORMANCE TASK
     *
     * Darken or Brighten based on the step size.
     *
     */
    private int[][] changeIntensity(int[][] sourcePixels, int stepSize) {

        //loop through values
        for (int r = 0; r < sourcePixels.length; r++) {
            for (int c = 0; c < sourcePixels[0].length; c++) {
                //add step size to value
                sourcePixels[r][c] += stepSize;
                //check if the value is less than 0 or greater than 255 
                if (sourcePixels[r][c] < MIN_INTENSITY) {
                    sourcePixels[r][c] = 0;
                } else if (sourcePixels[r][c] > MAX_INTENSITY) {
                    sourcePixels[r][c] = 255;
                }
            }
        }

        return sourcePixels;
    }

    /**
     * TODO: ICS4U PERFORMANCE TASK
     */
    private int[][] invert(int[][] sourcePixels) {

        int[][] inverted = new int[sourcePixels.length][sourcePixels[0].length];
        for (int r = 0; r < sourcePixels.length; r++) {
            for (int c = 0; c < sourcePixels[0].length; c++) {
                // subtract max then invert sign
                inverted[r][c] = -1 * (sourcePixels[r][c] - MAX_INTENSITY);
            }
        }
        return inverted;

    }

    /**
     * TODO: ICS4U PERFORMANCE TASK
     */
    private int[][] flipX(int[][] sourcePixels) {

        int[][] flipped = new int[sourcePixels.length][sourcePixels[0].length];
        //flip column location
        for (int r = 0; r < sourcePixels.length; r++) {
            for (int c = 0; c < sourcePixels[0].length; c++) {
                //invert the row of that pixel
                flipped[-1 * (r - sourcePixels.length + 1)][c] = sourcePixels[r][c];
            }
        }

        return flipped;
    }

    /**
     * TODO: ICS4U PERFORMANCE TASK
     */
    private int[][] flipY(int[][] sourcePixels) {

        int[][] flipped = new int[sourcePixels.length][sourcePixels[0].length];

        //flip column location
        for (int r = 0; r < sourcePixels.length; r++) {
            for (int c = 0; c < sourcePixels[0].length; c++) {
                //invert the column of that pixel
                flipped[r][-1 * (c - sourcePixels[0].length + 1)] = sourcePixels[r][c];
            }
        }

        return flipped;
    }

    /**
     * TODO: ICS4U PERFORMANCE TASK
     */
    private int[][] rotate(int[][] sourcePixels) {

        //create new array with opposite dimensions
        int[][] rotated = new int[sourcePixels[0].length][sourcePixels.length];

        //loop through array and rearrange pixels
        for (int r = 0; r < sourcePixels.length; r++) {
            for (int c = 0; c < sourcePixels[0].length; c++) {
                rotated[c][-1 * (r - sourcePixels.length + 1)] = sourcePixels[r][c];
            }
        }

        return rotated;
    }

    /**
     * TODO: ICS4U PERFORMANCE TASK
     */
    private int[][] mirror(int[][] sourcePixels) {

        //create new array with double the column length
        int[][] mirrored = new int[sourcePixels.length][sourcePixels[0].length * 2];
        int[][] copy = copyArray(sourcePixels);
        copy = flipY(copy);

        //add array twice
        for (int r = 0; r < sourcePixels.length; r++) {
            for (int c = 0; c < sourcePixels[0].length; c++) {
                mirrored[r][c] = sourcePixels[r][c];
            }
            for (int c2 = sourcePixels[0].length; c2 < sourcePixels[0].length * 2; c2++) {
                mirrored[r][c2] = copy[r][c2 - sourcePixels[0].length];
            }
        }

        return mirrored;
    }

    /**
     * TODO: ICS4U PERFORMANCE TASK
     */
    private int[][] scale50(int[][] sourcePixels) {

        int[][] scaled = new int[sourcePixels.length / 2][sourcePixels[0].length / 2];

        for (int r = 0; r < sourcePixels.length - 1; r++) {
            for (int c = 0; c < sourcePixels[0].length - 1; c++) {
                scaled[r / 2][c / 2] = sourcePixels[r][c];
            }
        }

        return scaled;
    }

    /**
     * TODO: ICS4U PERFORMANCE TASK
     */
    private int[][] blur(int[][] sourcePixels) {

        int[][] blurred = new int[sourcePixels.length][sourcePixels[0].length];

        //for first column
        for (int r = 0; r < sourcePixels.length; r++) {
            blurred[r][0] = (sourcePixels[r][0] + sourcePixels[r][1]) / 2;
        }

        //for last column
        for (int r = 0; r < sourcePixels.length; r++) {
            blurred[r][sourcePixels[0].length-1] = (sourcePixels[r][sourcePixels[0].length - 1] + sourcePixels[r][sourcePixels[0].length - 2]) / 2;
        }
        

        //for first row
        for (int c = 1; c < sourcePixels[0].length - 1; c++) {
            blurred[0][c] = (sourcePixels[0][c] + sourcePixels[0][c - 1] + sourcePixels[0][c + 1] + sourcePixels[1][c - 1] + sourcePixels[1][c] + sourcePixels[1][c + 1]) / 6;
        }

        //for last row
        for (int c = 1; c < sourcePixels[0].length - 1; c++) {
            blurred[sourcePixels.length - 1][c] = (sourcePixels[sourcePixels.length - 1][c] + sourcePixels[sourcePixels.length - 1][c - 1] + sourcePixels[sourcePixels.length - 2][c + 1] + sourcePixels[sourcePixels.length - 2][c - 1] + sourcePixels[sourcePixels.length - 2][c] + sourcePixels[sourcePixels.length - 2][c + 1]) / 6;
        }

        //rest
        for (int r = 1; r < sourcePixels.length-1; r++) {
            for (int c = 1; c < sourcePixels[0].length-1; c++) {
                blurred[r][c] =(sourcePixels[r][c] + sourcePixels[r][c-1] + sourcePixels[r][c+1] + sourcePixels[r-1][c] + sourcePixels[r-1][c-1] + sourcePixels[r-1][c+1] + sourcePixels[r+1][c] + sourcePixels[r+1][c-1] + sourcePixels[r+1][c+1])/9;
            }
        }

        return blurred;

    }

    /**
     * TODO: ICS4U PERFORMANCE TASK
     */
    public static void main(String[] args) {

        int[][] myPicture = new int[4][15];

        myPicture[0][0] = 1;
        myPicture[1][1] = 1;
        myPicture[2][2] = 1;
        myPicture[3][3] = 1;
        myPicture[2][4] = 1;
        myPicture[1][5] = 1;
        myPicture[2][6] = 1;
        myPicture[3][7] = 1;
        myPicture[2][8] = 1;
        myPicture[1][9] = 1;
        myPicture[0][10] = 1;

//       Construct the test object
        Transformer test = new Transformer(myPicture);

        //test copyArray()
        int[][] copy = test.copyArray(myPicture);

//       Display Original Image
        System.out.println("Original\n");
        display(copy);

//       Test flip on X-axis
        System.out.println("\nFlipped on the X axis.\n");
        test.performTransformation(FLIPX);
        display(test.getPixels());

//       Test flip on Y-axis
        System.out.println("\nFlipped on the Y axis.\n");
        test.performTransformation(FLIPY);
        display(test.getPixels());
//
////       Test Rotate 90 degrees
        System.out.println("\nRotated 90 degrees.\n");
        test.performTransformation(ROTATE);
        display(test.getPixels());

        System.out.println("\nRotated 90 degrees.\n");
        test.performTransformation(ROTATE);
        display(test.getPixels());
//
//       Test Rotate Scale 50%
        System.out.println( "\nScaled 50%.\n" );
        test.performTransformation( SCALE );
        display( test.getPixels() );
//
//       Test Mirror Image
        System.out.println("\nMirror image.\n");
        test.performTransformation(MIRROR);
        display(test.getPixels());
//
////       Test Reset
        System.out.println( "\nReset image.\n" );
        test.performTransformation( RESET );
        display( test.getPixels() );
    }

}
