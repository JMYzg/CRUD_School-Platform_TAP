package com.tap.schoolplatform.controllers.adminControllers;

import com.tap.schoolplatform.controllers.ViewController;
import com.tap.schoolplatform.models.academic.Degree;
import com.tap.schoolplatform.models.academic.Group;
import com.tap.schoolplatform.models.enums.Gender;
import com.tap.schoolplatform.models.enums.Status;
import com.tap.schoolplatform.models.enums.UserRole;
import com.tap.schoolplatform.models.users.Student;
import com.tap.schoolplatform.models.users.Teacher;
import com.tap.schoolplatform.models.users.User;
import com.tap.schoolplatform.services.users.AdministratorService;
import com.tap.schoolplatform.utils.SharedData;
import com.tap.schoolplatform.utils.dtos.UserDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.util.*;

public class AdminViewController extends ViewController {

    Student student;
    public AnchorPane AnchorPaneImage1;
    public ComboBox<Gender> studentGenderComboBox;
    public ComboBox<Degree> studentDegreeComboBox;
    public Button studentAddDegreeButton;
    public ComboBox<Group> studentGroupComboBox;
    public Button studentAddGroupButton;
    public AnchorPane AnchorPaneImage;
    public ComboBox<Status> studentStatusComboBox;
    public ComboBox<Gender> teacherGenderComboBox;
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
    public Button teacherNewButton;
    public TextField teacherIDTF;
    public Button teacherNextAvailableButton;
    public TextField teacherLastNameTF;
    public TextField teacherNameTF;
    public TextField teacherPhoneTF;
    public TextField teacherEmailTF;
    public TextField teacherStreetTF;
    public TextField teacherPCTF;
    public TextField teacherColonyTF;
    public TextField teacherCityTF;
    public TextField teacherStateTF;
    public TextField teacherCountryTF;
    public TextField teacherAddDegreeTF;
    public Button teacherCreateDegreeButton;
    public ComboBox<Degree> teacherDegreeComboBox;
    public Button logOutButton;
    public DatePicker studentDatePicker;
    public DatePicker teacherDatePicker;
    public Button studentEditButton;
    public Button studentAcceptButton;
    public Button studentCancelButton;
    public TableView<Student> studentList;
    public TableColumn<Student, UUID> studentIdTableColumn;
    public TableColumn<Student, String> studentLNTableColumn;
    public TableColumn<Student, String> studentNameTableColumn;
    public TableColumn<Student, String> studentDegreeTableColumn;
    public TableColumn<Student, String> studentGroupTableColumn;
    AdministratorService adminService;

    private final SharedData sharedDataObject = SharedData.getInstance();

