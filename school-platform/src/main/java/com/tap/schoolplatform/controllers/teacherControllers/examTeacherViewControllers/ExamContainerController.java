package com.tap.schoolplatform.controllers.teacherControllers.examTeacherViewControllers;

import com.tap.schoolplatform.controllers.ViewController;
import com.tap.schoolplatform.controllers.teacherControllers.TeacherViewController;
import com.tap.schoolplatform.models.academic.Group;
import com.tap.schoolplatform.models.academic.Subject;
import com.tap.schoolplatform.models.academic.tasks.Evaluation;
import com.tap.schoolplatform.services.users.TeacherService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import static com.tap.schoolplatform.controllers.teacherControllers.TeacherViewController.currentGroup;
import static com.tap.schoolplatform.controllers.teacherControllers.TeacherViewController.currentSubject;

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
    public Evaluation currentEvaluation;

    public void initialize() {
    }

    public Evaluation getEvaluation() {
        return currentEvaluation;
    }

    private void updateUI() {
        if (currentEvaluation != null) {
            examTitleLabel.setText(currentEvaluation.getTitle());
            // Set other fields based on the evaluation object
            statusLabel.setText(currentEvaluation.getStatus().toString());
            dayOfAplicationLabel.setText("Not available");
            timeLabel.setText(currentEvaluation.getDeadline().toString());
            durationLabel.setText("Not available");
            // Add more fields as needed
        }
    }

    public void setEvaluation(Evaluation evaluation) {
        this.currentEvaluation = evaluation;
        updateUI();
    }

    public void editExam(ActionEvent actionEvent) {
        System.out.println( "Edit exam");
    }

    public void openExamGrades(ActionEvent actionEvent) {
        System.out.println( "Open exam grades");
    }
}
