package com.tap.schoolplatform.controllers.adminControllers;

import com.tap.schoolplatform.controllers.ViewController;
import com.tap.schoolplatform.models.academic.Degree;
import com.tap.schoolplatform.models.academic.Group;
import com.tap.schoolplatform.models.enums.Gender;
import com.tap.schoolplatform.models.enums.UserRole;
import com.tap.schoolplatform.models.shared.Address;
import com.tap.schoolplatform.models.users.Student;
import com.tap.schoolplatform.models.users.Teacher;
import com.tap.schoolplatform.models.users.User;
import com.tap.schoolplatform.services.users.AdministratorService;
import com.tap.schoolplatform.utils.SharedData;
import com.tap.schoolplatform.utils.dtos.UserDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class AdminViewController extends ViewController {
    // student attributes
    public Button studentNewButton;
    public TextField studentNameTF;
    public TextField studentLastNameTF;
    public TextField studentPhoneTF;
    public TextField studentEmailTF;
    public TextField studentStreetTF;
    public TextField studentPCTF;
    public TextField studentColonyTF;
    public TextField studentCityTF;
    public TextField studentStateTF;
    public TextField studentCountryTF;
    public ComboBox<Gender> studentGenderComboBox;
    public DatePicker studentDatePicker;
    public ComboBox<Degree> studentDegreeComboBox;
    public Button studentManageDegreeButton;
    public ComboBox<Group> studentGroupComboBox;
    public Button studentManageGroupsButton;
    public ImageView studentImageView;
    public TextField studentSearchTF;
    public Button studentFilterButton;
    public TableView<Student> studentList;
    public TableColumn<Student, String> studentIdTableColumn;
    public TableColumn<Student, String> studentNameTableColumn;
    public TableColumn<Student, String> studentDegreeTableColumn;
    public TableColumn<Student, String> studentGroupTableColumn;
    public TableColumn<Student, String> studentLNTableColumn;
    public TableColumn<Student, String> studentEmailTableColumn;
    public TableColumn<Student, String> studentPhoneTableColumn;
    public TableColumn<Address, String> studentStreetTableColumn;
    public TableColumn<Address, Integer> studentPCTableColumn;
    public TableColumn<Address, String> studentColonyTableColumn;
    public TableColumn<Address, String> studentCityTableColumn;
    public TableColumn<Address, String> studentStateTableColumn;
    public TableColumn<Address, String> studentCountryTableColumn;
    public TableColumn<Student, Gender> studentGenderTableColumn;
    public TableColumn<Student, String> studentAgeTableColumn;
    // teacher attributes
    public Button teacherNewButton;
    public TextField teacherNameTF;
    public TextField teacherLastNameTF;
    public TextField teacherPhoneTF;
    public TextField teacherEmailTF;
    public TextField teacherStreetTF;
    public TextField teacherPCTF;
    public TextField teacherColonyTF;
    public TextField teacherCityTF;
    public TextField teacherStateTF;
    public TextField teacherCountryTF;
    public ComboBox<Gender> teacherGenderComboBox;
    public DatePicker teacherDatePicker;
    public ComboBox<Degree> teacherDegreeComboBox;
    public Button teacherManageDegreeButton;
    public AnchorPane AnchorPaneImage;
    public Button teacherNextAvailableButton;
    public TextField teacherAddDegreeTF;
    public Button teacherCreateDegreeButton;
    public Button logOutButton;
    public Button studentEditButton;
    public Button studentAcceptButton;
    public Button studentCancelButton;
    public Button studentUploadImageButton;
    public TextField teacherSearchTF;
    public Button teacherFilterButton;
    public TableView<Teacher> teacherList;
    public TableColumn<Teacher, String> teacherLicenseTableColumn;
    public TableColumn<Teacher, String> teacherNameTableColumn;
    public TableColumn<Teacher, String> teacherEspecialitationTableColumn;
    public TableColumn<Teacher, String> teacherDegreeTableColumn;
    public TableColumn<Teacher, String> teacherLNTableColumn;
    public TableColumn<Teacher, String> teacherEmailTableColumn;
    public TableColumn<Teacher, Integer> teacherPhoneTableColumn;
    public TableColumn<Address, String> teacherStreetTableColumn;
    public TableColumn<Address, Integer> teacherPCTableColumn;
    public TableColumn<Address, String> teacherColonyTableColumn;
    public TableColumn<Address, String> teacherCityTableColumn;
    public TableColumn<Address, String> teacherStateTableColumn;
    public TableColumn<Address, String> teacherCountryTableColumn;
    public TableColumn<Teacher, Gender> teacherGenderTableColumn;
    public TableColumn<Teacher, String> teacherAgeTableColumn;
    AdministratorService adminService;

    private final SharedData sharedDataObject = SharedData.getInstance();

    public void initialize() {
        adminService = new AdministratorService();
        studentGenderComboBox.setEditable(false);
        studentGenderComboBox.getItems().setAll(Gender.values());
        studentDatePicker.setEditable(false);
        studentDegreeComboBox.setEditable(false);
//        refreshCBDegree(studentDegreeComboBox);
        studentDegreeComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                List<Group> groups = newVal.getGroupList(1);
                studentGroupComboBox.getItems().setAll(groups);

                studentGroupComboBox.setConverter(new StringConverter<Group>() {
                    @Override
                    public String toString(Group group) {
                        return (group != null) ? group.getID() : "";
                    }

                    @Override
                    public Group fromString(String s) {
                        return null;
                    }
                });

                studentGroupComboBox.setDisable(groups.isEmpty());
            } else {
                studentGroupComboBox.getItems().clear();
                studentGroupComboBox.setDisable(true);
            }
        });
        studentGroupComboBox.setEditable(false);
        studentIdTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("ID"));
        studentNameTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        studentDegreeTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("degree"));
        studentGroupTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("group"));
        studentLNTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        studentEmailTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        studentPhoneTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("phone"));
        studentStreetTableColumn.setCellValueFactory(new PropertyValueFactory<Address, String>("street"));
        studentPCTableColumn.setCellValueFactory(new PropertyValueFactory<Address, Integer>("postalCode"));
        studentColonyTableColumn.setCellValueFactory(new PropertyValueFactory<Address, String>("colony"));
        studentCityTableColumn.setCellValueFactory(new PropertyValueFactory<Address, String>("city"));
        studentStateTableColumn.setCellValueFactory(new PropertyValueFactory<Address, String>("state"));
        studentCountryTableColumn.setCellValueFactory(new PropertyValueFactory<Address, String>("country"));
        studentGenderTableColumn.setCellValueFactory(new PropertyValueFactory<Student, Gender>("gender"));
        studentAgeTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("age"));
        studentList.setItems(sharedDataObject.getStudents());
        studentDatePicker.setEditable(false);
        teacherGenderComboBox.getItems().setAll(Gender.values());
        teacherGenderComboBox.setEditable(false);
        refreshCBDegree(teacherDegreeComboBox);
        teacherDegreeComboBox.setEditable(false);
