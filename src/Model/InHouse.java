package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class InHouse extends Part {

    private final IntegerProperty machineID;

    public InHouse(int partID,  String partName, double partPrice, int numOfPartsInStock, int min, int max, int machineID) {
        super(partID, partName, partPrice, numOfPartsInStock, min, max);
        this.machineID = new SimpleIntegerProperty(machineID);
    }

    public int getMachineID() {
        return machineID.get();
    }

    public IntegerProperty machineIDProperty() {
        return machineID;
    }

    public void setMachineID(int machineID) {
        this.machineID.set(machineID);
    }
}
