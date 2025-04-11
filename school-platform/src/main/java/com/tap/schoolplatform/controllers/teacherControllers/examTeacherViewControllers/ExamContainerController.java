package com.tap.schoolplatform.controllers.teacherControllers.examTeacherViewControllers;

import com.tap.schoolplatform.controllers.ViewController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ExamContainerController extends ViewController {

    public Button editButton;
    public Button gradeButton;
    public Label examTitleLabel;
    public Label creationDateLabel;
    public Label deadLineLabel;
    public Label statusLabel;
    public Label dayOfAplicationLabel;
    public Label timeLabel;
    public Label durationLabel;

    public void initialize() {

    }

    public void editExam(ActionEvent actionEvent) {
        System.out.println( "Edit exam");
    }

    public void openExamGrades(ActionEvent actionEvent) {
        System.out.println( "Open exam grades");
    }
}
