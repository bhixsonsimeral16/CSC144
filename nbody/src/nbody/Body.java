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
    public ArrayList xCoords = new ArrayList(); // ArrayList containing all of the x positions that the Body has occupied
    public ArrayList yCoords = new ArrayList(); // ArrayList containing all of the y positions that the Body has occupied

    
    /**
     * <p>Create the Body object
     * @param r is the position of the body
     * @param v is the velocity vector of the body
     * @param mass is the mass of the body
     */
    public Body(Vector r, Vector v, double mass) {
        this.r = r;
        this.v = v;
        this.mass = mass;
    } // Body( Vector, Vector, double )

    /**
     * <p>move alters the velocity and position vectors based 
     * <p>on the forces affecting the body over time
     * @param f is the force vector acting on this Body
     * @param dt is the period of time that has passed
     */
    public void move(Vector f, double dt) {
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
        double massScale = Math.log(this.mass)/1000;
        double colorV = v.magnitude();
        if(colorV < 10000) colorV = 10000;
        StdDraw.setPenColor(Color.getHSBColor(10000f / (float) colorV, 1.0f, 1.0f));
        
        xCoords.add(r.cartesian(0));
        yCoords.add(r.cartesian(1));
        
        if (xCoords.size() >= 100){
            xCoords.remove(0);
        }
        if (yCoords.size() >= 100){
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
        double massScale = Math.log(this.mass)/1000;
        double colorV = v.magnitude();
        if(colorV < 10000) colorV = 10000;
        StdDraw.setPenColor(Color.getHSBColor(10000f / (float) colorV, 1.0f, 1.0f));
        
        xCoords.add(r.cartesian(0));
        yCoords.add(r.cartesian(1));
        
        if (xCoords.size() >= 100){
            xCoords.remove(0);
        }
        if (yCoords.size() >= 100){
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
