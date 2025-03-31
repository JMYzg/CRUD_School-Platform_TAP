package com.tap.schoolplatform.controllers;

import com.tap.schoolplatform.models.academic.enums.Shift;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class GroupViewController {

    public Button cancelButton;
    public ComboBox shiftComboBox;
    public ComboBox degreeComboBox;
    public Button addButton;
    public Button clearAllButton;
    public ComboBox semesterComboBox;

    public void initialize() {
        shiftComboBox.getItems().setAll(Shift.values());
        shiftComboBox.setEditable(false);
        //.getItems().setAll(something);
        degreeComboBox.setEditable(false);
        //semesterComboBox.getItems().setAll(something);
        semesterComboBox.setEditable(false);

    }

    public void cancelGroup() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void addGroup(ActionEvent event) {
    }

    public void clearAll(ActionEvent event) {
    }
}
