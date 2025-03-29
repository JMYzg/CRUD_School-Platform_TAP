package com.tap.schoolplatform.controllers;

import com.tap.schoolplatform.models.enums.Gender;
import com.tap.schoolplatform.models.enums.Status;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;


public class AdminViewController {

    public AnchorPane AnchorPaneImage1;
    public ComboBox studentGenderComboBox;
    public ComboBox studentModalityComboBox;
    public ComboBox studentDegreeComboBox;
    public Button studentAddDegreeButton;
    public ComboBox studentGroupComboBox;
    public Button studentAddGroupButton;
    public ComboBox studentClassroomComboBox;
    public AnchorPane AnchorPaneImage;
    public ComboBox studentStatusComboBox;
    public ComboBox teacherGenderComboBox;
    public ComboBox studentLadaComboBox;
    public ComboBox teacherLadaComboBox;


    public void initialize() {
        studentGenderComboBox.getItems().setAll(Gender.values());
        studentGenderComboBox.setEditable(false);
        //studentModalityComboBox.getItems().setAll(something);
        studentModalityComboBox.setEditable(false);
        //studentDegreeComboBox.getItems().setAll(something);
        studentDegreeComboBox.setEditable(false);
        //studentGroupComboBox.getItems().setAll(something);
        studentGroupComboBox.setEditable(false);
        //studentClassroomComboBox.getItems().setAll(something);
        studentClassroomComboBox.setEditable(false);
        studentStatusComboBox.getItems().setAll(Status.values());
        studentStatusComboBox.setEditable(false);
        teacherGenderComboBox.getItems().setAll(Gender.values());
        teacherGenderComboBox.setEditable(false);
        //studentLadaComboBox.getItems().setAll(something);
        studentLadaComboBox.setEditable(false);
        //teacherLadaComboBox.getItems().setAll(something);
        teacherLadaComboBox.setEditable(false);
    }

    public ComboBox getStudentDegreeComboBox() {
        return studentDegreeComboBox;
    }

    public void newButton() {
    }

    public void nextAvailableButton() {
    }

    public void ladaCB() {
    }

    public void modalityCB() {
    }

    public void degreeCB() {
    }

    public void groupCB() {
    }

    public void classroomCB() {
    }

    public void statusCB() {
    }

    public void addNewDegreeAdmin() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/degree-view.fxml")));
        primaryStage.setTitle("Add new degree");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void addNewGroupAdmin() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/group-view.fxml")));
        primaryStage.setTitle("Add new group");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
