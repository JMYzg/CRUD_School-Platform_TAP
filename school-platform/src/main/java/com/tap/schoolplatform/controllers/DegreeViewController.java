package com.tap.schoolplatform.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class DegreeViewController {
    public TextField newDegreeTextField;
    public ComboBox degreeComboBox;
    public Button addButton;
    public Button clearAllButton;
    public Button cancelButton;

    public void addDegree(ActionEvent actionEvent) {
    }

    public void cancelDegree() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void clearNewDegree(ActionEvent actionEvent) {
    }
}
