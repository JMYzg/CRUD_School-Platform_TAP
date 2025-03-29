package com.tap.schoolplatform.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

import java.io.IOException;
import java.util.Objects;

public class LoginViewController {
    public TextField userId;
    public TextField userPassword;
    public CheckBox userRememberUserCheckBox;
    public Hyperlink userForgotPasswordHyperlink;
    public Button userLoginButton;

    //public SharedData getUser;

    public void userLogin() throws IOException {
        toAdminView();
            System.out.println("The user logged in");
    }

    /*public boolean confirmCredentials(int id)
    {
        id = Integer.parseInt(userId.getText());
        if(userId.equals(admin)){
            return true;
        }
        else{
            return false;
        }
    }*/

    public void toAdminView() throws IOException {
        Stage stage = (Stage) userLoginButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/admin-view.fxml")));
        primaryStage.setTitle("Administrator");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
