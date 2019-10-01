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
                alert.showAndWait();
                handleExceptionMessage = "";


            } else {
                if (outSourcedClicked = false) {
                    InHouse newPart = new InHouse();
                    newPart.setPartName(newPartName);
                    newPart.setPartID(Integer.parseInt(newPartID));
                    newPart.setPrice(Double.parseDouble(newPartPrice));
                    newPart.setPartInv(Integer.parseInt(newPartInventory));
                    newPart.setMin(Integer.parseInt(newPartMin));
                    newPart.setMax(Integer.parseInt(newPartMax));
                    newPart.setMachineID(Integer.parseInt(machineID));
                    Inventory.addPart(newPart);


                } else {
                    Outsourced newOutsourcedPart = new Outsourced();
                    newOutsourcedPart.setPartName(newPartName);
                    newOutsourcedPart.setPartID(Integer.parseInt(newPartID));
                    newOutsourcedPart.setPrice(Double.parseDouble(newPartPrice));
                    newOutsourcedPart.setPartInv(Integer.parseInt(newPartInventory));
                    newOutsourcedPart.setMin(Integer.parseInt(newPartMin));
                    newOutsourcedPart.setMax(Integer.parseInt(newPartMax));
                    newOutsourcedPart.setCompanyName(companyName);
                    Inventory.addPart(newOutsourcedPart);
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
            alert.setTitle("Error while adding new part");
            alert.setHeaderText("ERROR!");
            alert.setContentText("Your form contains blank fields. ");
            alert.showAndWait();

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inventoryToggleGroup = new ToggleGroup();
        this.inHouseRadioButton.setToggleGroup(inventoryToggleGroup);
        this.outSourcedRadioButton.setToggleGroup(inventoryToggleGroup);
    }

}
