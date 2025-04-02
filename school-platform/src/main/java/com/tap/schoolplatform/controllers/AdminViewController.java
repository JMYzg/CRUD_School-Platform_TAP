package com.tap.schoolplatform.controllers;

import com.tap.schoolplatform.models.academic.Degree;
import com.tap.schoolplatform.models.academic.Group;
import com.tap.schoolplatform.models.academic.enums.Shift;
import com.tap.schoolplatform.models.academic.keys.GroupKey;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import javax.management.relation.Role;
import java.io.IOException;
import java.util.*;

public class AdminViewController extends ViewController {

    public AnchorPane AnchorPaneImage1;
    public ComboBox<Gender> studentGenderComboBox;
    public ComboBox<Modality> studentModalityComboBox;
    public ComboBox<Degree> studentDegreeComboBox;
    public Button studentAddDegreeButton;
    public ComboBox<Group> studentGroupComboBox;
    public Button studentAddGroupButton;
    public ComboBox studentClassroomComboBox;
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
    public TextField studentDayTF;
    public TextField studentYearTF;
    public TextField studentMonthTF;
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
    public TextField teacherDayTF;
    public TextField teacherMonthTF;
    public TextField teacherYearTF;
    public TextField teacherAddDegreeTF;
    public Button teacherCreateDegreeButton;
    public ComboBox<Degree> teacherDegreeComboBox;
    AdministratorService adminUser;
    //Student studentUser;
    Teacher teacherUser;
    Degree degree;
    //UserDTO userDTO;
    private final SharedData sharedDataObject = SharedData.getInstance();

    public void initialize() {
        //teacherUser = new Teacher();
        studentGenderComboBox.getItems().setAll(Gender.values());
        studentGenderComboBox.setEditable(false);
        //studentModalityComboBox.getItems().setAll(something);
        studentModalityComboBox.setEditable(false);
        refreshCBDegree(studentDegreeComboBox);
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
        refreshCBDegree(teacherDegreeComboBox);
        teacherDegreeComboBox.setEditable(false);
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
        primaryStage.setResizable(false);
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
        primaryStage.setResizable(false);
    }

    public void addStudent(ActionEvent event) {
        //userDTO = createDTO(studentUser.getRole(), studentNameTF, studentLastNameTF, createBrithDate(studentDayTF, studentMonthTF, studentYearTF), studentPhoneTF, studentEmailTF, studentGenderComboBox.getSelectionModel().getSelectedItem());
        if (studentIdTF.getText() == null || studentNameTF.getText() == null || studentLastNameTF.getText() == null /*|| studentLadaComboBox.getValue() == null */ || studentPhoneTF.getText() == null || studentEmailTF.getText() == null || studentStreetTF.getText() == null || studentPCTF.getText() == null || studentColonyTF.getText() == null || studentCityTF.getText() == null || studentStateTF.getText() == null || studentCountryTF.getText() == null || studentCountryTF.getText() == null || studentGenderComboBox.getValue() == null || studentDayTF.getText() == null || studentMonthTF.getText() == null || studentYearTF.getText() == null || studentDegreeComboBox.getValue() == null || studentGroupComboBox.getValue() == null || studentStatusComboBox.getValue() == null) {
            alert("Error", "Please make sure to full fill all the tex boxes", Alert.AlertType.ERROR);
        } else {
            adminUser = new AdministratorService(studentDegreeComboBox.getValue());
            Student student = new Student(studentNameTF.getText(), studentLastNameTF.getText(), createBrithDate(studentDayTF, studentMonthTF, studentYearTF), studentEmailTF.getText(), studentPhoneTF.getText(), createAddress(studentStreetTF, studentPCTF, studentColonyTF, studentCityTF, studentStateTF, studentCountryTF), studentGenderComboBox.getValue());
            student.setGroup(studentGroupComboBox.getSelectionModel().getSelectedItem());
            UserDTO userDTO = new UserDTO();
            createUserDTO(student);
            adminUser.createUser(student, userDTO);
        }
    }
    /*
    * 1. Create Student
    * 2. Add student to Group
    *   2.1 Select degree to add it (adminUser.setDegree(newDegree);
    *   2.2 Select
    * */
    public void createStudent(Student student) {

        /*int id = Integer.parseInt(studentIdTF.getSelectedText());
        //Group group = studentGroupComboBox.getSelectionModel();
        GroupKey key = new GroupKey(Shift.MORNINGS, 1);

        //Group group = findGroup(adminUser.getDegree().getGroupList(key), "1ISC-1M");
        //group.getStudentSet();
        Set<Integer> setExample = new TreeSet<>();
        /*for (Student student1 : group.getStudentList()) {
            if (student1.getStudentId().equals(student.getStudentId())) {
                id = group.getStudentList().indexOf(student1);
            }
        }
        // group.addStudent();
*/
        //return new Student(studentGroupComboBox.getSelectionModel().getSelectedItem(), id, studentNameTF.getText(), studentLastNameTF.getText(), createBrithDate(studentDayTF, studentMonthTF, studentYearTF), studentEmailTF.getText(), studentPhoneTF.getText(), createAddress(studentStreetTF, studentPCTF, studentColonyTF, studentCityTF, studentStateTF, studentCountryTF), studentGenderComboBox.getSelectionModel().getSelectedItem());
    }
/*
    private Group findGroup(List<Group> groups, String id) {
        for (Group group : groups) {
            if (group.getId().equals(id)) return group;
        }
        return null;
    }
*/
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

