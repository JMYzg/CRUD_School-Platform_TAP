package com.tap.schoolplatform.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class LoginFirstTimeController {
    public Button UserLoginButton;
    public CheckBox userRememberUserCheckBox;
    public TextField userLoginFirstTimeTextField;
    public TextField userCreatePasswordFirstTimeTextField;
    public TextField userConfirmPasswordFirstTimeTextField;

    public void userLoginFT(ActionEvent actionEvent) {
        System.out.println("The user logged in for the first time");
    }
}
