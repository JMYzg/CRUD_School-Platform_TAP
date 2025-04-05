package com.tap.schoolplatform.controllers.teacherControllers.homeworkTeacherViewControllers;

import com.tap.schoolplatform.controllers.ViewController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class HomeworkOptionController extends ViewController {
    public Button addHomeworkButton;
    public Button editHomework;
    public Button gradeButton;

    public void addHomework(ActionEvent event) throws IOException {
        loadNewPageView(event, "/views/teacher-views/teacher-option-homework-newHomework-view.fxml", "Create new homework");
    }

    public void editHomework(ActionEvent event) throws IOException {
        loadNewPageView(event, "/views/teacher-views/teacher-option-homework-newHomework-view.fxml", "Edit homework");
    }

    public void homeworkGradeView(ActionEvent event) throws IOException {
        loadNewPageView(event, "/views/teacher-views/teacher-option-homework-grade-view.fxml", "Homework grades");
    }
}
