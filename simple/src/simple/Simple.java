package simple;

import java.awt.Container;
import javax.swing.JFrame;

public class Simple extends JFrame {

    private static final int SIMPLE_WIDTH = 512;
    private static final int SIMPLE_HEIGHT = 512;
    private static final String SIMPLE_TITLE = "Simple";

    public Simple() {
        this.setSize(SIMPLE_WIDTH, SIMPLE_HEIGHT);
        this.setTitle(SIMPLE_TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = this.getContentPane();
        pane.add(new SimplePanel());
        this.setVisible(true);
    } // Simple()

    public static void main(String[] args) {
        Simple simple = new Simple();
    } // main( String [] )

} // Simple
