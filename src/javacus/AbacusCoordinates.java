/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javacus;

/**
 * A coordinate system to identify a single bead in an abacus.
 * Created to keep abacus manipularion simple.
 * Graphics use a simple linear one-coordinate system wich is more suted
 * for that purpose.
 * Use convertCoordinatesLinearToNaturalAbacus(int linearPosition) to convert.
 *
 * <pre>
 *             Column 1
 *                |   Column 0
 *                |     |
 *                V     V
 *            ------------+
 *  Line 2        2     2 |
 *  Line 1        1     1 |     isMain = false
 *  Line 0        0     0 |
 *            ------------+
 *  Line 0        0     0 |
 *  Line 1        1     1 |
 *  Line 2        2     2 |     isMain = true
 *  Line 3        3     3 |
 *            ------------+
 *
 * </pre>
 *
 *
 * @author p
 */
public class AbacusCoordinates {
    int line;
    int column;
    boolean isMain;

    public AbacusCoordinates() {
    }

    public AbacusCoordinates(int line, int column, boolean isMain) {
        this.line = line;
        this.column = column;
        this.isMain = isMain;
    }

    @Override
    public String toString(){
        String stringIsMain = (isMain) ? "true" : "false";
        String retrunString = "line: " + line + "\n";
        retrunString += "clomun: " + column + "\n";
        retrunString += "isMain: " + stringIsMain;
        return retrunString;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isIsMain() {
        return isMain;
    }

    public void setIsMain(boolean isMain) {
        this.isMain = isMain;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

}
