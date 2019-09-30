package View_Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    @FXML
    private Button partSearchButton;

    @FXML
    private TextField SearchPartText;

    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableColumn<Part, Integer> partIDColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Integer> partInvColumn;

    @FXML
    private TableColumn<Part, Double> partPriceColumn;


    @FXML
    private Button modifyPartButton;

    @FXML
    private Button deletePartButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button searchProductButton;

    @FXML
    private TextField productSearchText;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, Integer> productIDColumn;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, Integer> productInvColumn;

    @FXML
    private TableColumn<Product, Double> productPriceColumn;

    @FXML
    private Button addProdButton;

    @FXML
    private Button modifyProdButton;

    @FXML
    private Button deleteProdButton;

    @FXML
    private MenuBar menuBar;
    private Part modifyPart;

    /*
    This method will allow the user to  click a cell and
    update the name, id, price, and inventory level of the selected
    part.
     */

    public void changePartNameCellEvent(TableColumn.CellEditEvent editedCell){
        modifyPart = partsTable.getSelectionModel().getSelectedItem();
        modifyPart.setPartName(editedCell.getNewValue().toString());
    }


//    @FXML
//    public void openModifyPartScreen(ActionEvent event) throws IOException {
////        modifyPart = partsTable.getSelectionModel().getSelectedItem();
////        modifyPart.setPartName(editedCell.getNewValue().toString());
//        Parent modifyPartScreenParent = FXMLLoader.load(getClass().getResource("ModifyPart.fxml"));
//        Scene modifyPartScreen = new Scene(modifyPartScreenParent);
//        Stage modifyPartStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        modifyPartStage.setScene(modifyPartScreen);
//        modifyPartStage.show();
//
//    }

    @FXML
    void openAddPartScreen(ActionEvent event) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("AddPartScreen.fxml"));
        Scene addPartScene = new Scene(addPartParent);
        Stage addPartStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addPartStage.setScene(addPartScene);
        addPartStage.show();
    }

    @FXML
    void openAddProductScreen(ActionEvent event) throws IOException {
        Parent addProductParent = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        Scene addProductScene = new Scene(addProductParent);
        Stage addPartStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addPartStage.setScene(addProductScene);
        addPartStage.show();
    }

    @FXML
    void openModifyProductScreen(ActionEvent event) throws IOException {
        Parent modifyProductParent = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));
        Scene modifyProductScene = new Scene(modifyProductParent);
        Stage modifyProductStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        modifyProductStage.setScene(modifyProductScene);
        modifyProductStage.show();
    }
    //User clicks on modify buttons
    @FXML
    void openModifyPartScreen(ActionEvent event) throws IOException {
        Parent modifyPartParent = FXMLLoader.load(getClass().getResource("ModifyPart.fxml"));
        Scene modifyPartScene = new Scene(modifyPartParent);
        Stage modifyPartStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        modifyPartStage.setScene(modifyPartScene);
        modifyPartStage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Set the columns for the parts table
        partIDColumn.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        partNameColumn.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        partPriceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        partInvColumn.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        //Set the columns for the products table
        productIDColumn.setCellValueFactory(cellData -> cellData.getValue().productIDProperty().asObject());
        productNameColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        productPriceColumn.setCellValueFactory(cellData -> cellData.getValue().productPriceProperty().asObject());
        productInvColumn.setCellValueFactory(cellData -> cellData.getValue().productInvProperty().asObject());

        //load some dummy data
        partsTable.setItems(updateTableData());
        productTable.setItems(updateProductsTable());

//        updateProductsTableView();
    }

    /*
    This method loads some test data
    to the products table.
     */
    public ObservableList<Part> updateTableData() {
        ObservableList<Part> parts = FXCollections.observableArrayList();
        InHouse part1 = new InHouse(1, "Part 1", 1.99, 10, 1, 20, 0);
        InHouse part2 = new InHouse(1, "Part 2", 4.99, 5, 1, 20, 0);
        Outsourced os1 = new Outsourced(1, "Outsourced 3", 3.99, 8, 1, 20, 1);
        Product computer = new Product();
        computer.setAllVariables(1, "Computer", 299.99, 20, 1, 3);
        //Inventory.setProductInventory(part1,part2);
        //Add the inhouse and outsourced parts to our list.
        parts.add(part1);
        parts.add(part2);
        parts.add(os1);
        return parts;

    }

    /*
    Load some dummy data to make sure the product table populates correctly.
     */
    public ObservableList<Product> updateProductsTable() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        Product gameConsole = new Product();
        gameConsole.setAllVariables(1, "Nintendo Switch", 299.99, 20, 1, 3);
        products.add(gameConsole);
        return products;
    }
}
