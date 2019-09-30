package View_Controller;

import Model.InHouse;
import Model.Inventory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class ModifyPartScreenController {
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
    private TextField costTextField;

    @FXML
    private TextField minTextField;

    @FXML
    private TextField maxTextField;

    @FXML
    private TextField companyName;

    @FXML
    private TextField dynamicAddTxt;

    @FXML
    private Label dynamicAddLabel;

    private boolean outSourceClicked = false;

    public void inHouseRadioButtonPushed(ActionEvent event){
        outSourceClicked = false;
        dynamicAddLabel.setText("Machine ID");

    }
/*
When user clicks on exit button
 */
    @FXML
    public void handleExitButtonPressed(ActionEvent event){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        Platform.exit();
        stage.close();
    }
/*
When the save button is pressed, a new part is added to the list.
 */
    @FXML
    public void handleSaveButtonPushed(ActionEvent event){

//        InHouse newPart = new InHouse();
        //Get all items from table

    }



}

