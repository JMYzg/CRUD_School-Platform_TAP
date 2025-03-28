package com.tap.schoolplatform.controllers;

import com.tap.schoolplatform.models.enums.Gender;
import com.tap.schoolplatform.models.enums.Status;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class AdminViewController {

    public AnchorPane AnchorPaneImage1;
    public TextField studentIdRegisterTF;
    public Button studentUserNewButton;
    public Button studentNextAvailableButton;
    public TextField studentNameRegisterTF;
    public TextField studentLastNameRegisterTF;
    public ComboBox studentLadaComboBox;
    public TextField studentNumberRegisterTF;
    public TextField studentEmailRegisterTF;
    public TextField studentStreetRegisterTF;
    public TextField studentPCRegisterTF;
    public TextField studentColonyRegisterTF;
    public TextField studentCityRegisterTF;
    public TextField studentStateRegisterTF;
    public TextField studentCountryRegisterTF;
    public ComboBox studentGenderComboBox;
    public TextField studentBDDayRegisterTF;
    public TextField studentBDMonthRegisterTF;
    public TextField studentBDYearRegisterTF;
    public ComboBox studentModalityComboBox;
    public ComboBox studentDegreeComboBox;
    public Button studentAddDegreeButton;
    public ComboBox studentGroupComboBox;
    public Button studentAddGroupButton;
    public ComboBox studentClassroomComboBox;
    public AnchorPane AnchorPaneImage;
    public ComboBox studentStatusComboBox;

    public void initialize() {
        studentGenderComboBox.getItems().addAll(Arrays.asList(Gender.values()));
        studentStatusComboBox.getItems().addAll(Status.values());
    }

    public void newButton(ActionEvent actionEvent) {
    }

    public void nextAvailableButton(ActionEvent actionEvent) {
    }

    public void ladaCB(ActionEvent actionEvent) {
    }

    public void modalityCB(ActionEvent actionEvent) {
    }

    public void degreeCB(ActionEvent actionEvent) {
    }

    public void groupCB(ActionEvent actionEvent) {
    }

    public void classroomCB(ActionEvent actionEvent) {
    }

    public void statusCB(ActionEvent actionEvent) {
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
