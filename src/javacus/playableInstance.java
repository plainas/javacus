/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javacus;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Map;

/**
 *
 * @author Pedro
 *
 * General container where multiple parts of the source come together. This
 * class pipes stuff between multiple parts of the application: GUI, Graphics,
 * event handling, abacus state, etc.
 *
 */
public class playableInstance {
    Abacus abacus;
    Graphics graphics;
    Dimension dimension;
    Map<String, Object> properties;

    
    //TODO: move multiple stuff in here

}
