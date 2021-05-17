/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;

import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import javax.swing.JFrame;

/**
 *
 * @author 40757
 */
public class MainFrame extends JFrame {

    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;

    public MainFrame() {
        super("My Drawing Application");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

   
        canvas = new DrawingPanel(this);
        controlPanel = new ControlPanel(this);
        configPanel= new ConfigPanel(this);
        add(canvas, CENTER); //this is BorderLayout.CENTER
        add(controlPanel, SOUTH);
        add(configPanel, NORTH);

        //invoke the layout manager
        pack();
    }

}
