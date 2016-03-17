
package simple;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

public class SimplePanel extends JPanel {
    private static final Color BG_COLOR = new Color(180, 224, 192);
    private static final Color FG_COLOR = new Color( 16, 64, 96);
    
    public SimplePanel() {
        this.setBackground(BG_COLOR);
    } // SimplePanel()
    
    @Override
    public void paintComponent( Graphics g ) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        
        // Get dimensions of drawing area.
        int w = this.getWidth();
        int h = this.getHeight();
        
        // Construct transform to scale and translate shapes.
        // Shapes are defined in a square whose upper left
        // corner is at (-1,-1) and whose lower right corner
        // is at (1,1).
        // The transform scales and moves proportionally to
        // the actual size of the drawing area.
        AffineTransform scale = new AffineTransform();
        scale.setToScale(w/2, h/2);
        
        AffineTransform translate = new AffineTransform();
        translate.setToTranslation(1.0, 1.0);
        
        AffineTransform transform = new AffineTransform();
        transform.concatenate(scale);
        transform.concatenate(translate);
        
        // Define and draw a rectangle that is whose center
        // is at the center of the space and whose width and height
        // are, respectively, half of the space's width and height.
        Rectangle2D r = new Rectangle2D.Double( -0.5, -0.5, 1.0, 1.0);
        
        g2D.setColor(FG_COLOR);
        g2D.draw(transform.createTransformedShape(r)); 
        
        // Construct and draw an array of circles and stars.
        int numberOfRows = 4;
        int numberOfColumns = 4;
        
        double minX = -0.5;
        double maxX = +0.5;
        double deltaX = (maxX - minX)/numberOfColumns;
        
        double minY = -0.5;
        double maxY = +0.5;
        double deltaY = (maxY - minY)/numberOfRows;
        
        for( double x = minX; x < maxX; x += deltaX) {
            for( double y = minY; y < maxY; y += deltaY ) {
                Shape s = circle( x, y, 0.1);
                g2D.setColor(FG_COLOR);
                g2D.fill( transform.createTransformedShape(s));
                s = star(x, y, 0.1);
                g2D.setColor(Color.WHITE);
                g2D.fill( transform.createTransformedShape(s));
            } // for
        } // for
    } // paintComponent( Graphics )
    
    // make a circle
    private Shape circle( double centerX, double centerY, double radius ) {
        double ulx = centerX - radius;
        double uly = centerY - radius;
        double diameter = 2 * radius;
        Ellipse2D circle = new Ellipse2D.Double(ulx, uly, diameter, diameter);
        return circle;
    } // circle( double, double, double )
    
    // make a 5-pointed star
    private Shape star( double centerX, double centerY, double radius ) {
        GeneralPath path = new GeneralPath();
        double angle = Math.PI/2;
        double x = centerX + radius * Math.cos(angle);
        double y = centerY + radius * Math.sin(angle);
        path.moveTo(x, y);
        double angularIncrement = 2.0 * Math.PI/10;
        for( int i = 0; i <= 10; i++ ) {
            angle += angularIncrement;
            if( i % 2 == 0 ) {
                x = centerX + radius * Math.cos(angle);
                y = centerY + radius * Math.sin(angle);
            } // if
            else {
                x = centerX + 0.5 * radius * Math.cos(angle);
                y = centerY + 0.5 * radius * Math.sin(angle);
            } // else
            path.lineTo(x, y);
        } // for
        path.closePath();
        return path;
    } // star( double, double, double )
} // SimplePanel
