package com.tap.schoolplatform.controllers.teacherControllers;

import com.tap.schoolplatform.controllers.ViewController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NewHomeworkController extends ViewController {

    public TextField titleTF;
    public TextArea descriptionTF;
    public DatePicker datePicker;
    public TextField pointsTF;
    public Button cleanButton;
    public Button cancelButton;
    public Button acceptButton;

    public void clearAll(ActionEvent event) {
    }

    public void cancelHomework(ActionEvent event) {
        confirmationAlert("Are you sure you want to cancel the new homework?", "All details about the homework will be lost", cancelButton);
    }

    public void createHomework(ActionEvent event) {
        confirmationAlert("Are you sure you want to create the new homework?", "You'll be able to edit details from this homework later", acceptButton);
    }
}
