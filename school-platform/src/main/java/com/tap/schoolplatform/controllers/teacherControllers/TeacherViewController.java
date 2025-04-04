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
        loadPageView("teacher-option-student-view", borderPane);
    }

    public void openExamsTab(ActionEvent event) {
        loadPageView("teacher-option-exams-view", borderPane);
    }

    public void openHomeworkTab(ActionEvent event) {
        loadPageView("teacher-option-homework-view", borderPane);
    }

    public void openGradesTap(ActionEvent event) {
        loadPageView("teacher-option-grade-view", borderPane);
    }

    public void logOut(ActionEvent event) throws IOException {
        logOutFunction(logOutButton);
    }

}
