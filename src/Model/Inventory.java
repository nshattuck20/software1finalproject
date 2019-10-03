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

    public static void setProductInventory(ObservableList<Product> productInventory) {
        Inventory.productInventory = productInventory;
    }

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
//********USE FOR DEBUGGING PURPOSES*****
//            partInventory.add(part);
//            System.out.println(partInventory.size());
//            for(int i = 0; i < partInventory.size(); i++){
//                Part Temp = partInventory.get(i);
//                Temp.toString();
//            }
    }


}
