package spitfire;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class SpitfirePanel extends JPanel implements ActionListener {

    public static final Color BG_COLOR = new Color(180, 224, 248);
    private double nearness = 0.5;

    public SpitfirePanel() {
        this.setBackground(BG_COLOR);
    } // SpitfirePanel()

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        int w = this.getWidth();
        int h = this.getHeight();

        AffineTransform makeCloser = new AffineTransform();
        makeCloser.setToScale(nearness, nearness);

        AffineTransform scale = new AffineTransform();
        scale.setToScale(w / 2, h / 2);

        AffineTransform translate = new AffineTransform();
        translate.setToTranslation(1.0, 1.0);

        AffineTransform transform = new AffineTransform();
        transform.concatenate(makeCloser);
        transform.concatenate(scale);
        transform.concatenate(translate);

        Ellipse2D wing = new Ellipse2D.Double(-0.75, -0.25, 1.5, 0.05);
        Ellipse2D body = new Ellipse2D.Double(-0.125, -0.50, 0.25, 0.35);
        Ellipse2D rWing = new Ellipse2D.Double(-0.3, -0.40, 0.6, 0.05);
        Ellipse2D vWing = new Ellipse2D.Double(-0.01, -0.75, 0.02, 0.5);
        Ellipse2D canopy = new Ellipse2D.Double(-0.03, -0.525, 0.06, 0.06);

        List<Shape> shapes = new ArrayList<>();
        shapes.add(wing);
        shapes.add(body);
        shapes.add(rWing);
        shapes.add(vWing);
        shapes.add(canopy);

        g2D.setColor(Color.RED);
        for (Shape s : shapes) {
            Shape shape = transform.createTransformedShape(s);
            g2D.fill(shape);
        } // for

    } // paintComponent( Graphics )

    @Override
    public void actionPerformed(ActionEvent e) {
        this.nearness += 0.02;
        this.repaint();
    } // actionPerformed( ActionEvent )
} // Spitfire
