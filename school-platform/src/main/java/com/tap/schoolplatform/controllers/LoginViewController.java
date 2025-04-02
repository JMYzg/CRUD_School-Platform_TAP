package com.tap.schoolplatform.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

import java.io.IOException;
import java.util.Objects;

public class LoginViewController extends ViewController {
    public Button loginButton;
    public TextField id;
    public TextField password;
    public CheckBox rememberUserCB;
    public Hyperlink ForgotPasswordHL;

    //public SharedData getUser;

    public void userLogin() throws IOException { //Temporal log in system so we can access to the different view windows
        if (id == null || password == null) {
            alert("", "Please make sure to full fill all the text boxes", Alert.AlertType.INFORMATION);
        }
        else {
            if (id.getText().equals("1")) {
                toAdminView();
            } else {
                if (id.getText().equals("2")) {
                    toTeacherView();
                } else {
                    toStudentView();
                }
            }
            System.out.println("The user logged in");
        }
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
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/admin-view.fxml")));
        primaryStage.setTitle("Administrator");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public void toTeacherView() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/teacher-option-view.fxml")));
        primaryStage.setTitle("Student");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public void toStudentView() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/student-view.fxml")));
        primaryStage.setTitle("Student");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setResizable(false);
    }
}
