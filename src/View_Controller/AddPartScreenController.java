package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class AddPartScreenController implements Initializable {

    @FXML
    private RadioButton inHouseRadioButton;

    @FXML
    private RadioButton outSourcedRadioButton;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField invTextField;

    @FXML
    private Label switchLabel;

    @FXML
    private TextField minTextField;

    @FXML
    private TextField maxTextField;

    @FXML
    private TextField switchText;

    private ToggleGroup inventoryToggleGroup;

    private boolean outSourcedClicked = false;
    private String handleExceptionMessage = new String();
    private String machineID;
    private String companyName;


    public AddPartScreenController() {
    }


    @FXML
    public void inHouseRadioButtonPushed(ActionEvent event) {
        System.out.println("InHouse selected");
        outSourcedClicked = false;
        switchLabel.setText("Machine ID");
    }

    @FXML
    public void outSourcedRadioButtonPushed(ActionEvent event) {
        System.out.println("Outsource Selected");
        outSourcedClicked = true;
        switchLabel.setText("Company Name");
    }

    @FXML
    public void saveButtonPushed(ActionEvent event) throws IOException {
        String newPartName = nameTextField.getText();
        String newPartID = idTextField.getText();
        String newPartPrice = priceTextField.getText();
        String newPartInventory = invTextField.getText();
        String newPartMin = minTextField.getText();
        String newPartMax = maxTextField.getText();
        String newPartMachineID = switchText.getText();

        try {
            handleExceptionMessage =
                    Part.validateNewPart(newPartName, Integer.parseInt(newPartMin), Integer.parseInt(newPartMax),
                            Double.parseDouble(newPartPrice), Integer.parseInt(newPartInventory),
                            handleExceptionMessage);
            if (handleExceptionMessage.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error while adding new part");
                alert.setHeaderText("ERROR!");
                alert.setContentText(handleExceptionMessage);
                //  alert.showAndWait();
                Optional<ButtonType> confirm = alert.showAndWait();
                if (confirm.get() == ButtonType.OK) {
                    Parent modifyPartParent = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
                    Scene modifyPartScene = new Scene(modifyPartParent);
                    Stage modifyProductStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    modifyProductStage.setScene(modifyPartScene);
                    modifyProductStage.show();
                }
                handleExceptionMessage = "";


            } else {
                if (inHouseRadioButton.isSelected()) {
                    InHouse newPart = new InHouse();
                    newPart.setPartName(newPartName);
                    newPart.setPartID(Integer.parseInt(newPartID));
                    newPart.setPrice(Double.parseDouble(newPartPrice));
                    newPart.setPartInv(Integer.parseInt(newPartInventory));
                    newPart.setMin(Integer.parseInt(newPartMin));
                    newPart.setMax(Integer.parseInt(newPartMax));
                    newPart.setMachineID(Integer.parseInt(newPartMachineID));
                    Inventory.addPart(newPart);
//                    Inventory inv = new Inventory();
//                    inv.addPart(newPart);

                } else {
                    if (outSourcedRadioButton.isSelected()) {
                        Outsourced newOutsourcedPart = new Outsourced();
                        newOutsourcedPart.setPartName(newPartName);
                        newOutsourcedPart.setPartID(Integer.parseInt(newPartID));
                        newOutsourcedPart.setPrice(Double.parseDouble(newPartPrice));
                        newOutsourcedPart.setPartInv(Integer.parseInt(newPartInventory));
                        newOutsourcedPart.setMin(Integer.parseInt(newPartMin));
                        newOutsourcedPart.setMax(Integer.parseInt(newPartMax));
                        newOutsourcedPart.setCompanyName(companyName);
//                        Inventory inv = new Inventory();
                        Inventory.addPart(newOutsourcedPart);
                    }
                    //Neither inHouse or Outsourced selected
                    else {
                        if (!outSourcedRadioButton.isSelected() && !inHouseRadioButton.isSelected()) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText("Part not identified");
                            alert.setContentText("Please specify whether the part is In-House or" +
                                    "Outsourced");
                            alert.showAndWait();

                        }
                    }
                }

            }

            Parent saveNewPart = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene scene = new Scene(saveNewPart);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            //alert.initOwner(initDialogPane);
            alert.setTitle("Error while adding new part");
            alert.setHeaderText("ERROR!");
            alert.setContentText("Your form contains blank fields. ");
            alert.showAndWait();

        }

    }

    @FXML
    public void handleCancelButton(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Add part canceled ");
        alert.setContentText("Cancel adding new part? ");

        Optional<ButtonType> confirm = alert.showAndWait();
        if (confirm.get() == ButtonType.OK) {
            Parent cancelParent = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
            Scene cancelAddPartScene = new Scene(cancelParent);
            Stage cancelAddPartStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            cancelAddPartStage.setScene(cancelAddPartScene);
            cancelAddPartStage.show();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inventoryToggleGroup = new ToggleGroup();
        this.inHouseRadioButton.setToggleGroup(inventoryToggleGroup);
        this.outSourcedRadioButton.setToggleGroup(inventoryToggleGroup);
    }

}
