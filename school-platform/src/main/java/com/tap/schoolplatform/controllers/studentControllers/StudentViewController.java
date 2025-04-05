package com.tap.schoolplatform.controllers.studentControllers;

import com.tap.schoolplatform.controllers.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class StudentViewController extends ViewController {
    public Button studentDataButton;
    public Button classesTab;
    public Button homeworkTab;
    public Button gradesButton;
    public BorderPane borderPane;
    public Button logOutButton;

    public void openStudentDataTab(MouseEvent mouseEvent) {
        loadPageView("/views/student-views/student-data-view.fxml", borderPane);
    }

    public void openClassTab(MouseEvent mouseEvent) {
        loadPageView("/views/student-views/student-classes-view.fxml", borderPane);
    }

    public void openHomeworkTab(MouseEvent mouseEvent) {
    }

    public void openGradesTab(MouseEvent mouseEvent) {
    }



    public void exitStudentView(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/admin-views/login-view.fxml")));
        primaryStage.setTitle("Log in");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
