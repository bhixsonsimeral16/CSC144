package nbody;

import edu.princeton.cs.In;
import edu.princeton.cs.StdDraw;
import java.awt.Color;

/**
 * ****************************************************************************
 * Compilation: javac Universe.java Execution: java Universe dt input.txt
 * Dependencies: Body.java Vector.java StdIn.java StdDraw.java Datafiles:
 * http://www.cs.princeton.edu/introcs/34nbody/2body.txt
 * http://www.cs.princeton.edu/introcs/34nbody/3body.txt
 * http://www.cs.princeton.edu/introcs/34nbody/4body.txt
 * http://www.cs.princeton.edu/introcs/34nbody/2bodyTiny.txt
 *
 * This data-driven program simulates motion in the universe defined by the
 * standard input stream, increasing time at the rate on the command line.
 *
 * % java Universe 25000 4body.txt
 *
 *
 *****************************************************************************
 */
public class Universe {

    private final double radius;         // radius of universe
    private final int N;                 // number of bodies
    private final Body[] orbs;           // array of N bodies
    private boolean[][] stars;           // grid on which to place stars
    private final double inc;            // divide the length of the universe into equal parts
    private final boolean bounce;        // should the bodies bounce off the walls
    private final boolean customColors;  // does the input file contain custom colors for the bodies
    private final boolean angleInput;    // takes an angle input and places the body at that angle from the horizontal (solarSystem only)
    private boolean randomAngle = false; // will place all bodies at a random angle from the horizontal
    //false by default
    
    // read universe from file
    public Universe(String fileName) {

        // the authors' version reads from standard input
        // our version reads from a file
        In inputStream = new In(fileName);

        // number of bodies
        N = inputStream.readInt();
        
        // should the bodies bounce off of the walls
        bounce = inputStream.readBoolean();
        
        // does the inout file contain custom colors
        customColors = inputStream.readBoolean();
        
        // takes an angle input and places the body at that angle from the horizontal (solarSystem only)
        // if true, position and velocity will be written as if the planet were at 0 degrees
        angleInput = inputStream.readBoolean();
        
        // randomAngle will place all bodies at a random angle from the horizontal
        if(angleInput){
            randomAngle = inputStream.readBoolean();
        }

        // the set scale for drawing on screen
        radius = inputStream.readDouble();
        StdDraw.setXscale(-radius, +radius);
        StdDraw.setYscale(-radius, +radius);
        
        //describes where the stars will be drawn in the Universe
        stars = new boolean[200][200];
        stars = drawStars(stars);
        //Diameter of the universe divided by the length of the array is the distance between each potential star
        inc = (this.radius * 2)/stars.length;

        //read in the N bodies
        //rx and ry are tne starting position
        //vx and vy are the starting velocities
        //mass is the mass of the body
        //red, green, and blue are color attributes
        orbs = new Body[N];
        for (int i = 0; i < N; i++) {
            int red;
            int green;
            int blue;
            double angle;
            double rx = inputStream.readDouble();
            double ry = inputStream.readDouble();
            double vx = inputStream.readDouble();
            double vy = inputStream.readDouble();
            
            //change the x and y compents of velocity and position based on an angle input
            if(angleInput){
                // There will only be a positive x position and a negative y velocity
                double rMag = rx;
                double vMag = -vy;
                if(randomAngle){
                    angle = Math.random() * 360;
                }
                else{ 
                    angle = inputStream.readDouble();
                }
                // angle must be specified in radians
                angle = Math.toRadians(angle);
                // position based on trig rules
                rx = Math.cos(angle) * rMag;
                ry = Math.sin(angle) * rMag;
                // velocity is the integral of position, thus the change in trig formulas
                vx = Math.sin(angle) * vMag;
                vy = -Math.cos(angle) * vMag;                
            }
            double mass = inputStream.readDouble();
            if (customColors){
                red = inputStream.readInt();
                green = inputStream.readInt();
                blue = inputStream.readInt();
            }
            else{
                red = 0;
                green = 0;
                blue = 0;
            }
            double[] position = {rx, ry};
            double[] velocity = {vx, vy};
            Vector r = new Vector(position);
            Vector v = new Vector(velocity);
            orbs[i] = new Body(r, v, mass, radius, customColors, red, green, blue);
        } // for
    } // Universe()

    // increment time by dt units, assume forces are constant in given interval
    public void increaseTime(double dt) {

        // initialize the forces to zero
        Vector[] f = new Vector[N];
        for (int i = 0; i < N; i++) {
            f[i] = new Vector(new double[2]);
        } // for

        // compute the forces
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i != j) {
                    f[i] = f[i].plus(orbs[i].forceFrom(orbs[j]));
                } // if
            } // for
        } // for

        // move the bodies
        for (int i = 0; i < N; i++) {
            orbs[i].move(f[i], dt, bounce);
        } // for
    } // increaseTime( double )

    // draw the N bodies
    public void draw() {
        for (int i = 0; i < N; i++) {
            orbs[i].draw();
        } // for
    } // draw()
    
    public static boolean[][] drawStars(boolean[][] stars){
        for (int i = 0; i < stars.length; i++){
            for(int j = 0; j < stars[0].length; j++){
                stars[i][j] = (Math.random() > 0.99);
            }
        }
        return stars;
    }

    // client to simulate a universe
    public static void main(String[] args) {
        Universe newton = new Universe( args[1] );
        double dt = Double.parseDouble(args[0]);
        
        while (true) {
            StdDraw.clear(new Color(0,0,0));
            StdDraw.setPenColor(new Color(255,255,255));
            StdDraw.setPenRadius(0.002);
            for (int i = 0; i < newton.stars.length; i++){
                for(int j = 0; j < newton.stars[0].length; j++){
                    if(newton.stars[i][j]){
                        StdDraw.point(-newton.radius + (i*newton.inc), -newton.radius + (j*newton.inc));
                    }
                }
            }
            /*for(int i = -1; i < 1; i+=0.01){
                for (int j = -1; j < 1; i+=0.01){
                    double rng = Math.random();
                    if(rng < .95){
                        StdDraw.point(i, j);
                    }
                }
            }*/
            newton.increaseTime(dt);
            newton.draw();
            StdDraw.show(10);
        } // while
    } // main( String [] )
} // Universe
