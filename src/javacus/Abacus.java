package javacus;

import java.util.ArrayList;

/**
 *
 * @author Pedro
 */
public class Abacus {
    private int abacusSize;
    private int lengthMain;
    private int lengthCarrier;
    private int activeColumnIndex;
    ArrayList<Column> columns = new ArrayList<Column>();

    public Abacus() {
    }

    public Abacus(int abacusSize, int lengthMain, int lengthCarrier) {
        this.abacusSize = abacusSize;
        this.lengthMain = lengthMain;
        this.lengthCarrier = lengthCarrier;
        this.activeColumnIndex = 0;
        for (int i = 0; i < this.abacusSize; i++) {
           Column column = new Column(this.lengthMain, this.lengthCarrier);
           column.setStateMain(0);
           column.setStateCarrier(0);
           columns.add(column);
        }
    }

    /**
     * Moves the column we are playing around with to the left
     * @return true, false if we are already at the lefttmost column
     */
    public boolean  moveActiveIndexLeft(){
        if (getActiveColumnIndex() >= getAbacusSize() - 1) return false;
        setActiveColumnIndex(activeColumnIndex + 1);
        return true;
    }

    /**
     * Moves the column we are playing around with to the right.
     * @return true. false if we are already at the rightmost column
     */
    public boolean moveActiveIndexRight(){
        if (getActiveColumnIndex() <= 0) return false;
        setActiveColumnIndex(getActiveColumnIndex()-1);
        return true;
    }

    /**
     * Checks if position given by (line,column, isMain) has a bead or not.
     * See figure for details about the coordinate system.
     *
     * @param line
     * @param column the column where the bead is or not. Zero is the rightmost
     * column
     * @param isMain true if we want to check for a bead in the lower part of
     * the abacus, false when the bead we're looking for is in the carrier section
     * @return true if it has a bead in the given spot, false if it doesn't
     */
    public boolean hasBead(int line, int column, boolean isMain ){
        boolean returnValue;
        int state;
        if (isMain) {
            state = this.getColumns().get(column).getStateMain();
        }else{
            state = this.getColumns().get(column).getStateCarrier();
        }
        returnValue = (state != line) ? true : false;
        return returnValue;
    }

    /**
     * Checks if position given by <i>abacusCoordinates</i> has a bead or not.
     *
     * @param abacusCoordinates
     * @return
     */
    public boolean hasBead(AbacusCoordinates abacusCoordinates){
        return hasBead(abacusCoordinates.getLine(), abacusCoordinates.getColumn(), abacusCoordinates.isIsMain());
    }


    /**
     * Converts from linear (int) to AbacusCoordinates
     *
     * @param linearPosition position in linear coordinates
     * @return
     */
    public AbacusCoordinates convertCoordinatesLinearToNaturalAbacus(int linearPosition){
        boolean isMain = false;
        int column;
        int line;
        int columnSize = this.getLengthMain() + this.getLengthCarrier() +2;

        // TODO: put the coordinates transformation into an hashmap and store it once abacus is initialized

        if( linearPosition%columnSize > this.getLengthCarrier()){
            isMain = true;
        }

        column = this.getAbacusSize() - 1 - (linearPosition/columnSize);

        if(isMain){
            line = (linearPosition % columnSize ) - this.getLengthCarrier() - 1;
        }else{
            line = this.getLengthCarrier() - (linearPosition % columnSize);
        }

        AbacusCoordinates abacusCoordinates = new AbacusCoordinates(line, column, isMain);

        return abacusCoordinates;
    }

    public void resetAbacus(){
        for (Column column : columns) {
            column.resetColum();
        }
    }



    public int getAbacusSize() {
        return abacusSize;
    }

    public void setAbacusSize(int abacusSize) {
        this.abacusSize = abacusSize;
    }

    public ArrayList<Column> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<Column> columns) {
        this.columns = columns;
    }

    public int getLengthCarrier() {
        return lengthCarrier;
    }

    public void setLengthCarrier(int lengthCarrier) {
        this.lengthCarrier = lengthCarrier;
    }

    public int getLengthMain() {
        return lengthMain;
    }

    public void setLengthMain(int lengthMain) {
        this.lengthMain = lengthMain;
    }

    public int getActiveColumnIndex() {
        return activeColumnIndex;
    }

    public void setActiveColumnIndex(int activeIndex) {
        this.activeColumnIndex = activeIndex;
    }

}