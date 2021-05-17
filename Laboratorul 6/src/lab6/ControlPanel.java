/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 *
 * @author 40757
 */
public class ControlPanel extends JPanel {

    final MainFrame frame;
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");


    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        //change the default layout manager (just for fun)
        setLayout(new GridLayout(1, 4));
        //add all buttons ...TODO
        add(saveBtn);
        add(loadBtn);
        add(resetBtn);
        add(exitBtn);
        //configure listeners for all buttons
        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::load);
        resetBtn.addActionListener(this::reset);
        exitBtn.addActionListener(this::exit);
  
    }

    private void save(ActionEvent e) {
        try {
            
            ImageIO.write(frame.canvas.image, "PNG", new File("d:/test.png"));
            
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
    private void load(ActionEvent e) {
        try {
            
            JFileChooser chooser= new JFileChooser();
            int returnVal=chooser.showOpenDialog(frame);
            if(returnVal == JFileChooser.APPROVE_OPTION)
            {
            FileInputStream file=new FileInputStream(chooser.getSelectedFile().getAbsolutePath());
            ObjectInputStream in= new ObjectInputStream(file);
            file.close();
           
            }
            
        } catch (FileNotFoundException FileNotFoundException) {
            System.err.println(FileNotFoundException);
        }
        catch(IOException ex)
        {
          //  System.err.println(ex);
            ex.printStackTrace();
        }
        
    }
    
    private void reset(ActionEvent e) {
       frame.canvas.clear();
    }
    
    private void exit(ActionEvent e) {
        System.exit(0);
    }

}
