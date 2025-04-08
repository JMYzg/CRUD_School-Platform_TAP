package com.tap.schoolplatform.controllers.adminControllers;

import com.tap.schoolplatform.controllers.ViewController;
import com.tap.schoolplatform.models.academic.Degree;
import com.tap.schoolplatform.models.academic.enums.Semester;
import com.tap.schoolplatform.models.academic.enums.Shift;
import com.tap.schoolplatform.services.users.AdministratorService;
import com.tap.schoolplatform.utils.SharedData;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class GroupViewController extends ViewController {

    public Button cancelButton;
    public ComboBox<Shift> shiftComboBox;
    public ComboBox<Degree> degreeComboBox;
    public ComboBox<Semester> semesterComboBox;
    public Button addButton;
    public Button clearAllButton;
    SharedData sharedDataObject = SharedData.getInstance();
    AdministratorService adminUser;
    Degree degree;

    public void initialize() {
        refreshDegreeCB();
        //degree = degreeComboBox.getSelectionModel().getSelectedItem();
        shiftComboBox.getItems().setAll(Shift.values());
        shiftComboBox.setEditable(false);
        //.getItems().setAll(something);
        degreeComboBox.setEditable(false);
        semesterComboBox.getItems().setAll(Semester.values());
        semesterComboBox.setEditable(false);
    }

    public void addGroup(ActionEvent event) {
        Degree selectedDegree = degreeComboBox.getValue();
        Shift selectedShift = shiftComboBox.getValue();
        if(semesterComboBox == null || shiftComboBox == null || degreeComboBox == null) {
            alertError("Error", "Please make sure to full fill all the options boxes");
        }
        else {
            //int semester = Integer.parseInt(semesterComboBox.getSelectionModel().getSelectedItem().toString());
            adminUser = new AdministratorService(selectedDegree);
            adminUser.createGroup(semesterComboBox.getValue(), selectedShift);
            alertInfo("", "Group added correctly", "");
            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.close();
        }
    }

    public void clearAll(MouseEvent event) {
        shiftComboBox.valueProperty().set(null);
        semesterComboBox.valueProperty().set(null);
        degreeComboBox.valueProperty().set(null);
    }

    public void cancelGroup() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void refreshDegreeCB () {
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
}
