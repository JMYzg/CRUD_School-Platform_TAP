package com.tap.schoolplatform.controllers;

import com.tap.schoolplatform.auth.AuthenticationService;
import com.tap.schoolplatform.models.users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

import java.io.IOException;
import java.util.Objects;

public class LoginViewController extends ViewController {
    public Button loginButton;
    public TextField email;
    public TextField password;
    public CheckBox rememberUserCB;
    public Hyperlink ForgotPasswordHL;

    //public SharedData getUser;

    public void handeLogin(ActionEvent actionEvent) throws IOException {
        AuthenticationService authenticationService = new AuthenticationService();
        User user = authenticationService.login(email.getText(), password.getText());

        if (user != null) {
            switch (user.getRole()) {
                case ADMIN:
                    toAdminView();
                    break;
                case TEACHER:
                    toTeacherView();
                    break;
                case STUDENT:
                    toStudentView();
                    break;
            }
        } else {
            alert("Error", "Make sure your credentials are right", Alert.AlertType.ERROR);
        }
    }

    public void validateCredentials() /*throws IOException*/ { //Temporal log in system so we can access to the different view windows
        if (email == null || password == null) {
            alert("", "Please make sure to full fill all the text boxes", Alert.AlertType.INFORMATION);
        }
//        else {
//            if (email.getText().equals("1")) {
//                toAdminView();
//            } else {
//                if (email.getText().equals("2")) {
//                    toTeacherView();
//                } else {
//                    toStudentView();
//                }
//            }
//            System.out.println("The user logged in");
//        }
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/admin-views/admin-view.fxml")));
        primaryStage.setTitle("Administrator");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public void toTeacherView() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/teacher-views/teacher-option-view.fxml")));
        primaryStage.setTitle("Student");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public void toStudentView() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/student-views/student-view.fxml")));
        primaryStage.setTitle("Student");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setResizable(false);
    }
}
