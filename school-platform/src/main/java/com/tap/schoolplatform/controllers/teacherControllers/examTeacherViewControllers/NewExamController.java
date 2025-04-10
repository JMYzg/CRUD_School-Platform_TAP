package com.tap.schoolplatform.controllers.teacherControllers.examTeacherViewControllers;

import com.tap.schoolplatform.controllers.ViewController;
import com.tap.schoolplatform.models.academic.tasks.Evaluation;
import com.tap.schoolplatform.models.academic.tasks.Task;
import javafx.util.StringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class NewExamController extends ViewController {
//    private final Evaluation currentExam = new Evaluation(" "," ",null);
    private boolean isEditing = true;

    public TextField examTitle;
    public Button plusButton;
    public DatePicker datePicker;
    public Button cleanButton;
    public Button cancelButton;
    public Button acceptExam;

    @FXML
    private Spinner<Integer> spinnerHourDte;
    @FXML
    private Spinner<Integer> spinnerMinuteDte;
    @FXML
    private Spinner<Integer> spinnerHour;  // Segundo grupo
    @FXML
    private Spinner<Integer> spinnerMinute; // Segundo grupo
    @FXML
    private Button btnSetTittleExam;
    @FXML
    private TextField titleTextField;
    @FXML
    private Label titleLabel;


    int currentHour;

    public void initialize() {
        spinnerConfiguration(spinnerHourDte, 0, 23);
        spinnerConfiguration(spinnerMinuteDte, 0, 59);
        spinnerConfiguration(spinnerHour, 0, 23);
        spinnerConfiguration(spinnerMinute, 0, 59);
        titleTextField.setVisible(true);
        titleLabel.setVisible(false);
    }


    private void spinnerConfiguration(Spinner<Integer> spinner, int min, int max) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max);
        valueFactory.setValue(min);
        valueFactory.setConverter(new StringConverter<Integer>() {

                public String toString(Integer value) {
                    return String.format("%02d", value); // Formato de dos dígitos
                }

                public Integer fromString(String string) {
                    return Integer.parseInt(string);
                }
        });

        spinner.setValueFactory(valueFactory);
    }

    public void setExamTitle(ActionEvent event) {
           // titleTextField.setDisable(true);
        if (isEditing) {
//            // Save the title and switch to display mode
//            currentExam.setTitle(titleTextField.getText());
//            titleLabel.setText(currentExam.getTitle());
            titleTextField.setVisible(false);
            titleLabel.setVisible(true);
            btnSetTittleExam.setText("EDIT");
        } else {
//            // Switch back to edit mode
           titleTextField.setVisible(true);
           titleLabel.setVisible(false);
            btnSetTittleExam.setText("OK");
        }
        isEditing = !isEditing; // Toggle state

    }

    @FXML
    private void guardarValores() {
        // Aquí obtienes los valores cuando el usuario haga clic
        int hoursDte = spinnerHourDte.getValue();
        int minutesDte = spinnerMinuteDte.getValue();
        int hoursLimitTime = spinnerHour.getValue();
        int minutesLimitTime = spinnerMinute.getValue();
    }

    public void cleanAllButton(ActionEvent event) {
    }

    public void cancelExam(ActionEvent event) {
        confirmationAlertCloseWindow("All questions and options will be lost", "Are you sure you want to cancel the new exam?", cancelButton);
    }

    public void createExam(ActionEvent event) {
        confirmationAlertCloseWindow("You'll be able to edit the details from this homework later", "Are you sure you want to create the new exam?", cancelButton);
    }

    public void addQuestion(ActionEvent event) throws IOException {
        loadNewPageView(event, "/views/teacher-views/teacher-option-exam-new-question-details-view.fxml", "Add new question");
    }
}
