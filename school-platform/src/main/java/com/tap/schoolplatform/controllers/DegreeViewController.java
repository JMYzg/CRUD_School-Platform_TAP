package com.tap.schoolplatform.controllers;

import com.tap.schoolplatform.models.academic.Degree;
import com.tap.schoolplatform.models.users.Administrator;
import com.tap.schoolplatform.services.academic.DegreeService;
import com.tap.schoolplatform.services.users.AdministratorService;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class DegreeViewController {
    public TextField newDegreeTextField;
    public ComboBox degreeComboBox;
    public Button addButton;
    public Button clearAllButton;
    public Button cancelButton;
    AdministratorService adminDegree;
    Degree degree;

    public void initialize() {
        degreeComboBox.setEditable(false);
        adminDegree = new AdministratorService(degree);
        degree = new Degree(newDegreeTextField.getText());
        //degreeComboBox.getItems().addAll(adminDegree.readDegree(degree.getName()));
    }

    public void addDegree(ActionEvent actionEvent) {
        if(newDegreeTextField.getText().isEmpty()) {
            alert("Please write a name for the degree.", Alert.AlertType.ERROR);
        }
        if(newDegreeTextField.getText().equals(degree.getName())) {
            alert("There is already a degree with this name, please write a non existing degree.", Alert.AlertType.ERROR);
        }
        adminDegree.createDegree(newDegreeTextField.getText());
        System.out.println(newDegreeTextField.getText());
        alert("Degree added correctly", Alert.AlertType.INFORMATION);
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }

    public void cancelDegree() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void clearNewDegree(ActionEvent actionEvent) {
        //newDegreeTextField.set
    }

    public void alert(String alertMsg, Alert.AlertType alertType) {
        Alert alertWindow = new Alert(alertType);
        alertWindow.setTitle("Error");
        alertWindow.setContentText(alertMsg);
        alertWindow.showAndWait();
    }

}
