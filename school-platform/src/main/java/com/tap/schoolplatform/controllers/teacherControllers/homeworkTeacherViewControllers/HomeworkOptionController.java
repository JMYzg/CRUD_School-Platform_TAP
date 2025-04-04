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
        loadNewPageView(event, "teacher-option-homework-newHomework-view", "Create new homework");
    }

    public void editHomework(ActionEvent event) throws IOException {
        loadNewPageView(event, "teacher-option-homework-newHomework-view", "Edit homework");
    }

    public void homeworkGradeView(ActionEvent event) {
    }
}