    public void initialize() {
        studentIdTF.setEditable(false);
        studentGenderComboBox.getItems().setAll(Gender.values());
        studentGenderComboBox.setEditable(false);
        refreshCBDegree(studentDegreeComboBox);
        studentDegreeComboBox.setEditable(false);
        studentGroupComboBox.setEditable(false);
        studentStatusComboBox.getItems().setAll(Status.values());
        studentStatusComboBox.setEditable(false);
        studentIdTableColumn.setCellValueFactory(new PropertyValueFactory<Student, UUID>("id"));
        studentLNTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        studentLNTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        studentLNTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("degree"));
        studentLNTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("group"));
        studentList.setItems(sharedDataObject.getStudents());
        studentDatePicker.setEditable(false);
        teacherIDTF.setEditable(false);
        teacherGenderComboBox.getItems().setAll(Gender.values());
        teacherGenderComboBox.setEditable(false);
        studentLadaComboBox.setEditable(false);
        teacherLadaComboBox.setEditable(false);
        refreshCBDegree(teacherDegreeComboBox);
        teacherDegreeComboBox.setEditable(false);
        teacherDatePicker.setEditable(false);
    }

    public void addNewDegreeAdmin(ActionEvent event) throws IOException {
        loadNewPageView(event, "degree-view", "Add new degree");
    }
    public void addNewGroupAdmin(ActionEvent event) throws IOException {
        loadNewPageView(event, "group-view", "Add new group");
    }

    public void addStudent(ActionEvent event) {
        if (studentIdTF.getText() == null || studentNameTF.getText() == null || studentLastNameTF.getText() == null || studentPhoneTF.getText() == null || studentEmailTF.getText() == null || studentStreetTF.getText() == null || studentPCTF.getText() == null || studentColonyTF.getText() == null || studentCityTF.getText() == null || studentStateTF.getText() == null || studentCountryTF.getText() == null || studentCountryTF.getText() == null || studentGenderComboBox.getValue() == null || studentDatePicker.getValue() == null|| studentDegreeComboBox.getValue() == null || studentGroupComboBox.getValue() == null || studentStatusComboBox.getValue() == null) {
            alert("Error", "Please make sure to full fill all the text boxes", Alert.AlertType.ERROR);
        }
            adminService = new AdministratorService(studentDegreeComboBox.getValue());
            Student studentUser = new Student(studentNameTF.getText(), studentLastNameTF.getText(), createBrithDate(studentDatePicker), studentEmailTF.getText(), studentPhoneTF.getText(), createAddress(studentStreetTF, studentPCTF, studentColonyTF, studentCityTF, studentStateTF, studentCountryTF), studentGenderComboBox.getValue());
            UserDTO userDTO = new UserDTO();
            userDTO.setGroup(studentGroupComboBox.getValue());
            adminService.createUser(studentUser, userDTO);
            alert("", "Student added correctly", Alert.AlertType.INFORMATION);
    }

    public void refreshCBDegree (ComboBox CBD) {
        CBD.getItems().setAll(sharedDataObject.getDegrees());
        CBD.setConverter(new StringConverter<Degree>() {
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
        studentGroupComboBox.setDisable(true);
        CBD.getSelectionModel().selectedItemProperty().addListener((observable, oldVal, newVal) -> {studentGroupComboBox.setDisable(newVal == null);});
    }

    public void createTeacher () {
        if (teacherIDTF.getText() == null || teacherNameTF.getText() == null || teacherLastNameTF.getText() == null || teacherPhoneTF.getText() == null || teacherEmailTF.getText() == null || teacherStreetTF.getText() == null || teacherPCTF.getText() == null || teacherColonyTF.getText() == null || teacherCityTF.getText() == null || teacherStreetTF.getText() == null || teacherCountryTF.getText() == null || teacherCountryTF.getText() == null || teacherGenderComboBox.getValue() == null || teacherDatePicker.getValue() == null || teacherDegreeComboBox.getValue() == null) {
            alert("Error", "Please make sure to full fill all the text boxes", Alert.AlertType.ERROR);
        } else {
            adminService = new AdministratorService(null);
            Teacher teacher = new Teacher(teacherNameTF.getText(), teacherLastNameTF.getText(), createBrithDate(teacherDatePicker), teacherEmailTF.getText(), teacherPhoneTF.getText(), createAddress(teacherStreetTF, teacherPCTF, teacherColonyTF, teacherCityTF, teacherStateTF, teacherCountryTF), teacherGenderComboBox.getValue());
            UserDTO userDTO = new UserDTO();
            createUserDTO(teacher, UserRole.TEACHER);
            adminService.createUser(teacher, userDTO);
        }
    }

    public void createDegree(ActionEvent event) {
    }

    public void refreshCBStudentDegree(MouseEvent event) {
        refreshCBDegree(studentDegreeComboBox);
    }

    public void addTeacher(ActionEvent event) {
    }

    public UserDTO createUserDTO (User user, UserRole role) {
        UserDTO userDTO = new UserDTO();
        userDTO.setRole(role);
        userDTO.setName(user.getName());
        userDTO.setLastName(user.getLastName());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setAddress(user.getAddress());
        userDTO.setGender(user.getGender());
//        userDTO.setStatus(userDTO.getStatus()); // I removed this shit nigga ass
        userDTO.setGroup(userDTO.getGroup());
        //if (role == UserRole.TEACHER) {
           // userDTO.setLicense(null);
           // userDTO.setDegree(teacherDegreeComboBox.getValue());
           // userDTO.setSpecialization(null);
        //}
        //if (role == UserRole.STUDENT) {

        //}
        return userDTO;
    }

    public void refreshCBStudentGroup(MouseEvent mouseEvent) {
        studentGroupComboBox.getItems().setAll(studentDegreeComboBox.getSelectionModel().getSelectedItem().getGroupList(1));
        studentGroupComboBox.setConverter(new StringConverter<Group>() {
            @Override
            public String toString(Group group) {
                if(group != null) return group.getID();
                else return null;
            }

            @Override
            public Group fromString(String s) {
                return null;
            }
        });
    }


    public void logOut(ActionEvent event) throws IOException {
        logOutFunction(logOutButton);
    }

    public void editStudent(ActionEvent event) {
    }

    public void acceptChangesStudent(ActionEvent event) {
    }

    public void cancelChangesStudent(ActionEvent event) {
    }
}
