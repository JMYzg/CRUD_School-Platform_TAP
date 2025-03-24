package com.tap.schoolplatform.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AdministratorController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
