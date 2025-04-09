package com.tap.schoolplatform.controllers.teacherControllers.homeworkTeacherViewControllers;

import com.tap.schoolplatform.controllers.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

public class NewHomeworkController extends ViewController {

    public TextField titleTF;
    public TextArea descriptionTF;
    public DatePicker datePicker;
    public TextField pointsTF;
    public Button cleanButton;
    public Button cancelButton;
    public Button acceptButton;

    @FXML
    private Spinner<Integer> spinnerHour;  // Segundo grupo
    @FXML
    private Spinner<Integer> spinnerMinute; // Segundo grupo

    public void initialize() {
        spinnerConfiguration(spinnerHour, 0, 23);
        spinnerConfiguration(spinnerMinute, 0, 59);
    }

    private void spinnerConfiguration(Spinner<Integer> spinner, int min, int max) {
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max);
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

    @FXML
    private void guardarValores() {
        // Aquí obtienes los valores cuando el usuario haga clic
        int hoursLimitTime = spinnerHour.getValue();
        int minutesLimitTime = spinnerMinute.getValue();
    }

    public void clearAll(ActionEvent event) {
    }

    public void cancelHomework(ActionEvent event) {
        confirmationAlertCloseWindow("All details about the homework will be lost","Are you sure you want to cancel the new homework?", cancelButton);
    }

    public void createHomework(ActionEvent event) {
        confirmationAlertCloseWindow("You'll be able to edit details from this homework later","Are you sure you want to create the new homework?", acceptButton);
    }
}
