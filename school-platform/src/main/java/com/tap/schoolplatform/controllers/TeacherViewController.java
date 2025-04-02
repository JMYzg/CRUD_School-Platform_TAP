package com.tap.schoolplatform.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Objects;

public class TeacherViewController extends ViewController {
    public Button studentsButton;
    public Button examsButton;
    public Button homeworkButton;
    public Button gradesButton;
    public Button logOutButton;
    public BorderPane borderPane;

    public void openStudentTab(ActionEvent event) {
    }

    public void openExamsTab(ActionEvent event) {
    }

    public void openHomeworkTab(ActionEvent event) {
    }

    public void openGradesTap(ActionEvent event) {
    }

    public void logOut(ActionEvent event) {
    }

    public void loadPageView(String pageName) {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(pageName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        borderPane.setCenter(root);
    }

}
