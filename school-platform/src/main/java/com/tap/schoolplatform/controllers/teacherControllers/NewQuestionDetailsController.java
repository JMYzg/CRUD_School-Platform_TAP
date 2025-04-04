package com.tap.schoolplatform.controllers.teacherControllers;

import com.tap.schoolplatform.controllers.ViewController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class NewQuestionDetailsController extends ViewController {

    public Button cleanButton;
    public Button cancelButton;
    public Button acceptButton;

    public void cleanAllFields(ActionEvent event) {
    }

    public void cancelCreateQuestion(ActionEvent event) {
        confirmationAlert("Are you sure you want to cancel the new question?", "All the question details will be deleted", cancelButton);
    }

    public void createQuestion(ActionEvent event) {
    }
}
