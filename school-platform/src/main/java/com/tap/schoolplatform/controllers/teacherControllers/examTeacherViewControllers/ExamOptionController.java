package com.tap.schoolplatform.controllers.teacherControllers.examTeacherViewControllers;

import com.tap.schoolplatform.controllers.ViewController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;

public class ExamOptionController extends ViewController {

    public Button createNewExamButton;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;

    private TextField option1;
    private TextField option2;
    private TextField option3;
    private TextField option4;

    private ToggleGroup radioGroup;

    public void createNewExamn(ActionEvent event) throws IOException {
        loadNewPageView(event, "/views/teacher-views/teacher-option-exam-new-view.fxml", "Create new exam");
    }

}
