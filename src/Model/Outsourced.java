package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Outsourced extends Part {

    private  SimpleStringProperty companyName;



    public Outsourced(int partID, String partName, double partPrice, int numOfPartsInStock, int min, int max, int machineID) {
        super();
        this.companyName = new SimpleStringProperty();
    }
}
