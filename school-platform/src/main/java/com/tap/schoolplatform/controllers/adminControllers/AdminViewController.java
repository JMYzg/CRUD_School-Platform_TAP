package com.tap.schoolplatform.controllers.adminControllers;

import com.tap.schoolplatform.controllers.ViewController;
import com.tap.schoolplatform.models.academic.Degree;
import com.tap.schoolplatform.models.academic.Group;
import com.tap.schoolplatform.models.academic.Subject;
import com.tap.schoolplatform.models.academic.enums.Semester;
import com.tap.schoolplatform.models.enums.Gender;
import com.tap.schoolplatform.models.shared.Address;
import com.tap.schoolplatform.models.shared.BirthDate;
import com.tap.schoolplatform.models.users.Student;
import com.tap.schoolplatform.models.users.Teacher;
import com.tap.schoolplatform.models.users.User;
import com.tap.schoolplatform.services.academic.DegreeService;
import com.tap.schoolplatform.services.academic.SubjectService;
import com.tap.schoolplatform.services.users.AdministratorService;
import com.tap.schoolplatform.services.users.TeacherService;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public TableColumn<Student, Degree> studentDegreeTableColumn;
    public TableColumn<Student, Group> studentGroupTableColumn;
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
    public TableColumn<Student, BirthDate> studentAgeTableColumn;

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
    public TableColumn<Teacher, String> teacherSpecializationTableColumn;
    public TableColumn<Teacher, Degree> teacherDegreeTableColumn;
    public TableColumn<Teacher, String> teacherLNTableColumn;
    public TableColumn<Teacher, String> teacherEmailTableColumn;
    public TableColumn<Teacher, String> teacherPhoneTableColumn;
    public TableColumn<Address, String> teacherStreetTableColumn;
    public TableColumn<Address, Integer> teacherPCTableColumn;
    public TableColumn<Address, String> teacherColonyTableColumn;
    public TableColumn<Address, String> teacherCityTableColumn;
    public TableColumn<Address, String> teacherStateTableColumn;
    public TableColumn<Address, String> teacherCountryTableColumn;
    public TableColumn<Teacher, Gender> teacherGenderTableColumn;
    public TableColumn<Teacher, String> teacherAgeTableColumn;
    public ComboBox<Degree> teacherDegreeComboBox;
    public Button teacherAcceptButton;
    public TextField teacherLicenseTF;
    public TextField teacherSpecializationTF;
    public TextField teacherSubjectTF;
    public Button teacherCreateSubjectButton;
    public ComboBox<Subject> teacherAssignSubjectComboBox;
    public ComboBox<Subject> teacherUnassignSubjectComboBox;
    public Button teacherUnassignSubjectButton;
    public Button teacherAssignSubjectButton;
    public ComboBox<Semester> teacherAssignSubjectSemesterComboBox;
    public ComboBox<Semester> teacherUnassignSubjectSemesterComboBox;
    public ComboBox<Semester> teacherSemesterSubjectComboBox;
    public Button teacherCancelButton;
    public Button teacherEditButton;

    AdministratorService adminService;
    TeacherService teacherService;
    DegreeService degreeService;
    SubjectService subjectService;

    private final SharedData sharedDataObject = SharedData.getInstance();

    public void initialize() {
        adminService = new AdministratorService();
        teacherService = new TeacherService();
        degreeService = new DegreeService();
        subjectService = new SubjectService();

        studentNewButton.setDisable(true);
        studentAcceptButton.setText("Create");
        studentCancelButton.setText("Unselect");
        studentGenderComboBox.setEditable(false);
        studentGenderComboBox.getItems().setAll(Gender.values());
        studentDatePicker.setEditable(false);
        studentDegreeComboBox.setEditable(false);
//        refreshCBDegree(studentDegreeComboBox);
        studentDegreeComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                List<Group> groups = newVal.getGroupList(Semester.FIRST);
                studentGroupComboBox.getItems().setAll(groups);

                studentGroupComboBox.setConverter(new StringConverter<>() {
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
        studentEditButton.setDisable(true);
        studentCancelButton.setDisable(true);
        studentIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        studentNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        studentDegreeTableColumn.setCellValueFactory(new PropertyValueFactory<>("degree"));
        studentGroupTableColumn.setCellValueFactory(new PropertyValueFactory<>("group"));
        studentLNTableColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        studentEmailTableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        studentPhoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        studentStreetTableColumn.setCellValueFactory(new PropertyValueFactory<>("street"));
        studentPCTableColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        studentColonyTableColumn.setCellValueFactory(new PropertyValueFactory<>("colony"));
        studentCityTableColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        studentStateTableColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
        studentCountryTableColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        studentGenderTableColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        studentAgeTableColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        studentList.setItems(sharedDataObject.getStudents());
        studentDatePicker.setEditable(false);
        teacherGenderComboBox.getItems().setAll(Gender.values());
        teacherGenderComboBox.setEditable(false);
        refreshCBDegree(teacherDegreeComboBox);
        teacherDegreeComboBox.setEditable(false);
//Por qué aparece tanto esto?
//        teacherDatePicker.setEditable(false);
        teacherLicenseTableColumn.setCellValueFactory(new PropertyValueFactory<>("license"));
        teacherNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        teacherSpecializationTableColumn.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        teacherDegreeTableColumn.setCellValueFactory(new PropertyValueFactory<>("degree"));
        teacherLNTableColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        teacherEmailTableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        teacherPhoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        teacherStreetTableColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        teacherPCTableColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        teacherColonyTableColumn.setCellValueFactory(new PropertyValueFactory<>("colony"));
        teacherCityTableColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        teacherStateTableColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
        teacherCountryTableColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        teacherGenderTableColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        teacherAgeTableColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        teacherList.setItems(sharedDataObject.getTeachers());
        teacherSemesterSubjectComboBox.getItems().setAll(Semester.values());
        teacherSemesterSubjectComboBox.setEditable(false);
        teacherAssignSubjectSemesterComboBox.getItems().setAll(Semester.values());
        teacherAssignSubjectSemesterComboBox.setEditable(false);
        teacherUnassignSubjectSemesterComboBox.getItems().setAll(Semester.values());
        teacherUnassignSubjectSemesterComboBox.setEditable(false);
        teacherDatePicker.setEditable(false);
        teacherNewButton.setDisable(true);

        teacherAcceptButton.setText("Create");
        teacherCancelButton.setText("Unselect");

        teacherSubjectTF.setEditable(false);
        teacherEditButton.setDisable(true);
        teacherCancelButton.setDisable(true);

        // Inicializar estado de componentes como deshabilitados
        disableSubjectManagementComponents();

        // Listener para el ComboBox de Degree
        teacherDegreeComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            boolean degreeSelected = newVal != null;

            // Habilitar ComboBoxes de semestre principales
            teacherSemesterSubjectComboBox.setDisable(!degreeSelected);
            teacherAssignSubjectSemesterComboBox.setDisable(!degreeSelected);
            teacherUnassignSubjectSemesterComboBox.setDisable(!degreeSelected);

            // Si se deselecciona el degree, deshabilitar componentes dependientes
            if (!degreeSelected) {
                disableDependentComponents();
            }
        });

        // Listeners para los ComboBoxes de semestre
        setupSemesterListeners();

    }

    private void disableSubjectManagementComponents() {
        // Componentes de creación de materias
        teacherSemesterSubjectComboBox.setDisable(true);
        teacherSubjectTF.setDisable(true);
        teacherCreateSubjectButton.setDisable(true);

        // Componentes de asignación
        teacherAssignSubjectSemesterComboBox.setDisable(true);
        teacherAssignSubjectComboBox.setDisable(true);
        teacherAssignSubjectButton.setDisable(true);

        // Componentes de desasignación
        teacherUnassignSubjectSemesterComboBox.setDisable(true);
        teacherUnassignSubjectComboBox.setDisable(true);
        teacherUnassignSubjectButton.setDisable(true);
    }

    private void disableDependentComponents() {
        teacherSubjectTF.setDisable(true);
        teacherCreateSubjectButton.setDisable(true);
        teacherAssignSubjectComboBox.setDisable(true);
        teacherAssignSubjectButton.setDisable(true);
        teacherUnassignSubjectComboBox.setDisable(true);
        teacherUnassignSubjectButton.setDisable(true);
    }

    private void setupSemesterListeners() {
        // Listener para semestre en creación de materias
        teacherSemesterSubjectComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            boolean enabled = newVal != null;
            teacherSubjectTF.setDisable(!enabled);
            teacherCreateSubjectButton.setDisable(!enabled);
        });

        // Listener para semestre en asignación
        teacherAssignSubjectSemesterComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            boolean enabled = newVal != null;
            teacherAssignSubjectComboBox.setDisable(!enabled);
            teacherAssignSubjectButton.setDisable(!enabled);
        });

        // Listener para semestre en desasignación
        teacherUnassignSubjectSemesterComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            boolean enabled = newVal != null;
            teacherUnassignSubjectComboBox.setDisable(!enabled);
            teacherUnassignSubjectButton.setDisable(!enabled);
        });
    }

    public void addStudent(ActionEvent event) {
        if (studentAcceptButton.getText().equals("Create")) {
            if (studentNameTF.getText().isEmpty() || studentLastNameTF.getText().isEmpty() || studentPhoneTF.getText().isEmpty() || studentEmailTF.getText().isEmpty() || studentStreetTF.getText().isEmpty() || studentPCTF.getText().isEmpty() || studentColonyTF.getText().isEmpty() || studentCityTF.getText().isEmpty() || studentStateTF.getText().isEmpty() || studentCountryTF.getText().isEmpty() || studentCountryTF.getText().isEmpty() || studentGenderComboBox.getValue() == null || studentDatePicker.getValue() == null || studentDegreeComboBox.getValue() == null || studentGroupComboBox.getValue() == null || studentImageView == null) {
                alertError("Error", "Please make sure to full fill all the text boxes");
            } else if (!verifyName(studentNameTF.getText())) {
                alertError("Invalid name", "Please write a valid name");
            } else if (!verifyName(studentLastNameTF.getText())) {
                alertError("Invalid last name", "Please write a valid last name");
            } else if (!verifyPhone(studentPhoneTF.getText())) {
                alertError("Invalid phone number", "Please write a valid phone number");
            } else if (!verifyEmail(studentEmailTF.getText())) {
                alertError("Invalid email", "Please write a valid email");
            } else {
                adminService = new AdministratorService();
                if (studentImageView == null) {
                    studentImageView = new ImageView(studentUploadImage());
                }
                adminService.createStudent(studentGroupComboBox.getValue(), studentImageView.getImage(), studentNameTF.getText(), studentLastNameTF.getText(), createBrithDate(studentDatePicker), studentEmailTF.getText(), studentPhoneTF.getText(), createAddress(studentStreetTF, studentPCTF, studentColonyTF, studentCityTF, studentStateTF, studentCountryTF), studentGenderComboBox.getValue());
                alertInfo("", "Student added correctly", "");
                studentNameTF.clear();
                studentLastNameTF.clear();
                studentPhoneTF.clear();
                studentEmailTF.clear();
                studentStreetTF.clear();
                studentPCTF.clear();
                studentColonyTF.clear();
                studentCityTF.clear();
                studentStateTF.clear();
                studentCountryTF.clear();
                studentGenderComboBox.setValue(null);
                studentDatePicker.setValue(null);
                studentDegreeComboBox.setValue(null);
                studentGroupComboBox.setValue(null);
                studentImageView.setImage(null);
            }
        } else if (studentAcceptButton.getText().equals("Update")) {
            if (studentNameTF.getText().isEmpty() || studentLastNameTF.getText().isEmpty() || studentPhoneTF.getText().isEmpty() || studentEmailTF.getText().isEmpty() || studentStreetTF.getText().isEmpty() || studentPCTF.getText().isEmpty() || studentColonyTF.getText().isEmpty() || studentCityTF.getText().isEmpty() || studentStateTF.getText().isEmpty() || studentCountryTF.getText().isEmpty() || studentCountryTF.getText().isEmpty() || studentGenderComboBox.getValue() == null || studentDatePicker.getValue() == null || studentDegreeComboBox.getValue() == null || studentGroupComboBox.getValue() == null || studentImageView == null) {
                alertError("Error", "Please make sure to full fill all the text boxes");
            } else if (!verifyName(studentNameTF.getText())) {
                alertError("Invalid name", "Please write a valid name");
            } else if (!verifyName(studentLastNameTF.getText())) {
                alertError("Invalid last name", "Please write a valid last name");
            } else if (!verifyPhone(studentPhoneTF.getText())) {
                alertError("Invalid phone number", "Please write a valid phone number");
            } else if (!verifyEmail(studentEmailTF.getText())) {
                alertError("Invalid email", "Please write a valid email");
            } else {
                adminService.updateUser(studentList.getSelectionModel().getSelectedItem(), createUserDTO(studentList.getSelectionModel().getSelectedItem()));
                alertInfo("", "Student updated correctly", "");
                enableDisableStudentAttributes(true);
                eraseAllStudentAttributes();
                studentAcceptButton.setDisable(true);
                studentEditButton.setDisable(false);
            }
        }

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

    public void refreshCBDegree(ComboBox CBD) {
        CBD.getItems().setAll(sharedDataObject.getDegrees());
        CBD.setConverter(new StringConverter<Degree>() {
            @Override
            public String toString(Degree degree) {
                if (degree != null) return degree.getName();
                else return null;
            }

            @Override
            public Degree fromString(String s) {
                return null;
            }
        });
        studentGroupComboBox.setDisable(true);
        CBD.getSelectionModel().selectedItemProperty().addListener((observable, oldVal, newVal) -> {
            studentGroupComboBox.setDisable(newVal == null);
        });
    }

    public void createTeacher() {
        if (studentAcceptButton.getText().equals("Create")) {
            if (teacherNameTF.getText() == null || teacherLastNameTF.getText() == null || teacherPhoneTF.getText() == null || teacherEmailTF.getText() == null || teacherStreetTF.getText() == null || teacherPCTF.getText() == null || teacherColonyTF.getText() == null || teacherCityTF.getText() == null || teacherStreetTF.getText() == null || teacherCountryTF.getText() == null || teacherCountryTF.getText() == null || teacherGenderComboBox.getValue() == null || teacherDatePicker.getValue() == null || teacherDegreeComboBox.getValue() == null) {
                alertError("Error", "Please make sure to full fill all the text boxes");
            } else if (!verifyName(teacherNameTF.getText())) {
                alertError("Invalid name", "Please write a valid name");
            } else if (!verifyName(teacherLastNameTF.getText())) {
                alertError("Invalid last name", "Please write a valid last name");
            } else if (!verifyPhone(teacherPhoneTF.getText())) {
                alertError("Invalid phone number", "Please write a valid phone number");
            } else if (!verifyEmail(teacherEmailTF.getText())) {
                alertError("Invalid email", "Please write a valid email");
            } else {
                adminService = new AdministratorService();
                adminService.createTeacher(teacherDegreeComboBox.getValue(), teacherNameTF.getText(), teacherLastNameTF.getText(), createBrithDate(teacherDatePicker), teacherEmailTF.getText(), teacherPhoneTF.getText(), createAddress(teacherStreetTF, teacherPCTF, teacherColonyTF, teacherCityTF, teacherStateTF, teacherCountryTF), teacherGenderComboBox.getValue(), teacherLicenseTF.getText(), teacherSpecializationTF.getText());
                alertInfo("", "Teacher added correctly", "");
                teacherNameTF.clear();
                teacherLastNameTF.clear();
                teacherLicenseTF.clear();
                teacherSpecializationTF.clear();
                teacherPhoneTF.clear();
                teacherEmailTF.clear();
                teacherStreetTF.clear();
                teacherPCTF.clear();
                teacherColonyTF.clear();
                teacherCityTF.clear();
                teacherStateTF.clear();
                teacherCountryTF.clear();
                teacherGenderComboBox.setValue(null);
                teacherDatePicker.setValue(null);
                teacherDegreeComboBox.setValue(null);
                teacherSemesterSubjectComboBox.setValue(null);
                teacherSubjectTF.clear();
                teacherAssignSubjectSemesterComboBox.setValue(null);
                teacherAssignSubjectComboBox.setValue(null);
                teacherUnassignSubjectSemesterComboBox.setValue(null);
                teacherUnassignSubjectComboBox.setValue(null);

            }
        } else if (teacherAcceptButton.getText().equals("Update")) {
            if (teacherNameTF.getText() == null || teacherLastNameTF.getText() == null || teacherPhoneTF.getText() == null || teacherEmailTF.getText() == null || teacherStreetTF.getText() == null || teacherPCTF.getText() == null || teacherColonyTF.getText() == null || teacherCityTF.getText() == null || teacherStreetTF.getText() == null || teacherCountryTF.getText() == null || teacherCountryTF.getText() == null || teacherGenderComboBox.getValue() == null || teacherDatePicker.getValue() == null || teacherDegreeComboBox.getValue() == null) {
                alertError("Error", "Please make sure to full fill all the text boxes");
            } else if (!verifyName(teacherNameTF.getText())) {
                alertError("Invalid name", "Please write a valid name");
            } else if (!verifyName(teacherLastNameTF.getText())) {
                alertError("Invalid last name", "Please write a valid last name");
            }
        } else if (!verifyPhone(teacherPhoneTF.getText())) {
            alertError("Invalid phone number", "Please write a valid phone number");
        } else if (!verifyEmail(teacherEmailTF.getText())) {
            alertError("Invalid email", "Please write a valid email");
        } else {
            adminService.updateUser(teacherList.getSelectionModel().getSelectedItem(), createUserDTO(teacherList.getSelectionModel().getSelectedItem()));
            alertInfo("", "Teacher updated correctly", "");
            enableDisableTeacherAttributes(true);
            eraseAllTeacherAttributes();
            teacherAcceptButton.setDisable(true);
            teacherEditButton.setDisable(false);
        }
    }

    public void createDegree(ActionEvent event) {
    }

    public void refreshCBStudentDegree(MouseEvent event) {
        refreshCBDegree(studentDegreeComboBox);
    }

    private UserDTO createUserDTO(User user) {
        UserDTO userDTO = new UserDTO();

        // Set basic user information
        userDTO.setName(studentNameTF.getText());
        userDTO.setLastName(studentLastNameTF.getText());
        userDTO.setEmail(studentEmailTF.getText());
        userDTO.setPhone(studentPhoneTF.getText());

        // Set address information
        Address address = user.getAddress();

        if (address != null) {
            userDTO.setAddress(createAddress(studentStreetTF, studentPCTF, studentColonyTF, studentCityTF, studentStateTF, studentCountryTF));
        }

        // Set other attributes
        userDTO.setGender(studentGenderComboBox.getValue());
        userDTO.setBirthDate(createBrithDate(studentDatePicker));

        // If degree and group information is needed
        if (user instanceof Student) {
            userDTO.setDegree(studentDegreeComboBox.getValue());
            userDTO.setGroup(studentGroupComboBox.getValue());
        }

        if (user instanceof Teacher teacher) {

        }

        return userDTO;
    }


    public void refreshCBStudentGroup(MouseEvent mouseEvent) {
        studentGroupComboBox.getItems().setAll(studentDegreeComboBox.getSelectionModel().getSelectedItem().getGroupList(Semester.FIRST));
        studentGroupComboBox.setConverter(new StringConverter<Group>() {
            @Override
            public String toString(Group group) {
                if (group != null) return group.getID();
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

    public boolean verifyName(String name) {
        String nameRegex = "^[A-Za-z]+$";
        return Pattern.matches(nameRegex, name);
    }

    public boolean verifyEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean verifyPhone(String phone) {
        String phoneRegex = "^\\+?\\d{7,15}$";
        return Pattern.matches(phoneRegex, phone);
    }

    public void teacherClearAllAttributes(ActionEvent event) {
        if (teacherAcceptButton.getText().equals("Create")) {
            if (confirmationAlertIf("You wont be able to recover the information", "Are you sure you want to clear delete all?")) {
                eraseAllTeacherAttributes();
                //Enable student attributes
                enableDisableTeacherAttributes(false);
                teacherEditButton.setDisable(true);
                teacherCancelButton.setText("Cancel");
                teacherCancelButton.setDisable(true);
                teacherAcceptButton.setText("Create");
                teacherAcceptButton.setDisable(false);
                teacherCancelButton.setText("Unselect");
            }
        } else if (teacherAcceptButton.getText().equals("Update")) {
            eraseAllTeacherAttributes();
            //Enable student attributes
            enableDisableTeacherAttributes(false);
            teacherEditButton.setDisable(true);
            teacherCancelButton.setText("Unselect");
            teacherCancelButton.setDisable(true);
            teacherAcceptButton.setText("Create");
            teacherAcceptButton.setDisable(false);
        }
    }

    public void studentClearAllAttributes(ActionEvent event) {
        if (studentAcceptButton.getText().equals("Create")) {
            if (confirmationAlertIf("You wont be able to recover the information", "Are you sure you want to clear delete all?")) {
                eraseAllStudentAttributes();
                //Enable student attributes
                enableDisableStudentAttributes(false);
                studentEditButton.setDisable(true);
                studentCancelButton.setText("Cancel");
                studentCancelButton.setDisable(true);
                studentAcceptButton.setText("Create");
                studentAcceptButton.setDisable(false);
                studentCancelButton.setText("Unselect");
                studentImageView.setImage(null);
            }
        } else if (studentAcceptButton.getText().equals("Update")) {
            eraseAllStudentAttributes();
            //Enable student attributes
            enableDisableStudentAttributes(false);
            studentEditButton.setDisable(true);
            studentCancelButton.setText("Unselect");
            studentCancelButton.setDisable(true);
            studentAcceptButton.setText("Create");
            studentAcceptButton.setDisable(false);
        }
    }

    public void studentSelectUser(MouseEvent mouseEvent) {
        int index = studentList.getSelectionModel().getSelectedIndex();
        studentNameTF.setText(studentNameTableColumn.getCellObservableValue(index).getValue());
        studentLastNameTF.setText(studentLNTableColumn.getCellObservableValue(index).getValue());
        studentPhoneTF.setText(studentPhoneTableColumn.getCellObservableValue(index).getValue());
        studentEmailTF.setText(studentEmailTableColumn.getCellObservableValue(index).getValue());
        studentStreetTF.setText(studentStreetTableColumn.getCellObservableValue(index).getValue());
        studentPCTF.setText(studentPCTableColumn.getCellObservableValue(index).getValue().toString());
        studentColonyTF.setText(studentColonyTableColumn.getCellObservableValue(index).getValue());
        studentCityTF.setText(studentCityTableColumn.getCellObservableValue(index).getValue());
        studentStateTF.setText(studentStateTableColumn.getCellObservableValue(index).getValue());
        studentCountryTF.setText(studentCountryTableColumn.getCellObservableValue(index).getValue());
        studentGenderComboBox.setValue(studentGenderTableColumn.getCellObservableValue(index).getValue());
        studentDegreeComboBox.setValue(studentDegreeTableColumn.getCellObservableValue(index).getValue());
        studentGroupComboBox.setValue(studentGroupTableColumn.getCellObservableValue(index).getValue());
        BirthDate studentBirthDate = null;
        for (Student student : studentGroupComboBox.getValue().getStudentList()) {
            if (student.getID().equals(studentIdTableColumn.getCellObservableValue(index).getValue())) {
                studentBirthDate = student.getBirthDate();
            }
        }
        assert studentBirthDate != null;
        studentDatePicker.setValue(studentBirthDate.getLocalDate());
        enableDisableStudentAttributes(true);
        studentEditButton.setDisable(false);
        studentCancelButton.setText("Unselect");
        studentCancelButton.setDisable(false);
        studentAcceptButton.setText("Update");
        studentAcceptButton.setDisable(true);
        studentNewButton.setDisable(false);
    }

    public void studentEditUser(ActionEvent event) {
        enableDisableStudentAttributes(false);
        studentAcceptButton.setDisable(false);
        studentEditButton.setDisable(true);
    }

    public void CreateSubject(ActionEvent actionEvent) {
        degreeService.setDegree(teacherDegreeComboBox.getValue());
        degreeService.createSubject(teacherSemesterSubjectComboBox.getValue(), teacherSubjectTF.getText());
    }

//    public void subjectManagement(MouseEvent mouseEvent) {
//        int index = teacherList.getSelectionModel().getSelectedIndex();
//        for (Teacher teacher : teacherDegreeComboBox.getValue().getTeacherList()) {
//            if (teacher.getLicense().equals(teacherLicenseTableColumn.getCellObservableValue(index).getValue())) {
//                if (teacherAssignSubjectButton.isPressed()) {
//                    teacher.assignSubject(teacherAssignSubjectComboBox.getValue());
//                } else if (teacherUnassignSubjectButton.isPressed()) {
//                    teacher.unassignSubject(teacherUnassignSubjectComboBox.getValue());
//                }
//            }
//        }
//    }

    public void unassignSubject(ActionEvent actionEvent) {
        teacherService.getTeacher().unassignSubject(teacherUnassignSubjectComboBox.getValue());
    }

    public void assignSubject(ActionEvent actionEvent) {
        teacherService.getTeacher().assignSubject(teacherAssignSubjectComboBox.getValue());
    }

    public void cancelCreateEditStudent(ActionEvent actionEvent) {
        studentAcceptButton.setDisable(true);
        studentNameTF.clear();
        studentLastNameTF.clear();
        studentPhoneTF.clear();
        studentEmailTF.clear();
        studentStreetTF.clear();
        studentPCTF.clear();
        studentColonyTF.clear();
        studentCityTF.clear();
        studentStateTF.clear();
        studentCountryTF.clear();
        studentGenderComboBox.setValue(null);
        studentDatePicker.setValue(null);
        studentDegreeComboBox.setValue(null);
        studentGroupComboBox.setValue(null);
        studentImageView.setImage(null);
        enableDisableStudentAttributes(true);
        studentCancelButton.setDisable(true);
        studentEditButton.setDisable(true);
    }

    public void cancelCreateEditTeacher(ActionEvent actionEvent) {
        teacherAcceptButton.setDisable(true);
        teacherNameTF.clear();
        teacherLastNameTF.clear();
        teacherLicenseTF.clear();
        teacherSpecializationTF.clear();
        teacherPhoneTF.clear();
        teacherEmailTF.clear();
        teacherStreetTF.clear();
        teacherPCTF.clear();
        teacherColonyTF.clear();
        teacherCityTF.clear();
        teacherStateTF.clear();
        teacherCountryTF.clear();
        teacherGenderComboBox.setValue(null);
        teacherDatePicker.setValue(null);
        teacherDegreeComboBox.setValue(null);
        teacherSemesterSubjectComboBox.setValue(null);
        teacherAssignSubjectComboBox.setValue(null);
        teacherAssignSubjectSemesterComboBox.setValue(null);
        teacherUnassignSubjectComboBox.setValue(null);
        teacherUnassignSubjectSemesterComboBox.setValue(null);
        teacherSubjectTF.clear();
        teacherCancelButton.setDisable(true);
        teacherEditButton.setDisable(true);
        teacherCancelButton.setDisable(true);
    }

    public void enableDisableStudentAttributes(boolean bool) {
        studentNameTF.setDisable(bool);
        studentLastNameTF.setDisable(bool);
        studentPhoneTF.setDisable(bool);
        studentEmailTF.setDisable(bool);
        studentStreetTF.setDisable(bool);
        studentPCTF.setDisable(bool);
        studentColonyTF.setDisable(bool);
        studentCityTF.setDisable(bool);
        studentStateTF.setDisable(bool);
        studentCountryTF.setDisable(bool);
        studentGenderComboBox.setDisable(bool);
        studentDegreeComboBox.setDisable(bool);
        studentGroupComboBox.setDisable(bool);
        studentDatePicker.setDisable(bool);
        studentUploadImageButton.setDisable(bool);
    }

    public void eraseAllStudentAttributes() {
        studentNameTF.clear();
        studentLastNameTF.clear();
        studentPhoneTF.clear();
        studentEmailTF.clear();
        studentStreetTF.clear();
        studentPCTF.clear();
        studentColonyTF.clear();
        studentCityTF.clear();
        studentStateTF.clear();
        studentCountryTF.clear();
        studentGenderComboBox.setValue(null);
        studentDatePicker.setValue(null);
        studentDegreeComboBox.setValue(null);
        studentGroupComboBox.setValue(null);
        studentImageView.setImage(null);
    }

    public void enableDisableTeacherAttributes(boolean bool) {
        teacherNameTF.setDisable(bool);
        teacherLastNameTF.setDisable(bool);
        teacherLicenseTF.setDisable(bool);
        teacherSpecializationTF.setDisable(bool);
        teacherPhoneTF.setDisable(bool);
        teacherEmailTF.setDisable(bool);
        teacherStreetTF.setDisable(bool);
        teacherPCTF.setDisable(bool);
        teacherColonyTF.setDisable(bool);
        teacherCityTF.setDisable(bool);
        teacherStateTF.setDisable(bool);
        teacherCountryTF.setDisable(bool);
        teacherGenderComboBox.setDisable(bool);
        teacherDatePicker.setDisable(bool);
        teacherDegreeComboBox.setDisable(bool);
        teacherSemesterSubjectComboBox.setDisable(bool);
        teacherSubjectTF.setDisable(bool);
        teacherAssignSubjectSemesterComboBox.setDisable(bool);
        teacherAssignSubjectComboBox.setDisable(bool);
        teacherUnassignSubjectSemesterComboBox.setDisable(bool);
        teacherUnassignSubjectSemesterComboBox.setDisable(bool);
    }

    public void eraseAllTeacherAttributes() {
        teacherNameTF.clear();
        teacherLastNameTF.clear();
        teacherLicenseTF.clear();
        teacherSpecializationTF.clear();
        teacherPhoneTF.clear();
        teacherEmailTF.clear();
        teacherStreetTF.clear();
        teacherPCTF.clear();
        teacherColonyTF.clear();
        teacherCityTF.clear();
        teacherStateTF.clear();
        teacherCountryTF.clear();
        teacherGenderComboBox.setValue(null);
        teacherDatePicker.setValue(null);
        teacherDegreeComboBox.setValue(null);
        teacherSemesterSubjectComboBox.setValue(null);
        teacherSubjectTF.clear();
        teacherAssignSubjectSemesterComboBox.setValue(null);
        teacherAssignSubjectComboBox.setValue(null);
        teacherUnassignSubjectComboBox.setValue(null);
        teacherUnassignSubjectComboBox.setValue(null);
    }

    public void teacherSelectUser(MouseEvent mouseEvent) {
        int index = teacherList.getSelectionModel().getSelectedIndex();
        teacherNameTF.setText(teacherNameTableColumn.getCellObservableValue(index).getValue());
        teacherLastNameTF.setText(teacherLNTableColumn.getCellObservableValue(index).getValue());
        teacherLicenseTF.setText(teacherLicenseTableColumn.getCellObservableValue(index).getValue());
        teacherSpecializationTF.setText(teacherSpecializationTableColumn.getCellObservableValue(index).getValue());
        teacherPhoneTF.setText(teacherPhoneTableColumn.getCellObservableValue(index).getValue());
        teacherEmailTF.setText(teacherEmailTableColumn.getCellObservableValue(index).getValue());
        teacherStreetTF.setText(teacherStreetTableColumn.getCellObservableValue(index).getValue());
        teacherPCTF.setText(teacherPCTableColumn.getCellObservableValue(index).getValue().toString());
        teacherColonyTF.setText(teacherColonyTableColumn.getCellObservableValue(index).getValue());
        teacherCityTF.setText(teacherCityTableColumn.getCellObservableValue(index).getValue());
        teacherStateTF.setText(teacherStateTableColumn.getCellObservableValue(index).getValue());
        teacherCountryTF.setText(teacherCountryTableColumn.getCellObservableValue(index).getValue());
        teacherGenderComboBox.setValue(teacherGenderTableColumn.getCellObservableValue(index).getValue());
        teacherDegreeComboBox.setValue(teacherDegreeTableColumn.getCellObservableValue(index).getValue());
        BirthDate teacherBirthDate = null;
        for (Teacher teacher : teacherDegreeComboBox.getValue().getTeacherList()) {
            if (teacher.getLicense().equals(teacherLicenseTableColumn.getCellObservableValue(index).getValue())) {
                teacherBirthDate = teacher.getBirthDate();
            }
        }
        assert teacherBirthDate != null;
        teacherDatePicker.setValue(teacherBirthDate.getLocalDate());
        enableDisableTeacherAttributes(true);
        teacherEditButton.setDisable(false);
        teacherCancelButton.setText("Unselect");
        teacherCancelButton.setDisable(false);
        teacherAcceptButton.setText("Update");
        teacherAcceptButton.setDisable(true);
        teacherNewButton.setDisable(false);
    }

    public void teacherEditUser(ActionEvent actionEvent) {
        enableDisableTeacherAttributes(false);
        teacherAcceptButton.setDisable(false);
        teacherEditButton.setDisable(true);
    }
}
