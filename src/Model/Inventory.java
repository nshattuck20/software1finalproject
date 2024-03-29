package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Product> productInventory = FXCollections.observableArrayList();
    private static ObservableList<Part> partInventory = FXCollections.observableArrayList();
//    private static int partIDCount = 0;
//    private static int productIDCount = 0;

    //Default constructor
    public Inventory() {

    }

    public static ObservableList<Product> getProductInventory() {
        return productInventory;
    }

//    public void setAssociatedParts(ObservableList<Part> P){
//        productInventory.addAll((Product) P);
//    }


    public static ObservableList<Part> getPartInventory() {
        return partInventory;
    }

    public static ObservableList<Part> setPartInventory(ObservableList<Part> partInventory) {
        Inventory.partInventory = partInventory;
        return partInventory;
    }

    public static void addAll(ObservableList<Part> allParts) {

        partInventory.addAll(allParts);
        System.out.println("Size of addAll is " + partInventory.size());


    }


    public static void addAllProducts(ObservableList<Product> allProducts) {
        productInventory.addAll(allProducts);
    }

    public static void addPart(Part part) {
        partInventory.add(part);

    }
    /*
    This method searches through the list of parts and returns the part
    we are looking for.
     */

    public static Part searchParts(String lookPart) {
        for (Part p : partInventory) {
            if (p.getPartName().contains(lookPart)) return p;
        }
        return null;
    }

    public static Product searchProducts(String lookProduct) {
        for (Product p : productInventory) {
            if (p.getProductName().contains(lookProduct)) return p;
        }
        return null;
    }

    public static void modifyPart(int index, Part part) {
        partInventory.set(index, part);
    }


}
