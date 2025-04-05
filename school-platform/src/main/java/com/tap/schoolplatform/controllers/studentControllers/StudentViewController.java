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
    public Button logOutButton;
    public BorderPane borderPane;

    public void openStudentDataTab(MouseEvent mouseEvent) {
        loadPageView("/views/student-views/student-data-view.fxml", borderPane);
    }

    public void openClassTab(MouseEvent mouseEvent) {
        loadPageView("/views/student-views/student-classes-view.fxml", borderPane);
    }

    public void openHomeworkTab(MouseEvent mouseEvent) {
        loadPageView("/views/student-views/student-homework-view.fxml", borderPane);
    }

    public void openGradesTab(MouseEvent mouseEvent) {
    }



    public void exitStudentView(MouseEvent mouseEvent) throws IOException {
        logOutFunction(logOutButton);
    }
}
