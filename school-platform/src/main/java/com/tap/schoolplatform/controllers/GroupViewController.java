package com.tap.schoolplatform.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GroupViewController {

    public Button cancelButton;

    public void cancelGroup() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
