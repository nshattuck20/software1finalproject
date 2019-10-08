package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyProductScreenController implements Initializable {

    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableColumn<Part, Integer> addPartIDColumn;

    @FXML
    private TableColumn<Part, String> addPartNameColumn;

    @FXML
    private TableColumn<Part, Integer> addPartInvColumn;

    @FXML
    private TableColumn<Part, Double> addPartPriceColumn;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button addProductButton;

    @FXML
    private TableView<Part> associatedPartsTable;

    @FXML
    private TableColumn<Part, Integer> assocPartIDColumn;

    @FXML
    private TableColumn<Part, String> assocPartNameColumn;

    @FXML
    private TableColumn<Part, Integer> assocPartInvColumn;

    @FXML
    private TableColumn<Part, Double> assocPartPriceColumn;

    @FXML
    private Button addProductDeleteButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;


    @FXML
    private TextField productNameTextField;

    @FXML
    private TextField productPriceTextField;

    @FXML
    private TextField productMaxTextField;

    @FXML
    private TextField productInvTextField;

    @FXML
    private TextField productMinTextField;

    @FXML
    private TextField productIDTextField;

    private Product selectedProduct;

    int tempProductIndex = MainScreenController.getTempProductIndex();


    public void initData(Product product) {


        selectedProduct = product;
        productIDTextField.setText(Integer.toString(selectedProduct.getProductID()));
        productNameTextField.setText(selectedProduct.getProductName());
        productPriceTextField.setText(Double.toString(selectedProduct.getProductPrice()));
        productInvTextField.setText(Integer.toString(selectedProduct.getProductInStock()));
        productMinTextField.setText(Integer.toString(selectedProduct.getProductMin()));
        productMaxTextField.setText(Integer.toString(selectedProduct.getProductMax()));
    }


    @FXML
    void handleCancelButtonPressed(ActionEvent event) throws IOException {


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Exit selected ");
        alert.setContentText("Are you sure you want to cancel modifying this part? ");

        Optional<ButtonType> confirm = alert.showAndWait();
        if (confirm.get() == ButtonType.OK) {
            Parent cancelParent = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
            Scene cancelModifyProductScene = new Scene(cancelParent);
            Stage exitModifyProductStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            exitModifyProductStage.setScene(cancelModifyProductScene);
            exitModifyProductStage.show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Populate the add parts table
        addPartIDColumn.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        addPartNameColumn.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        addPartPriceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        addPartInvColumn.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        // Set the columns for the associated parts table
        assocPartIDColumn.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        assocPartNameColumn.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        assocPartPriceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        assocPartInvColumn.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());

        partsTable.setItems(Inventory.getPartInventory());
        //associatedPartsTable.setItems(Inventory.);

    }
}
