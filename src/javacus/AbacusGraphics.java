
package javacus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Pedro
 */
public class AbacusGraphics{

    //needs to be static ???
    //todo wrap arguments in single object (playable instance will do)
    public static Graphics draw(Graphics graphics, Abacus abacus, Dimension dimension){
           
        graphics = drawHighlightColumn(graphics, abacus, dimension);
        graphics.setColor(new Color(80,22,0));
        graphics = drawBars(graphics, abacus, dimension);
        graphics = drawHbar(graphics, abacus, dimension);
        graphics = drawBeads(graphics, abacus, dimension);

        return graphics;
    }


    private static  Graphics drawBars(Graphics graphics, Abacus abacus, Dimension dimension){

        int numBars = abacus.getAbacusSize();
        int y1 = 0;
        int y2 = (int)dimension.getHeight();
        int thickness = 5;

        ArrayList<Integer> barPositions = getBarsPositions(graphics, abacus, dimension);

        for (Integer barPosition : barPositions) {
            int upperLeftCornerX = barPosition - thickness/2;
            graphics.fillRect(upperLeftCornerX, y1, thickness, y2);
        }
        
        return graphics;
    }

    private static Graphics drawHbar(Graphics graphics, Abacus abacus, Dimension dimension){
        int thickness = 10; //TODO: move this to properties
        int heigth = (int)dimension.getHeight();
        int hBarY = (heigth /(abacus.getLengthCarrier() + abacus.getLengthMain() + 2)) *(abacus.getLengthCarrier() +1) + thickness/2;
        
        int width = (int)dimension.getWidth();

        graphics.fillRect(0, hBarY - thickness/2, width , thickness);
        return graphics;
    }

    public static Graphics drawHelpTooltip(Graphics graphics, Dimension dimension){
        int width = 105;
        int height = 15;
        
        int x = (int)dimension.getWidth() - width;
        int y = (int)dimension.getHeight() - height;

        graphics.setColor(Color.WHITE);
        graphics.fillRect(x, y, width, height);


        String helpMsgString = "Press F1 for help";
        char[] helpMsg = helpMsgString.toCharArray();
        graphics.setColor(Color.BLACK);
        graphics.drawChars(helpMsg, 0, helpMsgString.length(), x, y+10);

        return graphics;
    }


    private static Graphics drawBeads(Graphics graphics, Abacus abacus, Dimension dimension){

        //TODO: move this settings away
        graphics.setColor(new Color(160,66,0));

        //TODO: Compute these values in a separate method
        int heigth = (int)dimension.getHeight();
        int width = (int)dimension.getWidth();
        int beadHigth = heigth /(abacus.getLengthCarrier() + abacus.getLengthMain() + 2);
        int columnWidth = width / abacus.getAbacusSize();

        ArrayList<HashMap<String,Integer>> positions = getBeadsPossiblePositions(graphics, abacus, dimension);

        for (int i = 0; i < positions.size(); i++) {
            AbacusCoordinates coordinates = abacus.convertCoordinatesLinearToNaturalAbacus(i);
            if (abacus.hasBead(coordinates)) {
                HashMap<String, Integer> hashPosition = positions.get(i);
                graphics.fillOval(hashPosition.get("x")+2, hashPosition.get("y"), columnWidth -4, beadHigth-1); //TODO: move spacing to properties
            }
        }
        return graphics;
    }

    private static Graphics drawHighlightColumn(Graphics graphics, Abacus abacus, Dimension dimension){
        //TODO: Continuar aqui!!!!

        //TODO: move this settings away
        graphics.setColor(new Color(0,200,0));

        ArrayList<Integer> barsPositions = getBarsPositions(graphics, abacus, dimension);

        int heigth = (int)dimension.getHeight();
        int width = (int)dimension.getWidth();
        int columnWidth = width / abacus.getAbacusSize();

        int barsPositionsArrayIndex = abacus.getAbacusSize() - abacus.getActiveColumnIndex() - 1;
        
        int xPosition = barsPositions.get(barsPositionsArrayIndex);
        xPosition = xPosition - columnWidth/2;

        graphics.fillRect(xPosition, 0, columnWidth, heigth);

        return graphics;
    }

    /**
     * Computes all possible positions where beads can be. Positions are ordered
     * as in the following graphical example:
     *
     * <pre>
     * +------+
     * | 0  6 |
     * | 1  7 |
     * +------+
     * | 2  8 |
     * | 3  . |
     * | 4  . |
     * | 5  . |
     * +------+
     * </pre>
     * @param graphics
     * @param abacus
     * @param dimension
     * @return An ArrayList Of HashMaps, each one containing two elements, 'x'
     * and 'y' which hold the coordinates of a bead.
     */
    private static ArrayList<HashMap<String,Integer>> getBeadsPossiblePositions(Graphics graphics, Abacus abacus, Dimension dimension){
        ArrayList<Integer> barsPositions = getBarsPositions(graphics, abacus, dimension);
        ArrayList<Integer> hPositions = new ArrayList<Integer>();
        ArrayList<HashMap<String,Integer>> beadsPossiblePositions = new ArrayList<HashMap<String,Integer>>();

        int heigth = (int)dimension.getHeight() -10;
        int width = (int)dimension.getWidth();
        int beadHigth = heigth /(abacus.getLengthCarrier() + abacus.getLengthMain() + 2);
        int columnWidth = width / abacus.getAbacusSize();

        int numBeadSlots = abacus.getLengthCarrier() + abacus.getLengthMain() + 2;

        for (int i = 0; i < numBeadSlots; i++) {
            int hPosition =  beadHigth/2 + beadHigth*i;
            hPositions.add(hPosition);
        }

        for (int barPosition : barsPositions) {
            for (int j=0; j < hPositions.size(); j++) {
                HashMap<String, Integer> posicao = new HashMap<String, Integer>();

                int upCornerX =  barPosition- columnWidth/2;
                int upCornerY = hPositions.get(j) - beadHigth/2;

                if(j>abacus.getLengthCarrier()){ upCornerY = upCornerY +10; }

                posicao.put("x", upCornerX);
                posicao.put("y", upCornerY);
                beadsPossiblePositions.add(posicao);
            }
        }
        return beadsPossiblePositions;
    }

    private static ArrayList getBarsPositions(Graphics graphics, Abacus abacus, Dimension dimension){
        ArrayList<Integer> barsPositions = new ArrayList<Integer>();

        int numBars = abacus.getAbacusSize();
        int width = (int)dimension.getWidth();
        int barSpacing = width / (numBars+1);
        for (int i = 1; i < numBars +1; i++) {
            int x = barSpacing * i;
            barsPositions.add(x);
        }
        return barsPositions;
    }


}
