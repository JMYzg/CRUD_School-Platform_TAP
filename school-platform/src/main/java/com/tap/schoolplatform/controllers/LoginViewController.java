package com.tap.schoolplatform.controllers;

//import javafx.event.*;
import javafx.scene.control.*;

public class LoginViewController {
    public TextField userIdTextField;
    public TextField userPasswordTextField;
    public CheckBox userRememberUserCheckBox;
    public Hyperlink userForgotPasswordHyperlink;
    public Button UserLoginButton;

    public void userLogin() {
        System.out.println("The user logged in");
    }
}
