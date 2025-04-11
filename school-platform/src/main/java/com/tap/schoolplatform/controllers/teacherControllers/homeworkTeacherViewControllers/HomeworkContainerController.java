package com.tap.schoolplatform.controllers.teacherControllers.homeworkTeacherViewControllers;

import com.tap.schoolplatform.controllers.ViewController;
import com.tap.schoolplatform.models.academic.tasks.Assignment;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HomeworkContainerController extends ViewController {
    public Button editButton;
    public Button gradeButton;
    public Label homeworkTitle;
    public Label creationDateLabel;
    public Label deadLineLabel;
    public Assignment currentAssignment;


    public void initialize() {

    }

    private void updateUI() {
        if (currentAssignment != null) {
            homeworkTitle.setText(currentAssignment.getTitle());
            creationDateLabel.setText(currentAssignment.getCreationDate().toString());
            deadLineLabel.setText(currentAssignment.getDeadline().toString());
        }
    }

    public void setAssignment(Assignment assignment) {
        this.currentAssignment = assignment;
        updateUI();
    }

    public void editHomework(ActionEvent actionEvent) {
    }

    public void openHomeworkGrades(ActionEvent actionEvent) {
    }
}
