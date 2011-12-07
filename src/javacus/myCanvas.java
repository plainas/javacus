/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javacus;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Pedro
 */
public class myCanvas extends Canvas implements KeyListener {

    Abacus abacus;
    int xValue = 50;
    boolean appJustStarted = true;

    public myCanvas(Dimension dimension, Abacus abacus) {
        setSize(dimension);
        this.setAbacus(abacus);
        this.setBackground(new Color(255, 240, 30));

    }

    @Override
    public void paint(Graphics g) {
        g = AbacusGraphics.draw(g, abacus, getSize());
        if (appJustStarted) {
            g = AbacusGraphics.drawHelpTooltip(g, getSize());
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

        setAppJustStarted(false);
        int activeIndex = getAbacus().getActiveColumnIndex();

        if (e.getKeyCode() == KeyEvent.VK_F1){
           HelpDialog helpDialog = new HelpDialog(null, true);
           helpDialog.setVisible(true);
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT){
           getAbacus().moveActiveIndexLeft();
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
           getAbacus().moveActiveIndexRight();
        }

        else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            getAbacus().getColumns().get(activeIndex).pushMainBeadDown();
        }

        else if (e.getKeyCode() == KeyEvent.VK_UP){
            getAbacus().getColumns().get(activeIndex).pushMainBeadUp();
        }
        else if (e.getKeyCode() == KeyEvent.VK_A){
            getAbacus().getColumns().get(activeIndex).pushCarrierBeadUp();
        }
        else if (e.getKeyCode() == KeyEvent.VK_Z){
            getAbacus().getColumns().get(activeIndex).pushCarrierBeadDown();
        }
        else if(e.getKeyCode() == KeyEvent.VK_C){
            getAbacus().resetAbacus();
        }

        repaint();

    }

    public void keyReleased(KeyEvent e) {
    }


    public void setXvalue(int xValue) {
        this.xValue = xValue;
    }

    public int getXvalue() {
        return xValue;
    }

    public Abacus getAbacus() {
        return abacus;
    }

    public void setAbacus(Abacus abacus) {
        this.abacus = abacus;
    }

        public boolean isAppJustStarted() {
        return appJustStarted;
    }

    public void setAppJustStarted(boolean appJustStarted) {
        this.appJustStarted = appJustStarted;
    }
}
