package com.tap.schoolplatform.controllers.teacherControllers.homeworkTeacherViewControllers;

import com.tap.schoolplatform.controllers.ViewController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

import static com.tap.schoolplatform.controllers.teacherControllers.TeacherViewController.currentGroup;

public class HomeworkOptionController extends ViewController {
    public Button addHomeworkButton;
    public Button editHomework;
    public Button gradeButton;
    public Label groupName;
    public Label semesterName;
    public Label shiftName;

    public void initialize() {
        groupName.setText(currentGroup.getID());
        semesterName.setText(currentGroup.getSemester().toString());
        shiftName.setText(currentGroup.getShift().toString());
    }

    public void addHomework(ActionEvent event) throws IOException {
        loadNewPageView(event, "/views/teacher-views/teacher-option-homework-new-view.fxml", "Create new homework");
    }

    public void editHomework(ActionEvent event) throws IOException {
        loadNewPageView(event, "/views/teacher-views/teacher-option-homework-new-view.fxml", "Edit homework");
    }

    public void homeworkGradeView(ActionEvent event) throws IOException {
        loadNewPageView(event, "/views/teacher-views/teacher-option-homework-grade-view.fxml", "Homework grades");
    }
}
