package com.tap.schoolplatform.controllers.teacherControllers.examTeacherViewControllers;

import com.tap.schoolplatform.controllers.ViewController;
import com.tap.schoolplatform.utils.SharedData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.io.IOException;

public class NewExamController extends ViewController {

    public DatePicker datePicker;
    public Button cancelButton;
    public Button submitExamButton;
    public TextField examTitleTF;
    public Button okButton;
    public Spinner timeHourSpinner;
    public Spinner timeMinuteSpinner;
    public TextField examOption1TF;
    public TextField examOption2TF;
    public TextField examOption3TF;
    public TextField examOption4TF;
    public ToggleGroup radioGroup;
    public RadioButton rightAnswer1RadioButton;
    public RadioButton rightAnswer2RadioButton;
    public RadioButton rightAnswer3RadioButton;
    public RadioButton rightAnswer4RadioButton;
    public Button addQuestionButton;
    public TableView<String> tableQuestions;
    public TableColumn questionNumberTableColumn;
    public TableColumn questionTableColumn;

    public void initialize() {
        datePicker.setEditable(false);
        timeHourSpinner.setEditable(false);
        timeMinuteSpinner.setEditable(false);

    }
    public void cleanAllButton(ActionEvent event) {
    }

    public void cancelExam(ActionEvent event) {
        confirmationAlertCloseWindow("All questions and options will be lost","Are you sure you want to cancel the new exam?", cancelButton);
    }

    public void submitExam(ActionEvent event) {
        confirmationAlertCloseWindow("You'll be able to edit the details from this homework later","Are you sure you want to create the new exam?", cancelButton);
    }

    public void addQuestion(ActionEvent event) throws IOException {
        loadNewPageView(event, "/views/teacher-views/teacher-option-exam-new-question-details-view.fxml", "Add new question");
    }

    public void setExamName(ActionEvent actionEvent) {
    }
}
