package Model;

import javafx.beans.property.*;

public abstract class Part {

    private  IntegerProperty partID;
    private  StringProperty partName;
    private  DoubleProperty price;
    private  IntegerProperty partInv;
    private  IntegerProperty min;
    private  IntegerProperty max;

    public Part() {
         partID = new SimpleIntegerProperty();
         partName = new SimpleStringProperty();
         price = new SimpleDoubleProperty();
         partInv =new SimpleIntegerProperty();
         min = new SimpleIntegerProperty();
         max = new SimpleIntegerProperty();
//         machineID = new SimpleIntegerProperty(0);
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


    //// Constructor
//    public Part(int partId, String partName, double price, int inStock, int min, int max) {
//        this.partID = new SimpleIntegerProperty(partId);
//        this.partName = new SimpleStringProperty(partName);
//        this.price = new SimpleDoubleProperty(price);
//        this.partInv = new SimpleIntegerProperty(inStock);
//        this.min = new SimpleIntegerProperty(min);
//        this.max = new SimpleIntegerProperty(max);
//    }




    //// Validator to make sure that the part added is a valid entry
    public static String isPartValid(String name, int min, int max, int inv, double price, String errorMessage){
        if (name == null) {
            errorMessage = errorMessage + "The name field is required. ";
        }
        if (inv < 1) {
            errorMessage = errorMessage + "The inventory count cannot be less than 1. ";
        }
        if (price <= 0) {
            errorMessage = errorMessage + "The price must be greater than $0. ";
        }
        if (max < min) {
            errorMessage = errorMessage + "The Max must be greater than or equal to the Min. ";
        }
        if (inv < min || inv > max) {
            errorMessage = errorMessage + "The inventory must be between the Min and Max values. ";
        }
        return errorMessage;
    }
}
