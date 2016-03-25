/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbody;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Brian
 */
public class VectorTest {
    
    public VectorTest() {
    }

    /**
     * Test of length method, of class Vector.
     */
    @Test
    public void testLength() {
        System.out.println("length");
        Vector instance = new Vector(2);
        int expResult = 2;
        int result = instance.length();
        assertEquals(expResult, result);
    }

    /**
     * Test of dot method, of class Vector.
     */
    @Test
    public void testDot() {
        System.out.println("dot");
        double[] a = {2,1};
        double[] b = {2,2};
        Vector that = new Vector(a);
        Vector instance = new Vector(b);
        double expResult = 6.0;
        double result = instance.dot(that);
        assertEquals(expResult, result, 1e-4);
    }

    /**
     * Test of magnitude method, of class Vector.
     */
    @Test
    public void testMagnitude() {
        System.out.println("magnitude");
        double[] a = {3,4};
        Vector instance = new Vector(a);
        double expResult = 5.0;
        double result = instance.magnitude();
        assertEquals(expResult, result, 1e-4);
    }

    /**
     * Test of distanceTo method, of class Vector.
     */
    @Test
    public void testDistanceTo() {
        System.out.println("distanceTo");
        double[] a = {-2,-2};
        double[] b = {2,1};
        Vector that = new Vector(a);
        Vector instance = new Vector(b);
        double expResult = 5.0;
        double result = instance.distanceTo(that);
        assertEquals(expResult, result, 1e-4);
    }

    /**
     * Test of plus method, of class Vector.
     */
    @Test
    public void testPlus() {
        System.out.println("plus");
        double[] a = {1,1};
        double[] b = {3,4};
        double[] c = {4,5};
        Vector that = new Vector(a);
        Vector instance = new Vector(b);
        Vector expResult = new Vector(c);
        Vector result = instance.plus(that);
        assertEquals(expResult.cartesian(0), result.cartesian(0), 1e-4);
        assertEquals(expResult.cartesian(1), result.cartesian(1), 1e-4);
    }

    /**
     * Test of minus method, of class Vector.
     */
    @Test
    public void testMinus() {
        System.out.println("minus");
        double[] a = {1,1};
        double[] b = {3,4};
        double[] c = {2.0,3.0};
        Vector that = new Vector(a);
        Vector instance = new Vector(b);
        Vector expResult = new Vector(c);
        Vector result = instance.minus(that);
        assertEquals(expResult.cartesian(0), result.cartesian(0), 1e-4);
        assertEquals(expResult.cartesian(1), result.cartesian(1), 1e-4);
    }

    /**
     * Test of cartesian method, of class Vector.
     */
    @Test
    public void testCartesian() {
        System.out.println("cartesian");
        int i = 0;
        double[] a = {4,5};
        Vector instance = new Vector(a);
        double expResult = 4.0;
        double result = instance.cartesian(i);
        assertEquals(expResult, result, 1e-4);
    }

    /**
     * Test of times method, of class Vector.
     */
    @Test
    public void testTimes() {
        System.out.println("times");
        double factor = 2;
        double[] a = {2,3};
        double[] b = {4,6};
        Vector instance = new Vector(a);
        Vector expResult = new Vector(b);
        Vector result = instance.times(factor);
        double x1 = expResult.cartesian(0);
        double x2 = result.cartesian(0);
        double y1 = expResult.cartesian(1);
        double y2 = result.cartesian(1);
        assertEquals(x1, x2, 1e-4);
        assertEquals(y1, y2, 1e-4);
    }

    /**
     * Test of direction method, of class Vector.
     */
    @Test
    public void testDirection() {
        System.out.println("direction");
        double[] a = {3,4};
        double[] b = {0.6, 0.8};
        Vector instance = new Vector(a);
        Vector expResult = new Vector(b);
        Vector result = instance.direction();
        assertEquals(expResult.cartesian(0), result.cartesian(0), 1e-4);
        assertEquals(expResult.cartesian(1), result.cartesian(1), 1e-4);
    }

    /**
     * Test of toString method, of class Vector.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        double[] a = {5,-6};
        Vector instance = new Vector(a);
        String expResult = "(5.0, -6.0)";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of main method, of class Vector.
     */
    /*
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Vector.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
}
