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

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class ModifyPartScreenController implements Initializable {
    /*
    User clicks the exit button
     */
    @FXML
    private RadioButton inHouseRadioButton;

    @FXML
    private RadioButton outsourcedRadioButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button exitButton;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField invTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField minTextField;

    @FXML
    private TextField maxTextField;

    @FXML
    private TextField companyNameTxtField;

    @FXML
    private TextField switchTextField;

    @FXML
    private Label switchLabel;

    private ToggleGroup modifyToggleGroup;

    @FXML
    private boolean isOutSourcedClicked;
    private String handleExceptionMessage = new String();
    int tempPartIndex = MainScreenController.getTempPartIndex();
    private int partID;
    private String companyName;


    //private boolean outSourcedClicked = false;


    @FXML
    public void inHouseRadioButtonPushed(ActionEvent event) {
        System.out.println("InHouse selected");
        isOutSourcedClicked = false;
        switchLabel.setText("Machine ID");
    }

    @FXML
    public void outSourcedRadioButtonPushed(ActionEvent event) {
        System.out.println("Outsource Selected");
        isOutSourcedClicked = true;
        switchLabel.setText("Company Name");
    }


    /*
    When user clicks on exit button
     */
    @FXML
    public void handleExitButtonPressed(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Exit selected ");
        alert.setContentText("Are you sure you want to cancel modifying this part? ");

        Optional<ButtonType> confirm = alert.showAndWait();
        if (confirm.get() == ButtonType.OK) {
            Parent cancelParent = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
            Scene cancelmodifyPartScene = new Scene(cancelParent);
            Stage exitModifyPartStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            exitModifyPartStage.setScene(cancelmodifyPartScene);
            exitModifyPartStage.show();
        }
    }

    /*
    When the save button is pressed, a new part is added to the list.
     */
    @FXML
    public void handleSaveButtonPushed(ActionEvent event) {

        String newPartName = nameTextField.getText();
        String newPartID = idTextField.getText();
        String newPartPrice = priceTextField.getText();
        String newPartInventory = invTextField.getText();
        String newPartMin = minTextField.getText();
        String newPartMax = maxTextField.getText();
        String newPartMachineID = switchTextField.getText();

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
                    Parent modifyPartParent = FXMLLoader.load(getClass().getResource("ModifyPart.fxml"));
                    Scene modifyPartScene = new Scene(modifyPartParent);
                    Stage modifyPartStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    modifyPartStage.setScene(modifyPartScene);
                    modifyPartStage.show();
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
                    Inventory.modifyPart(tempPartIndex, newPart);
//                    Inventory inv = new Inventory();
//                    inv.addPart(newPart);

                } else {
                    if (outsourcedRadioButton.isSelected()) {
                        Outsourced newOutsourcedPart = new Outsourced();
                        newOutsourcedPart.setPartName(newPartName);
                        newOutsourcedPart.setPartID(Integer.parseInt(newPartID));
                        newOutsourcedPart.setPrice(Double.parseDouble(newPartPrice));
                        newOutsourcedPart.setPartInv(Integer.parseInt(newPartInventory));
                        newOutsourcedPart.setMin(Integer.parseInt(newPartMin));
                        newOutsourcedPart.setMax(Integer.parseInt(newPartMax));
                        newOutsourcedPart.setCompanyName(companyName);
//
                        Inventory.modifyPart(tempPartIndex, newOutsourcedPart);
                    }
                    //Neither inHouse or Outsourced selected
                    else {
                        if (!outsourcedRadioButton.isSelected() && !inHouseRadioButton.isSelected()) {
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
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            //alert.initOwner(initDialogPane);
            alert.setTitle("Error while adding new part");
            alert.setHeaderText("ERROR!");
            alert.setContentText("Your form contains blank fields. ");
            alert.showAndWait();

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        modifyToggleGroup = new ToggleGroup();
        this.inHouseRadioButton.setToggleGroup(modifyToggleGroup);
        this.outsourcedRadioButton.setToggleGroup(modifyToggleGroup);

        Part tempPart = Inventory.getPartInventory().get(tempPartIndex);
        partID = Inventory.getPartInventory().get(tempPartIndex).getPartID();
        idTextField.setText(String.valueOf(partID));
        nameTextField.setText(tempPart.getPartName());
        invTextField.setText(Integer.toString(tempPart.getPartInv()));
        priceTextField.setText(Double.toString(tempPart.getPrice()));
        minTextField.setText(Integer.toString(tempPart.getMin()));
        maxTextField.setText(Integer.toString(tempPart.getMax()));

        if (tempPart instanceof InHouse) {
            switchTextField.setText(Integer.toString(((InHouse) Inventory.getPartInventory().get(tempPartIndex)).getMachineID()));
            switchLabel.setText("Machine ID");
            inHouseRadioButton.setSelected(true);


        }

        if (tempPart instanceof Outsourced) {
            switchTextField.setText(((Outsourced) Inventory.getPartInventory().get(tempPartIndex)).getCompanyName());
            switchLabel.setText("Company Name");
            outsourcedRadioButton.setSelected(true);
        }
    }
}




