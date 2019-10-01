package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Product> productInventory = FXCollections.observableArrayList();
    private static ObservableList<Part> partInventory = FXCollections.observableArrayList();
//    private static int partIDCount = 0;
//    private static int productIDCount = 0;

    //Default constructor
    public Inventory(){

    }

    public static ObservableList<Product> getProductInventory() {
        return productInventory;
    }

//    public void setAssociatedParts(ObservableList<Part> P){
//        productInventory.addAll((Product) P);
//    }

    public static void setProductInventory(ObservableList<Product> productInventory) {
        Inventory.productInventory = productInventory;
    }

    public static ObservableList<Part> getPartInventory() {
        return partInventory;
    }

    public static void setPartInventory(ObservableList<Part> partInventory) {
        Inventory.partInventory = partInventory;
    }

    public static void addPart(Part part){
       partInventory.add(part);
    }



//    public static int getPartIDCount() {
//        return partIDCount;
//    }
//
//    public static void setPartIDCount(int partIDCount) {
//        Inventory.partIDCount = partIDCount;
//    }

//    public static int getProductIDCount() {
//        return productIDCount;
//    }
//
//    public static void setProductIDCount(int productIDCount) {
//        Inventory.productIDCount = productIDCount;
//    }
}
