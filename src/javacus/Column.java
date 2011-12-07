package javacus;

/**
 *
 * @author Pedro
 */
public class Column {

    private int lengthMain;
    private int lengthCarrier;
    private int stateMain;
    private int stateCarrier;
    private int abacusSize;

    public Column() {
    }

    public Column(int lengthMain, int lengthCarrier) {
        this.lengthMain = lengthMain;
        this.lengthCarrier = lengthCarrier;
        this.stateCarrier = 0;
        this.stateMain = 0;
    }

    public int getAbacusSize() {
        return abacusSize;
    }

    public void setAbacusSize(int abacusSize) {
        this.abacusSize = abacusSize;
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

    public int getStateCarrier() {
        return stateCarrier;
    }

    public void setStateCarrier(int stateCarrier) {
        this.stateCarrier = stateCarrier;
    }

    public int getStateMain() {
        return stateMain;
    }

    public void setStateMain(int stateMain) {
        this.stateMain = stateMain;
    }

    public boolean pushMainBeadUp(){
       if (getStateMain() >= getLengthMain())  return false;
       setStateMain(getStateMain()+1);
       return true;
    }

    public boolean pushMainBeadDown(){
        if (getStateMain() <=0) return false;
        setStateMain(getStateMain()-1);
        return true;
    }

    public boolean pushCarrierBeadUp(){
        if (getStateCarrier()<=0 ) return false;
        setStateCarrier(getStateCarrier()-1);
        return true;
    }

    public boolean pushCarrierBeadDown(){
        if (getStateCarrier() >= getLengthCarrier())  return false;
        setStateCarrier(getStateCarrier()+1);
        return true;
    }

    public void resetColum(){
        setStateMain(0);
        setStateCarrier(0);
    }

}
