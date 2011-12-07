/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javacus;

import java.awt.Dimension;


/**
 *
 * @author Pedro
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Abacus abacus = new Abacus(12, 4, 1);
        Console console = new Console(abacus);
        console.draw(abacus);

        int sizeX = 600;
        int sizeY = 300;
        Dimension dimension = new Dimension(sizeX, sizeY);

        //quick workaround to prevent the canvas from getting partially hiden
        Dimension dimensionOuter = new Dimension(sizeX + 5, sizeY + 26);

        MyFrame myFrame = new MyFrame();
        myFrame.setSize(dimensionOuter);
        myFrame.setTitle("Javacus");
        myFrame.setResizable(true);

        myCanvas myCanvas = new myCanvas(dimension, abacus);
        myFrame.add(myCanvas);

        myFrame.addKeyListener(myCanvas);       

        myFrame.setVisible(true);
        myFrame.pack();
        

    }
}
