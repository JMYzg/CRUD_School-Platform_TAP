package com.tap.schoolplatform.controllers;

import com.tap.schoolplatform.models.academic.Degree;
import com.tap.schoolplatform.models.academic.Group;
import com.tap.schoolplatform.models.enums.Gender;
import com.tap.schoolplatform.models.enums.Status;
import com.tap.schoolplatform.models.shared.Address;
import com.tap.schoolplatform.models.shared.BirthDate;
import com.tap.schoolplatform.models.users.Student;
import com.tap.schoolplatform.models.users.Teacher;
import com.tap.schoolplatform.services.users.AdministratorService;
import com.tap.schoolplatform.utils.UserDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.w3c.dom.Text;

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
    public TextField studentIdTF;
    public TextField studentNameTF;
    public TextField studentPCTF;
    public TextField studentEmailTF;
    public TextField studentPhoneTF;
    public TextField studentLastNameTF;
    public TextField studentStreetTF;
    public TextField studentColonyTF;
    public TextField studentCityTF;
    public TextField studentStateTF;
    public TextField studentCountryTF;
    public Button studentNextAvailableButton;
    public Button studentNewButton;
    public TextField studentDayTF;
    public TextField studentYearTF;
    public TextField studentMonthTF;
    AdministratorService adminUser;
    Student studentUser;
    Teacher teacherUser;
    Degree degree;
    UserDTO userDTO;


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


    public void addNewDegreeAdmin(ActionEvent event) throws IOException {
        Stage ownerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.initOwner(ownerStage);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/degree-view.fxml")));
        primaryStage.setTitle("Add new degree");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public void addNewGroupAdmin(ActionEvent event) throws IOException {
        Stage ownerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.initOwner(ownerStage);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/group-view.fxml")));
        primaryStage.setTitle("Add new group");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void addStudentButton(ActionEvent event) {
        adminUser.createUser(studentUser, userDTO);

    }

    public void createStudent(Student student) {
        //BirthDate BD = createBrithDate(studentDayTF, studentMonthTF, studentYearTF);
        int id = Integer.parseInt(studentIdTF.getSelectedText());
        //Group group = studentGroupComboBox.getSelectionModel();
        //return new Student(studentGroupComboBox.getSelectionModel(), id, studentNameTF.getText(), studentLastNameTF.getText(), createBrithDate(studentDayTF, studentMonthTF, studentYearTF), studentEmailTF.getText(), studentPhoneTF.getText(), createAddress(studentStreetTF, studentPCTF, studentColonyTF, studentCityTF, studentStateTF, studentCountryTF), studentGenderComboBox.getSelectionModel());
    }

    public BirthDate createBrithDate (TextField dayTF, TextField monthTF, TextField yearTF) {
        int d = Integer.parseInt(dayTF.getSelectedText());
        int m = Integer.parseInt(monthTF.getSelectedText());
        int y = Integer.parseInt(yearTF.getSelectedText());
        return new BirthDate(d, m, y);
    }

    public Address createAddress(TextField street, TextField PC, TextField colony, TextField city, TextField state, TextField country){
        int pc = Integer.parseInt(studentPCTF.getSelectedText());
        return new Address(studentStreetTF.getSelectedText(), pc, studentColonyTF.getSelectedText(), studentCityTF.getSelectedText(), studentStateTF.getSelectedText(), studentCountryTF.getSelectedText());
    }
}