    public void createTeacher (Teacher teacher) {
    }

    public void createDegree(ActionEvent event) {
    }

    public void refreshCBStudentDegree(MouseEvent event) {
        refreshCBDegree(studentDegreeComboBox);
    }

    public void addTeacher(ActionEvent event) {
        /*if (teacherIDTF.getText() == null || teacherNameTF.getText() == null || teacherLastNameTF.getText() == null /*|| teacherLadaComboBox.getValue() == null  || teacherPhoneTF.getText() == null || teacherEmailTF.getText() == null || teacherStreetTF.getText() == null || teacherPCTF.getText() == null || teacherColonyTF.getText() == null || teacherCityTF.getText() == null || teacherStreetTF.getText() == null || teacherCountryTF.getText() == null || teacherCountryTF.getText() == null || teacherGenderComboBox.getValue() == null || teacherDayTF.getText() == null || teacherMonthTF.getText() == null || teacherYearTF.getText() == null || teacherDegreeComboBox.getValue() == null /*|| teacherGroupComboBox.getValue() == null || teacStatusComboBox.getValue() == null) {
            alert("Error", "Please make sure to full fill all the tex boxes", Alert.AlertType.ERROR);
        } else {
            adminUser = new AdministratorService(studentDegreeComboBox.getValue());
            Student student = new Student(studentNameTF.getText(), studentLastNameTF.getText(), createBrithDate(studentDayTF, studentMonthTF, studentYearTF), studentEmailTF.getText(), studentPhoneTF.getText(), createAddress(studentStreetTF, studentPCTF, studentColonyTF, studentCityTF, studentStateTF, studentCountryTF), studentGenderComboBox.getValue());
            student.setGroup(studentGroupComboBox.getValue());
            UserDTO userDTO = new UserDTO();
            createUserDTO(student);
            adminUser.createUser(student, userDTO);
        }
        */
    }

    public UserDTO createUserDTO (Student studentUser) {
        UserDTO userDTO = new UserDTO();
        userDTO.setRole(UserRole.STUDENT);
        userDTO.setName(studentUser.getName());
        userDTO.setLastName(studentUser.getLastName());
        userDTO.setBirthDate(studentUser.getBirthDate());
        userDTO.setEmail(studentUser.getEmail());
        userDTO.setPhone(studentUser.getPhone());
        userDTO.setAddress(studentUser.getAddress());
        userDTO.setGender(studentUser.getGender());
        userDTO.setStatus(studentUser.getStatus());
        userDTO.setGroup(studentUser.getGroup());
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
}
