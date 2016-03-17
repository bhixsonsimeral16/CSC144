
package spitfire;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Spitfire extends JFrame {

    public static final int FRAME_WIDTH = 768;
    public static final int FRAME_HEIGHT = 768;
    public static final String TITLE = "Spitfire";
    
    public Spitfire() {
        this.setSize( FRAME_WIDTH, FRAME_HEIGHT );
        this.setTitle( TITLE );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        
        Container pane = this.getContentPane();
        SpitfirePanel panel = new SpitfirePanel();
        Timer timer = new Timer( 40, panel );
        timer.start();
        pane.add( panel );
        
        this.setVisible( true  );
        
    } // Spitfire()
    
    public static void main(String[] args) {
        Spitfire spitfire = new Spitfire();
    } // main( String [] )
    
} // Spitfire
