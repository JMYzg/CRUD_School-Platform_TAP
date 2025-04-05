package com.tap.schoolplatform.controllers.teacherControllers;

import com.tap.schoolplatform.controllers.ViewController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class TeacherViewController extends ViewController {
    public Button studentsButton;
    public Button examsButton;
    public Button homeworkButton;
    public Button gradesButton;
    public Button logOutButton;
    public BorderPane borderPane;

    public void openStudentTab(ActionEvent event) {
        loadPageView("/views/teacher-views/teacher-option-student-view.fxml", borderPane);
    }

    public void openExamsTab(ActionEvent event) {
        loadPageView("/views/teacher-views/teacher-option-exams-view.fxml", borderPane);
    }

    public void openHomeworkTab(ActionEvent event) {
        loadPageView("/views/teacher-views/teacher-option-homework-view.fxml", borderPane);
    }

    public void openGradesTap(ActionEvent event) {
        loadPageView("/views/teacher-views/teacher-option-grade-view.fxml", borderPane);
    }

    public void logOut(ActionEvent event) throws IOException {
        logOutFunction(logOutButton);
    }
}