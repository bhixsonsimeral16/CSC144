package nbody;

import edu.princeton.cs.StdDraw;
import java.util.*;
import java.awt.Color;

/******************************************************************************
 *  Compilation:  javac Body.java
 *  Execution:    java Body
 *  Dependencies: Vector.java StdDraw.java
 *
 *  Implementation of a 2D Body with a position, velocity and mass.
 *
 *
 ******************************************************************************/

public class Body {
    private Vector r;      // position
    private Vector v;      // velocity
    private final double mass;   // mass
    private final double radius; // radius of the window/universe
    private final int trailLength = 100; // controls the length of the tails (larger numbers represent longer tails)
    private final int bodyScale = 10000; // scales the size of the Bodies on a log scale (larger means smaller Bodies)
    // determines the rate at which the colors of the bodies change, should be scaled to the velocities of the bodies
    private final int red;
    private final int green;
    private final int blue;
    private final float colorControl = 10000f;
    private final boolean customColors;
    private double bounceCountX = 0; // count between bounces in the x direction
    private double bounceCountY = 0; // count between bounces in the y direction
    public ArrayList xCoords = new ArrayList(); // ArrayList containing all of the x positions that the Body has occupied
    public ArrayList yCoords = new ArrayList(); // ArrayList containing all of the y positions that the Body has occupied

    
    /**
     * <p>Create the Body object
     * @param r is the position of the body
     * @param v is the velocity vector of the body
     * @param mass is the mass of the body
     * @param radius is the radius of the Universe
     */
    public Body(Vector r, Vector v, double mass, double radius, boolean customColors, int red, int green, int blue) {
        this.r = r;
        this.v = v;
        this.mass = mass;
        this.radius = radius;
        this.customColors = customColors;
        this.red = red;
        this.green = green;
        this.blue = blue;
    } // Body( Vector, Vector, double )

    /**
     * <p>move alters the velocity and position vectors based 
     * <p>on the forces affecting the body over time
     * @param f is the force vector acting on this Body
     * @param dt is the period of time that has passed
     * @param bounce is whether or not we want the bodies to bounce off of the sides or not
     */
    public void move(Vector f, double dt, boolean bounce) {
        //If the Body runs into the barrier, reverse its speed.
        //Add a counter so that if it is still outside the barrier after 10ms it won;t reverse again.
        //Reset counter on bounce, add to counter if no bounce.
        //Enabled for both x and y directions
        if (bounce) {
            if ((r.cartesian(0) >= radius || r.cartesian(0) <= -radius) && bounceCountX > 2) {
                double[] vect = {-0.9 * v.cartesian(0), v.cartesian(1)};
                v = new Vector(vect);
                bounceCountX = 0;
            } else {
                bounceCountX++;
            }

            if ((r.cartesian(1) >= radius || r.cartesian(1) <= -radius) && bounceCountY > 2) {
                double[] vect = {v.cartesian(0), -0.9 * v.cartesian(1)};
                v = new Vector(vect);
                bounceCountY = 0;
            } else {
                bounceCountY++;
            }
        }
        Vector a = f.times(1/mass);
        v = v.plus(a.times(dt));
        r = r.plus(v.times(dt));
    } // move( Vector, double )

    /**
     * 
     * @param b is another Body in the system which is pulling on this body
     * <p>delta is the distance vector between Body a and b
     * <p>dist is the magnitude of delta 
     * <p>F is the magnitude of the force between the two Bodies
     * @return the force vector
     */
    public Vector forceFrom(Body b) {
        Body a = this;
        double G = 6.67e-11;
        Vector delta = b.r.minus(a.r);
        double dist = delta.magnitude();
        double F = (G * a.mass * b.mass) / (dist * dist);
        return delta.direction().times(F);
    } // forceFrom( Body )

    /**
     * <p>draw will draw each frame of the animation
     * <p>The Body's size on screen is relative to its mass on a logarithmic scale
     * <p>I have modified it so that the color of the Bodies changes with their velocity
     * <p>I have also modified it so that the Bodies leave tapering trails behind them
     */
    public void draw() {
        double massScale = Math.log(this.mass)/bodyScale;
        if(!customColors){
            double colorV = v.magnitude();
            if(colorV < colorControl) colorV = colorControl;
            StdDraw.setPenColor(Color.getHSBColor(colorControl / (float) colorV, 1.0f, 1.0f));
        }
        else if (customColors){
            StdDraw.setPenColor(new Color(red, green, blue));
        }
        xCoords.add(r.cartesian(0));
        yCoords.add(r.cartesian(1));
        
        if (xCoords.size() >= trailLength){
            xCoords.remove(0);
        }
        if (yCoords.size() >= trailLength){
            yCoords.remove(0);
        }
        
        StdDraw.setPenRadius(massScale);
        StdDraw.point(r.cartesian(0), r.cartesian(1));
        
        if(xCoords.size() > 2 && yCoords.size() > 2){
            for(int i = 0; i < xCoords.size()-1; i++){
                double trailScale = (0.9*massScale)/xCoords.size();
                StdDraw.setPenRadius((i+1)*trailScale);
                StdDraw.line((double) xCoords.get(i), (double) yCoords.get(i), (double) xCoords.get(i+1), (double) yCoords.get(i+1));
            }
        }
    } // draw()

    // this method is only needed if you want to change the size of the bodies
    public void draw(double penRadius) {
        if(!customColors){
            double colorV = v.magnitude();
            if(colorV < colorControl) colorV = colorControl;
            StdDraw.setPenColor(Color.getHSBColor(colorControl / (float) colorV, 1.0f, 1.0f));
        }
        else if (customColors){
            StdDraw.setPenColor(new Color(red, green, blue));
        }
        
        xCoords.add(r.cartesian(0));
        yCoords.add(r.cartesian(1));
        
        if (xCoords.size() >= trailLength){
            xCoords.remove(0);
        }
        if (yCoords.size() >= trailLength){
            yCoords.remove(0);
        }
        
        StdDraw.setPenRadius(penRadius);
        StdDraw.point(r.cartesian(0), r.cartesian(1));
        
        if(xCoords.size() > 2 && yCoords.size() > 2){
            for(int i = 0; i < xCoords.size()-1; i++){
                double trailScale = (0.9*penRadius)/xCoords.size();
                StdDraw.setPenRadius((i+1)*trailScale);
                StdDraw.line((double) xCoords.get(i), (double) yCoords.get(i), (double) xCoords.get(i+1), (double) yCoords.get(i+1));
            }
        }
    } // draw( double )

} // Body
