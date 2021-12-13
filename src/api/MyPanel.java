package api;

//import gui.Point3D;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;


    public class MyPanel extends JPanel implements MouseListener {
        LinkedList<GeoLocation> points = new LinkedList<GeoLocation>();

        public MyPanel()
        {
            this.setBackground(Color.white);
            this.addMouseListener(this);
        }

        @Override
        public void paintComponent(Graphics g)
        {
         /*   super.paintComponent(g);

            GeoLocation prev = null;

            for (GeoLocation p : points)
            {
                g.setColor(Color.BLACK);
                g.fillOval((int) p.x() - 10, (int) p.y() - 10, 20, 20);

                if (prev != null) {
                    g.setColor(Color.GRAY);
                    g.drawLine((int) p.x(), (int) p.y(), (int) prev.x(), (int) prev.y());

                    g.drawString("some string", (int) ((p.x() + prev.x()) / 2), (int) ((p.y() + prev.y()) / 2)); //draw in the middle
                }

                prev = p;
            }
*/
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            GeoLocation p = new Geo_location(e.getX(), e.getY(),0);
            points.add(p);
            repaint();
            System.out.println("mouseClicked");
        }

        @Override
        public void mousePressed(MouseEvent e)
        {

            System.out.println("mousePressed");
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            System.out.println("mouseReleased");

        }

        @Override
        public void mouseEntered(MouseEvent e)
        {
            System.out.println("mouseEntered");

        }

        @Override
        public void mouseExited(MouseEvent e)
        {
            System.out.println("mouseExited");
        }
    }


