package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class InHouse extends Part {

    private final IntegerProperty machineID;



    public InHouse() {
        super();
        this.machineID = new SimpleIntegerProperty();
    }



    public int getMachineID() {
        return this.machineID.get();
    }

    public IntegerProperty machineIDProperty() {
        return machineID;
    }

    public void setMachineID(int machineID) {
        this.machineID.set(machineID);
    }
}

