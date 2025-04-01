package com.tap.schoolplatform.controllers;

import com.tap.schoolplatform.models.academic.Degree;
import com.tap.schoolplatform.models.academic.Group;
import com.tap.schoolplatform.models.academic.enums.Shift;
import com.tap.schoolplatform.models.academic.keys.GroupKey;
import com.tap.schoolplatform.models.enums.Gender;
import com.tap.schoolplatform.models.enums.Status;
import com.tap.schoolplatform.models.enums.UserRole;
import com.tap.schoolplatform.models.shared.BirthDate;
import com.tap.schoolplatform.models.users.Student;
import com.tap.schoolplatform.models.users.Teacher;
import com.tap.schoolplatform.models.users.User;
import com.tap.schoolplatform.services.users.AdministratorService;
import com.tap.schoolplatform.services.users.TeacherService;
import com.tap.schoolplatform.utils.SharedData;
import com.tap.schoolplatform.utils.UserDTO;
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

import java.io.IOException;
import java.util.*;

public class AdminViewController extends ViewController {

    public AnchorPane AnchorPaneImage1;
    public ComboBox<Gender> studentGenderComboBox;
    public ComboBox<Modality> studentModalityComboBox;
    public ComboBox studentDegreeComboBox;
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
    Student studentUser;
    Teacher teacherUser;
    Degree degree;
    UserDTO userDTO;
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
        userDTO = createDTO(studentUser.getRole(), studentNameTF, studentLastNameTF, createBrithDate(studentDayTF, studentMonthTF, studentYearTF), studentPhoneTF, studentEmailTF, studentGenderComboBox.getSelectionModel().getSelectedItem());
        adminUser.createUser(createStudent(studentUser), userDTO);
    }

    /*
    * 1. Create Student
    * 2. Add student to Group
    *   2.1 Select degree to add it (adminUser.setDegree(newDegree);
    *   2.2 Select
    * */
    public Student createStudent(Student student) {
        int id = Integer.parseInt(studentIdTF.getSelectedText());
        //Group group = studentGroupComboBox.getSelectionModel();
        GroupKey key = new GroupKey(Shift.MORNINGS, 1);

        Group group = findGroup(adminUser.getDegree().getGroupList(key), "1ISC-1M");
        //group.getStudentSet();
        Set<Integer> setExample = new TreeSet<>();
        for (Student student1 : group.getStudentList()) {
            if (student1.getStudentId().equals(student.getStudentId())) {
                id = group.getStudentList().indexOf(student1);
            }
        }
        // group.addStudent();

        return new Student(studentGroupComboBox.getSelectionModel().getSelectedItem(), id, studentNameTF.getText(), studentLastNameTF.getText(), createBrithDate(studentDayTF, studentMonthTF, studentYearTF), studentEmailTF.getText(), studentPhoneTF.getText(), createAddress(studentStreetTF, studentPCTF, studentColonyTF, studentCityTF, studentStateTF, studentCountryTF), studentGenderComboBox.getSelectionModel().getSelectedItem());
    }

    private Group findGroup(List<Group> groups, String id) {
        for (Group group : groups) {
            if (group.getId().equals(id)) return group;
        }
        return null;
    }

    public void refreshCBDegree (ComboBox<Degree> CBD) {
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
    }

    public void addTeacher(ActionEvent event) {
        User userTeacher = createTeacher(teacherUser);
        userDTO = createDTO(teacherUser.getRole(), teacherNameTF, teacherLastNameTF, createBrithDate(teacherDayTF, teacherMonthTF, teacherYearTF), teacherPhoneTF, teacherEmailTF, teacherGenderComboBox.getSelectionModel().getSelectedItem());
        adminUser.createUser(userTeacher, userDTO);
    }

    public Teacher createTeacher (Teacher teacher) {
     return new Teacher(teacherDegreeComboBox.getValue(), teacher.getLicense(), teacher.getSpecialization(), teacherNameTF.getText(), teacherLastNameTF.getText(), createBrithDate(teacherDayTF, teacherMonthTF, teacherYearTF), studentEmailTF.getText(), teacherPhoneTF.getText(), createAddress(teacherStreetTF, teacherPCTF, teacherColonyTF, teacherCityTF, teacherStateTF, teacherCountryTF), teacherGenderComboBox.getSelectionModel().getSelectedItem());
    }

    public void createDegree(ActionEvent event) {
    }

    public UserDTO createDTO (UserRole role, TextField nameTF, TextField nameLastNameTF, BirthDate BD, TextField phone, TextField email, Gender gender ) {
        UserDTO userDTO = new UserDTO();
        userDTO.setRole(role);
        userDTO.setName(nameTF.getText());
        userDTO.setLastName(nameLastNameTF.getText());
        userDTO.setBirthDate(BD);
        userDTO.setPhone(phone.getText());
        userDTO.setEmail(email.getText());
        userDTO.setGender(gender);
        return new UserDTO();
    }

    public void refreshCBStudentDegree(MouseEvent event) {
        refreshCBDegree(studentDegreeComboBox);
    }
}
