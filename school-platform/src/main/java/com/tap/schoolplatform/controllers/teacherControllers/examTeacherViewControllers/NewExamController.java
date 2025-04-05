package com.tap.schoolplatform.controllers.teacherControllers.examTeacherViewControllers;

import com.tap.schoolplatform.controllers.ViewController;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;

public class NewExamController extends ViewController {

    public TextField examTitle;
    public Button plusButton;
    public DatePicker datePicker;
    public Button cleanButton;
    public Button cancelButton;
    public Button acceptExam;

    public void cleanAllButton(ActionEvent event) {
    }

    public void cancelExam(ActionEvent event) {
        confirmationAlert("Are you sure you want to cancel the new exam?", "All questions and options will be lost", cancelButton);
    }

    public void createExam(ActionEvent event) {
        confirmationAlert("Are you sure you want to create the new exam?", "You'll be able to edit the details from this homework later", cancelButton);
    }

    public void addQuestion(ActionEvent event) throws IOException {
        loadNewPageView(event, "/views/teacher-views/teacher-option-exam-new-question-details-view.fxml", "Add new question");
    }
}
