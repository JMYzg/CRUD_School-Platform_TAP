package com.tap.schoolplatform.controllers;

import com.tap.schoolplatform.models.academic.Degree;
import com.tap.schoolplatform.models.academic.enums.Shift;
import com.tap.schoolplatform.services.users.AdministratorService;
import com.tap.schoolplatform.utils.SharedData;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class GroupViewController extends ViewController {

    public Button cancelButton;
    public ComboBox<Shift> shiftComboBox;
    public ComboBox<Degree> degreeComboBox;
    public ComboBox semesterComboBox;
    public Button addButton;
    public Button clearAllButton;
    SharedData sharedDataObject = SharedData.getInstance();
    AdministratorService adminUser;
    Degree degree;

    public void initialize() {
        refreshDegreeCB();
        //degree = degreeComboBox.getSelectionModel().getSelectedItem();
        shiftComboBox.getItems().setAll(Shift.values());
        shiftComboBox.setEditable(false);
        //.getItems().setAll(something);
        degreeComboBox.setEditable(false);
        //semesterComboBox.getItems().setAll(something);
        semesterComboBox.setEditable(false);
    }

    public void addGroup(ActionEvent event) {
        Degree selectedDegree = degreeComboBox.getValue();
        Shift selectedShift = shiftComboBox.getValue();
        if(/* ||  semesterComboBox.getItems().isEmpty() */shiftComboBox == null || degreeComboBox == null) {
            alert("Error", "Please make sure to full fill all the options boxes", Alert.AlertType.ERROR);
        }
        else {
            //int semester = Integer.parseInt(semesterComboBox.getSelectionModel().getSelectedItem().toString());
            adminUser = new AdministratorService(selectedDegree);
            adminUser.createGroup(1, selectedShift);
            alert("", "Group added correctly", Alert.AlertType.INFORMATION);
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        }
    }

    public void clearAll(MouseEvent event) {
        shiftComboBox.valueProperty().set(null);
        semesterComboBox.valueProperty().set(null);
        degreeComboBox.valueProperty().set(null);
    }

    public void cancelGroup() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void refreshDegreeCB () {
        degreeComboBox.getItems().setAll(sharedDataObject.getDegrees());
        degreeComboBox.setConverter(new StringConverter<Degree>() {
            @Override
            public String toString(Degree degree) {
                if(degree != null) return degree.getName();
                else return null;
            }

            @Override
            public Degree fromString(String s) {
                return null;
            }
        });
    }
}
