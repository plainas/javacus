package javacus;

/**
 *
 * @author Pedro
 */
public class Console {

    Abacus abacus;

    public Console(Abacus abacus) {
        this.abacus = abacus;
    }

    /**
     * Draws an horizontal line based on abacus size. Used only internaly in
     * text mode operation.
     * @param isTopOrBottom
     */
    private String drawLine(boolean isTopOrBottom){
        String tips;
        tips = isTopOrBottom ? "+" : "|";
        String line = tips + "-";
        for (int i = 0; i < abacus.getAbacusSize(); i++) {
            line += "--";
        }
        line += tips + "\n";
        return line;
    }

    /**
     * Main drawing routine. Outputs the abacus in its current state in text
     * mode.
     * @param abacus
     */
    public void draw(Abacus abacus) {
        String abacusString = "";

        //top bar
        abacusString += drawLine(true);

        //carrier (top) abacus' section of beans
        for (int j = abacus.getLengthCarrier(); j >= 0; j--) {
            abacusString += "| ";
            for (int i = abacus.getAbacusSize()-1; i >=0; i--) {
                String beadOrBar = abacus.hasBead(j, i, false) ? "*" : "|";
                abacusString += beadOrBar + " ";
            }
            abacusString += "|\n";
        }

        //middle bar
        abacusString += drawLine(false);

        //main (lower) abacus' section of beans
        for (int j = 0; j <= abacus.getLengthMain(); j++) {
            abacusString += "| ";
            for (int i = abacus.getAbacusSize()-1; i >= 0 ; i--) {
                String beadOrBar = abacus.hasBead(j, i, true) ? "*" : "|";
                abacusString += beadOrBar + " ";
            }
            abacusString += "|\n";
        }

        //bottom bar
        abacusString += drawLine(true);
        
        //output the abacus
        System.out.print(abacusString);
    }
}
