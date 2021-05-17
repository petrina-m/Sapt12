/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author 40757
 */
public class DrawingPanel extends JPanel {

    final MainFrame frame;
    final static int W = 800, H = 600;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the "tools" needed to draw in the image
    List<ConfiguredShape> shapes = new ArrayList<>();

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    private void init() {
        setPreferredSize(new Dimension(W, H)); //don’t use setSize. Why?
        setBorder(BorderFactory.createEtchedBorder()); //for fun
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (((String) frame.configPanel.actionCombo.getSelectedItem()).equals("Add")) {
                    drawShape(e.getX(), e.getY());
                } else if (((String) frame.configPanel.actionCombo.getSelectedItem()).equals("Remove")) {
                    deleteShape(e.getX(), e.getY());
                }
                
                repaint();
            } //Can’t use lambdas, JavaFX does a better job in these cases
            
            
        });
       
//  try{  frame.configPanel.commandButton.addActionListener(new ActionListener()
//     {
//         @Override
//         public void actionPerformed(ActionEvent e){  
//         
//                   String s=frame.configPanel.textLabel.getText();
//                   System.out.println(s);
//         }
//     });
//   }
//  catch( Exception e)
//  {
//      System.out.println("wg");
//  }
    }

    private void drawShape(int x, int y) {
        int radius = (int) (Math.random() * 50 + 5);
        int sides = (int) frame.configPanel.sidesField.getValue();
        Color color;

        if (((String) frame.configPanel.colorCombo.getSelectedItem()).equals("Black")) {
            color = Color.black;
        } else {
            Random rand = new Random();
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            color = new Color(r, g, b);
        }

        graphics.setColor(color);
        Shape newShape;
        if (((String) frame.configPanel.shapeCombo.getSelectedItem()).equals("Circle")) {
            newShape = new NodeShape(x, y, radius);
        } else {
            newShape = new RegularPolygon(x, y, radius, sides);
        }

        graphics.fill(newShape);
        shapes.add(new ConfiguredShape(newShape, color));

    }
  

    private void deleteShape(int x, int y) {
        int length = shapes.size();
        int id;
        for (id = length - 1; id >= 0; id--) {
            if (shapes.get(id).shape.contains(x, y)) {
                shapes.remove(id);
                drawAll();
                return;
            }
        }

    }

    private void drawAll() {
        clear();
        int i;
        for (i = 0; i < shapes.size(); i++) {
            graphics.setColor(shapes.get(i).color);
            graphics.fill(shapes.get(i).shape);
        }
        repaint();
    }

    @Override
    public void update(Graphics g) {
    } //Why did I do that?

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE); //fill the image with white
        graphics.fillRect(0, 0, W, H);
    }

    public void clear() {
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, W, H);
        repaint();
    }

}
