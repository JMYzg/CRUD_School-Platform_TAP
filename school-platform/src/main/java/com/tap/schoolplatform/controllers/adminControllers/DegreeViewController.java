package com.tap.schoolplatform.controllers.adminControllers;

import com.tap.schoolplatform.controllers.ViewController;
import com.tap.schoolplatform.models.academic.Degree;
import com.tap.schoolplatform.services.users.AdministratorService;
import com.tap.schoolplatform.utils.SharedData;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class DegreeViewController extends ViewController {
    public TextField newDegreeTextField;
    public ComboBox degreeComboBox;
    public Button addButton;
    public Button clearAllButton;
    public Button cancelButton;
    AdministratorService adminDegree;
    Degree degree;
    SharedData sharedDataObject = SharedData.getInstance();

    public void initialize() {
        degreeComboBox.setEditable(false);
        refreshCBDegree();
        adminDegree = new AdministratorService(null);
        degree = new Degree(newDegreeTextField.getText());
    }

    public void addDegree(ActionEvent actionEvent) {
        if(newDegreeTextField.getText().isEmpty()) {
            alertError("Error", "Please write a name for the degree.");
        }
        if(newDegreeTextField.getText().equals(sharedDataObject.getDegrees().toString())) {
            alertError("Error", "There is already a degree with this name, please write a non existing degree.");
        }
        adminDegree.createDegree(newDegreeTextField.getText());
        System.out.println(newDegreeTextField.getText());
        alertInfo("", "Degree added correctly", "");
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }

    public void cancelDegree() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void clearNewDegree(ActionEvent actionEvent) {
        //newDegreeTextField.set
    }

    public void refreshCBDegree () {
        degreeComboBox.getItems().setAll(sharedDataObject.getDegrees());
        degreeComboBox.setConverter(new StringConverter<Degree>() {
            @Override
            public String toString(Degree degree) {
                if(degree != null) return degree.getName();
                else return null;
            }

            @Override
            public Degree fromString(String s) {
                return null;
            }
        });
    }

    public void clearAll(MouseEvent mouseEvent) {
        degreeComboBox.valueProperty().set(null);
        newDegreeTextField.clear();
    }
}