//        teacherDatePicker.setEditable(false);
        teacherLicenseTableColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("license"));
        teacherNameTableColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("name"));
        teacherEspecialitationTableColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("specialization"));
        teacherDegreeTableColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("degree"));
        teacherLNTableColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("lastName"));
        teacherEmailTableColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("email"));
        teacherPhoneTableColumn.setCellValueFactory(new PropertyValueFactory<Teacher, Integer>("phone"));
        teacherStreetTableColumn.setCellValueFactory(new PropertyValueFactory<Address, String>("phone"));
        teacherPCTableColumn.setCellValueFactory(new PropertyValueFactory<Address, Integer>("postalCode"));
        teacherColonyTableColumn.setCellValueFactory(new PropertyValueFactory<Address, String>("colony"));
        teacherCityTableColumn.setCellValueFactory(new PropertyValueFactory<Address, String>("city"));
        teacherStateTableColumn.setCellValueFactory(new PropertyValueFactory<Address, String>("state"));
        teacherCountryTableColumn.setCellValueFactory(new PropertyValueFactory<Address, String>("country"));
        teacherGenderTableColumn.setCellValueFactory(new PropertyValueFactory<Teacher, Gender>("gender"));
        teacherAgeTableColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("age"));
        teacherList.setItems(sharedDataObject.getTeachers());
    }

    public void studentCreateNewStudent (ActionEvent event) {
        // refresque
        addStudent(event);
    }

    public void addStudent(ActionEvent event) {
        if (studentNameTF.getText().isEmpty() || studentLastNameTF.getText().isEmpty() || studentPhoneTF.getText().isEmpty() || studentEmailTF.getText().isEmpty() || studentStreetTF.getText().isEmpty() || studentPCTF.getText().isEmpty() || studentColonyTF.getText().isEmpty() || studentCityTF.getText().isEmpty() || studentStateTF.getText().isEmpty() || studentCountryTF.getText().isEmpty() || studentCountryTF.getText().isEmpty() || studentGenderComboBox.getValue() == null || studentDatePicker.getValue() == null|| studentDegreeComboBox.getValue() == null || studentGroupComboBox.getValue() == null || studentImageView == null) {
            alert("Error", "Please make sure to full fill all the text boxes", Alert.AlertType.ERROR);
        }
        adminService = new AdministratorService();
        if (studentImageView == null) {
        studentImageView = new ImageView(studentUploadImage());
        }
        adminService.createStudent(studentGroupComboBox.getValue(), studentImageView.getImage(), studentNameTF.getText(), studentLastNameTF.getText(), createBrithDate(studentDatePicker), studentEmailTF.getText(), studentPhoneTF.getText(), createAddress(studentStreetTF, studentPCTF, studentColonyTF, studentCityTF, studentStateTF, studentCountryTF), studentGenderComboBox.getValue());
        alert("", "Student added correctly", Alert.AlertType.INFORMATION);
    }

    public Image studentUploadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chose the image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpg", "*.jpeg")
        );
        File image = fileChooser.showOpenDialog(studentUploadImageButton.getScene().getWindow());
        if (image != null) {
            try {
                String imagePath = image.toURI().toString();
                Image pfp = new Image(imagePath);
                studentImageView.setImage(pfp);
                return pfp;
            } catch (IllegalArgumentException e) {
                System.err.println("Error loading the image: " + e.getMessage());
                return null;
            }
        }
        return null;
    }

    public void manageDegreesAdmin(ActionEvent event) throws IOException {
        loadNewPageView(event, "/views/admin-views/degree-view.fxml", "Add new degree");
    }

    public void manageGroupAdmin(ActionEvent event) throws IOException {
        loadNewPageView(event, "/views/admin-views/group-view.fxml", "Add new group");
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
        if (teacherNameTF.getText() == null || teacherLastNameTF.getText() == null || teacherPhoneTF.getText() == null || teacherEmailTF.getText() == null || teacherStreetTF.getText() == null || teacherPCTF.getText() == null || teacherColonyTF.getText() == null || teacherCityTF.getText() == null || teacherStreetTF.getText() == null || teacherCountryTF.getText() == null || teacherCountryTF.getText() == null || teacherGenderComboBox.getValue() == null || teacherDatePicker.getValue() == null || teacherDegreeComboBox.getValue() == null) {
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

    public void searchStudent(ActionEvent event) {
        studentList.setItems(adminService.findStudent(studentSearchTF.getText()));
    }

    public void searchTeacher() {
        teacherList.setItems(adminService.findTeacher(teacherSearchTF.getText()));
    }
}
