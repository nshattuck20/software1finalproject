package Model;

import View_Controller.AddPartScreenController;
import javafx.beans.property.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public abstract class Part {

    private IntegerProperty partID;
    private StringProperty partName;
    private DoubleProperty price;
    private IntegerProperty partInv;
    private IntegerProperty min;
    private IntegerProperty max;
    private IntegerProperty machineID;

    public Part() {
        System.out.println("Calling parent constructor");
        partID = new SimpleIntegerProperty();
        partName = new SimpleStringProperty();
        price = new SimpleDoubleProperty();
        partInv = new SimpleIntegerProperty();
        min = new SimpleIntegerProperty();
        max = new SimpleIntegerProperty();
        machineID = new SimpleIntegerProperty(0);
    }

    public int getPartID() {
        return partID.get();
    }

    public IntegerProperty partIDProperty() {
        return partID;
    }


    public void setPartID(int partID) {
        this.partID.set(partID);
    }

    public String getPartName() {
        return this.partName.get();
    }

    public StringProperty partNameProperty() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName.set(partName);
    }

    public double getPrice() {
        return this.price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    //Added a simple Double wrapper so price can be edited
    public void setPrice(double price) {
        this.price.set(price);
    }

    public int getPartInv() {
        return partInv.get();
    }

    public IntegerProperty partInvProperty() {
        return partInv;
    }

    public void setPartInv(int partInv) {
        this.partInv.set(partInv);
    }

    public int getMin() {
        return min.get();
    }

    public IntegerProperty minProperty() {
        return min;
    }

    /*Added simple integer wrapper to enable editing
    when user clicks modify button.
     */
    public void setMin(int min) {
        this.min.set(min);
    }

    public int getMax() {
        return max.get();
    }

    public IntegerProperty maxProperty() {
        return max;
    }

    /*Added simple integer wrapper to enable editing
    when user clicks modify button.
     */
    public void setMax(int max) {
        this.max.set(max);
    }
/*
Made for debugging purposes. Part was not showing in tableview
 */
//    public String toString(){
//
//        return "Returning " + partID.toString();
//    }

    public void setAllVariables(int ID, String name, double price, int inStock, int min, int max) {
        this.setPartName(name);
        this.setPartID(ID);
        this.setPartInv(inStock);
        this.setPrice(price);
        this.setMin(min);
        this.setMax(max);

    }

    public static String validateNewPart(String name, int min, int max, double price, int inv, String message) throws IOException {
        if (name.equals("")) {
            message = message + "Name field is empty!";
        }
        // TODO: Handle ID field.
        if (inv < 1) {
            message = message + "The in stock amount cannot be less than 1 ";
        }
        if (price <= 0) {
            message = message + "Price must be larger than $0 ";
        }
        if (max < min) {
            message = message + "The Max must be greater than or equal to the Min. ";
        }
        if (inv < min || inv > max) {
            message = message + "The inventory must be between the Min and Max values. ";

        }

        // if(Integer)machineID)
        return message;
    }
}
