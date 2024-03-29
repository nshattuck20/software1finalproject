package View_Controller;

import Model.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
    //Object to encapsulate part table data and return to the selection model
    private static Part tempPart;
    //The index position of the current selected object
    private static int tempPartIndex;
    //Object to encapsulate product table data and return to the selection model
    private static Product tempProduct;

    private static boolean isAdded;
    private static int tempProductIndex;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Set the columns for the parts table

        //Lamda expressions
        partIDColumn.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        partNameColumn.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        partPriceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        partInvColumn.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        // Set the columns for the products table
        productIDColumn.setCellValueFactory(cellData -> cellData.getValue().productIDProperty().asObject());
        productNameColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        productPriceColumn.setCellValueFactory(cellData -> cellData.getValue().productPriceProperty().asObject());
        productInvColumn.setCellValueFactory(cellData -> cellData.getValue().productInvProperty().asObject());

        /*
        Add some dummy data to enable easier testing.
         */
        if (!isAdded) {

            updateProductsTable(updateDummyPartData());
            isAdded = true;
        }
        partsTable.setItems(Inventory.getPartInventory());
        //ObservableList temp = Inventory.getProductInventory();
        productTable.setItems(Inventory.getProductInventory());

        /**
         * Enables the user to select multiple rows if applicable to user's
         * scenario.
         */
        partsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        productTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }
    /*
    This method searches the list of parts and returns
    the part the user is looking for.
     */

    @FXML
    private void searching(ActionEvent event) {
        String x = SearchPartText.getText();
        partsTable.getSelectionModel().select(Inventory.searchParts(x));

    }

    @FXML
    private void searchingProducts(ActionEvent event) {
        String x = productSearchText.getText();
        productTable.getSelectionModel().select(Inventory.searchProducts(x));

    }

    /**
     * This method will remove the selected row from the table
     */
    public void deletePartButtonPushed(ActionEvent event) throws IOException {
        ObservableList<Part> selectedRows, parts;
        parts = partsTable.getItems();

        //Get the rows that were selected
        selectedRows = partsTable.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Delete Part Confirmation");
        alert.setHeaderText("Delete part(s)?");
        Optional<ButtonType> confirm = alert.showAndWait();
        if (confirm.get() == ButtonType.OK) {

            for (Part part : selectedRows) {
                System.out.println("Deleting part " + part.getPartName());
                parts.remove(part);
                System.out.println("Size of parts inventory is " + Inventory.getPartInventory().size());

            }
            Parent modifyPartParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene modifyPartScene = new Scene(modifyPartParent);
            Stage modifyProductStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            modifyProductStage.setScene(modifyPartScene);
            modifyProductStage.show();
        }



    }

    //This method returns the index of the object in the Selection model
    public static int getTempPartIndex() {
        return tempPartIndex;
    }


    public static int getTempProductIndex() {
        return tempPartIndex;
    }

    @FXML
    public void handleExitClicked(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        Platform.exit();
        stage.close();
    }


    public void openAddPartScreen(ActionEvent event) throws IOException {
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
        tempProduct = productTable.getSelectionModel().getSelectedItem();
        //Store the index of the selected product
        tempProductIndex = Inventory.getPartInventory().indexOf(tempProduct);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifyProduct.fxml"));
        Parent modifyProductParent = loader.load();
        Scene modifyProductScene = new Scene(modifyProductParent);
        Stage modifyProductStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        modifyProductStage.setScene(modifyProductScene);
        modifyProductStage.show();

        //Access other controller and call a method initData
        ModifyProductScreenController controller = loader.getController();
        controller.initData(productTable.getSelectionModel().getSelectedItem());

    }

    //User clicks on modify button. Modify passes a part object
    //modify part view.
    @FXML
    void openModifyPartScreen(ActionEvent event) throws IOException {
        tempPart = partsTable.getSelectionModel().getSelectedItem();
        //Store the index of the selected part
        tempPartIndex = Inventory.getPartInventory().indexOf(tempPart);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifyPart.fxml"));
        Parent modifyPartParent = loader.load();
        Scene modifyPartScene = new Scene(modifyPartParent);
        Stage modifyPartStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        modifyPartStage.setScene(modifyPartScene);
        modifyPartStage.show();

        //Access othe controller and call a method
        ModifyPartScreenController controller = loader.getController();
        controller.initData(partsTable.getSelectionModel().getSelectedItem());
    }




    public ObservableList<Part> addPart(ObservableList<Part> part) {
        System.out.println("Size of addPart is " + part.size());
        return Inventory.setPartInventory(part);
    }

    public ObservableList<Product> updateProductTableData() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        Product product = new Product();
        System.out.println("The size of product list is " + products.size());
        return Inventory.getProductInventory();
    }

    /*
   This method loads some dummy test data to the products table.
    */
    public ObservableList<Product> updateProductsTable(ObservableList associatedParts) {
        ObservableList<Product> productsList = FXCollections.observableArrayList();
        Product product = new Product();
        product.setAllVariables(1, "Computer", 299, 20, 1, 5);
        product.setAssociatedParts(associatedParts);
        System.out.println("Associated part list is " + associatedParts.toString());

        productsList.add(product);
        Inventory.addAllProducts(productsList);
        System.out.println("The size of the product list is " + productsList.size());
        return productsList;

    }
    //This method returns some default data for easy testing


    //This method updates the tableview of parts with some test data.
    public ObservableList<Part> updateDummyPartData() {
        ObservableList<Part> dummyParts = FXCollections.observableArrayList();
        InHouse dummyPart = new InHouse();
        dummyPart.setPartID(102);
        dummyPart.setPartName("Hard Drive");
        dummyPart.setPrice(129.99);
        dummyPart.setPartInv(10);

        Outsourced dummyPart2 = new Outsourced();
        dummyPart2.setPartID(201);
        dummyPart2.setPartName("Power Supply ");
        dummyPart2.setPrice(29.99);
        dummyPart2.setPartInv(20);
        //Add the parts to the observable list


        InHouse dummyPart3 = new InHouse();
        dummyPart3.setPartName("Motherboard");
        dummyPart3.setPartInv(5);
        dummyPart3.setPrice(159.99);
        dummyPart3.setPartID(109);

        Outsourced dummyPart4 = new Outsourced();
        dummyPart4.setPartName("Vulcan 8GB RAM ");
        dummyPart4.setPartInv(30);
        dummyPart4.setPrice(59.99);
        dummyPart4.setPartID(202);

        dummyParts.add(dummyPart);
        dummyParts.add(dummyPart2);
        dummyParts.add(dummyPart3);
        dummyParts.add(dummyPart4);

        Inventory.addAll(dummyParts);
        updateProductsTable(dummyParts);
        return dummyParts;
    }

}