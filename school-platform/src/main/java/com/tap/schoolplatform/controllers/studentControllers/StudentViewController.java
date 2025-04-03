package com.tap.schoolplatform.controllers.studentControllers;

import com.tap.schoolplatform.controllers.ViewController;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class StudentViewController extends ViewController {
    public Button studentDataButton;
    public Button classesTab;
    public Button homeworkTab;
    public Button gradesButton;
    public BorderPane borderPane;
    public Button logOutButton;

    public void openStudentDataTab(MouseEvent mouseEvent) {
        loadPageView("student-data-view", borderPane);
    }

    public void openClassTab(MouseEvent mouseEvent) {
        loadPageView("student-classes-view", borderPane);
    }

    public void openHomeworkTab(MouseEvent mouseEvent) {
        loadPageView("student-homework-view", borderPane);
    }

    public void openGradesTab(MouseEvent mouseEvent) {
        //loadPageView("/views/student-", borderPane);
    }

    public void exitStudentView(MouseEvent mouseEvent) throws IOException {
        logOutFunction(logOutButton);
    }
}
