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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
        initializeServices();
        initializeStudentUI();
        initializeTeacherUI();
        initializeStudentTableColumns();
        initializeTeacherTableColumns();
        setupDataBindings();
        initializeTeacherSubjectManagement();
    }

    public void initializeTeacherSubjectManagement() {
        teacherAssignSubjectSemesterComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            teacherAssignSubjectComboBox.getItems().clear();
            if (newValue != null && degreeService.getDegree() != null) {
                List<Subject> subjectList = degreeService.getDegree().getSubjectList(newValue);
                if (subjectList != null && teacherService.getTeacher().getSubjectLists().containsKey(newValue)) {
                    teacherAssignSubjectComboBox.getItems().setAll(
                            subjectList.stream()
                                    .filter(subject -> !teacherService.getTeacher().getAssignedSubjectList(newValue).contains(subject))
                                    .collect(Collectors.toCollection(FXCollections::observableArrayList))
                    );
                } else {
                    teacherAssignSubjectComboBox.getItems().setAll(subjectList);
                }
            }
        });

        teacherUnassignSubjectSemesterComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            teacherUnassignSubjectComboBox.getItems().clear();
            if (newValue != null && teacherService.getTeacher() != null) {
                teacherUnassignSubjectComboBox.getItems().setAll(teacherService.getTeacher().getAssignedSubjectList(newValue));
            }
        });
    }

    private void initializeServices() {
        adminService = new AdministratorService();
        teacherService = new TeacherService();
        degreeService = new DegreeService();
        subjectService = new SubjectService();
    }

    private void initializeStudentUI() {
        // Initialize student buttons
        studentNewButton.setDisable(true);
        studentEditButton.setDisable(true);
        studentCancelButton.setDisable(true);
        studentAcceptButton.setText("Create");
        studentCancelButton.setText("Unselect");

        // Initialize student input controls
        studentGenderComboBox.setEditable(false);
        studentGenderComboBox.getItems().setAll(Gender.values());
        studentDatePicker.setEditable(false);
        studentDegreeComboBox.setEditable(false);
        studentGroupComboBox.setEditable(false);

        // Setup degree selection listener
        setupStudentDegreeListener();
    }

    private void setupStudentDegreeListener() {
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
    }

    private void initializeTeacherUI() {
        // Initialize teacher buttons
        teacherNewButton.setDisable(true);
        teacherEditButton.setDisable(true);
        teacherCancelButton.setDisable(true);
        teacherAcceptButton.setText("Create");
        teacherCancelButton.setText("Unselect");

        // Initialize teacher input controls
        teacherGenderComboBox.setEditable(false);
        teacherGenderComboBox.getItems().setAll(Gender.values());
        teacherDegreeComboBox.setEditable(false);
        teacherDatePicker.setEditable(false);
        teacherSubjectTF.setEditable(true);
        teacherAssignSubjectComboBox.setEditable(false);
        teacherUnassignSubjectComboBox.setEditable(false);
        refreshCBDegree(teacherDegreeComboBox);

        // Initialize semester combo boxes
        initializeSemesterComboBoxes();

        // Set up subject management
        disableSubjectManagementComponents();
        setupTeacherDegreeListener();
        setupSemesterListeners();
    }

    private void initializeSemesterComboBoxes() {
        // Configure semester combo boxes
        teacherSemesterSubjectComboBox.getItems().setAll(Semester.values());
        teacherSemesterSubjectComboBox.setEditable(false);

        teacherAssignSubjectSemesterComboBox.getItems().setAll(Semester.values());
        teacherAssignSubjectSemesterComboBox.setEditable(false);

        teacherUnassignSubjectSemesterComboBox.getItems().setAll(Semester.values());
        teacherUnassignSubjectSemesterComboBox.setEditable(false);
    }

    private void setupTeacherDegreeListener() {
        teacherDegreeComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            boolean degreeSelected = newVal != null;
            // Enable/disable semester combo boxes
            teacherSubjectTF.setDisable(!degreeSelected);
            teacherSemesterSubjectComboBox.setDisable(!degreeSelected);
            teacherAssignSubjectSemesterComboBox.setDisable(!degreeSelected);
            teacherUnassignSubjectSemesterComboBox.setDisable(!degreeSelected);

            // Disable dependent components if degree is deselected
            if (!degreeSelected) {
                disableDependentComponents();
            }
        });
    }

    private void initializeStudentTableColumns() {
        studentIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        studentNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        studentLNTableColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        studentDegreeTableColumn.setCellValueFactory(new PropertyValueFactory<>("degree"));
        studentGroupTableColumn.setCellValueFactory(new PropertyValueFactory<>("group"));
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
    }

    private void initializeTeacherTableColumns() {
        teacherLicenseTableColumn.setCellValueFactory(new PropertyValueFactory<>("license"));
        teacherNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        teacherLNTableColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        teacherSpecializationTableColumn.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        teacherDegreeTableColumn.setCellValueFactory(new PropertyValueFactory<>("degree"));
        teacherEmailTableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        teacherPhoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        teacherStreetTableColumn.setCellValueFactory(new PropertyValueFactory<>("street"));
        teacherPCTableColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        teacherColonyTableColumn.setCellValueFactory(new PropertyValueFactory<>("colony"));
        teacherCityTableColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        teacherStateTableColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
        teacherCountryTableColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        teacherGenderTableColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        teacherAgeTableColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
    }

    private void setupDataBindings() {
        studentList.setItems(sharedDataObject.getStudents());
        teacherList.setItems(sharedDataObject.getTeachers());
    }

    // Existing methods remain unchanged
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

    public void setupTeacherDegreeComboBoxSubjects(Semester semester) {
//        teacherAssignSubjectComboBox.getItems().setAll(sharedDataObject.);
    }

    public void addStudent(ActionEvent event) {
        if (studentAcceptButton.getText().equals("Create")) {
            handleCreateStudent();
        } else if (studentAcceptButton.getText().equals("Update")) {
            handleUpdateStudent();
        }
    }

    private void handleCreateStudent() {
        if (!validateStudentForm()) {
            return;
        }

        adminService = new AdministratorService();
        if (studentImageView == null) {
            studentImageView = new ImageView(studentUploadImage());
        }

        // Create the student
        adminService.createStudent(
                studentGroupComboBox.getValue(),
                studentImageView.getImage(),
                studentNameTF.getText(),
                studentLastNameTF.getText(),
                createBirthDate(studentDatePicker),
                studentEmailTF.getText(),
                studentPhoneTF.getText(),
                createAddress(studentStreetTF, studentPCTF, studentColonyTF, studentCityTF, studentStateTF, studentCountryTF),
                studentGenderComboBox.getValue()
        );

        alertInfo("", "Student added correctly", "");
        clearStudentForm();
    }

    private void handleUpdateStudent() {
        if (!validateStudentForm()) {
            return;
        }

        adminService.updateUser(
                studentList.getSelectionModel().getSelectedItem(),
                createUserDTO(studentList.getSelectionModel().getSelectedItem())
        );

        alertInfo("", "Student updated correctly", "");
        enableDisableStudentAttributes(true);
        clearStudentForm();
        studentAcceptButton.setDisable(true);
        studentEditButton.setDisable(false);
    }

    private boolean validateStudentForm() {
        if (isAnyFieldEmpty()) {
            alertError("Error", "Please make sure to fill in all the text boxes");
            return false;
        }

        if (!verifyName(studentNameTF.getText())) {
            alertError("Invalid name", "Please write a valid name");
            return false;
        }

        if (!verifyName(studentLastNameTF.getText())) {
            alertError("Invalid last name", "Please write a valid last name");
            return false;
        }

        if (!verifyPhone(studentPhoneTF.getText())) {
            alertError("Invalid phone number", "Please write a valid phone number");
            return false;
        }

        if (!verifyEmail(studentEmailTF.getText())) {
            alertError("Invalid email", "Please write a valid email");
            return false;
        }

        return true;
    }

    private boolean isAnyFieldEmpty() {
        return studentNameTF.getText().isEmpty()
                || studentLastNameTF.getText().isEmpty()
                || studentPhoneTF.getText().isEmpty()
                || studentEmailTF.getText().isEmpty()
                || studentStreetTF.getText().isEmpty()
                || studentPCTF.getText().isEmpty()
                || studentColonyTF.getText().isEmpty()
                || studentCityTF.getText().isEmpty()
                || studentStateTF.getText().isEmpty()
                || studentCountryTF.getText().isEmpty()
                || studentGenderComboBox.getValue() == null
                || studentDatePicker.getValue() == null
                || studentDegreeComboBox.getValue() == null
                || studentGroupComboBox.getValue() == null
                || studentImageView == null;
    }

    private void clearStudentForm() {
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

    public Image studentUploadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose the image");
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
            if (areTeacherFieldsEmpty()) {
                alertError("Error", "Please make sure to fill all the text boxes");
                return;
            }

            if (!validateTeacherInputs()) {
                return; // Validation errors already displayed to user
            }

            // All validations passed, proceed with creating teacher
            adminService = new AdministratorService();
            adminService.createTeacher(
                    teacherDegreeComboBox.getValue(),
                    teacherNameTF.getText(),
                    teacherLastNameTF.getText(),
                    createBirthDate(teacherDatePicker),
                    teacherEmailTF.getText(),
                    teacherPhoneTF.getText(),
                    createAddress(teacherStreetTF, teacherPCTF, teacherColonyTF, teacherCityTF, teacherStateTF, teacherCountryTF),
                    teacherGenderComboBox.getValue(),
                    teacherLicenseTF.getText(),
                    teacherSpecializationTF.getText()
            );

            alertInfo("", "Teacher added correctly", "");
            clearTeacherFields();

        } else if (teacherAcceptButton.getText().equals("Update")) {
            if (areTeacherFieldsEmpty()) {
                alertError("Error", "Please make sure to fill all the text boxes");
                return;
            }

            if (!validateTeacherInputs()) {
                return; // Validation errors already displayed to user
            }

            // All validations passed, proceed with updating teacher
            adminService.updateUser(
                    teacherList.getSelectionModel().getSelectedItem(),
                    createUserDTO(teacherList.getSelectionModel().getSelectedItem())
            );

            alertInfo("", "Teacher updated correctly", "");
            enableDisableTeacherAttributes(true);
            eraseAllTeacherAttributes();
            teacherAcceptButton.setDisable(true);
            teacherEditButton.setDisable(false);
        }
    }

    private boolean areTeacherFieldsEmpty() {
        return teacherNameTF.getText() == null ||
                teacherLastNameTF.getText() == null ||
                teacherPhoneTF.getText() == null ||
                teacherEmailTF.getText() == null ||
                teacherStreetTF.getText() == null ||
                teacherPCTF.getText() == null ||
                teacherColonyTF.getText() == null ||
                teacherCityTF.getText() == null ||
                teacherStateTF.getText() == null ||
                teacherCountryTF.getText() == null ||
                teacherGenderComboBox.getValue() == null ||
                teacherDatePicker.getValue() == null ||
                teacherDegreeComboBox.getValue() == null;
    }

    private boolean validateTeacherInputs() {
        if (!verifyName(teacherNameTF.getText())) {
            alertError("Invalid name", "Please write a valid name");
            return false;
        }
        if (!verifyName(teacherLastNameTF.getText())) {
            alertError("Invalid last name", "Please write a valid last name");
            return false;
        }
        if (!verifyPhone(teacherPhoneTF.getText())) {
            alertError("Invalid phone number", "Please write a valid phone number");
            return false;
        }
        if (!verifyEmail(teacherEmailTF.getText())) {
            alertError("Invalid email", "Please write a valid email");
            return false;
        }
        return true;
    }

    private void clearTeacherFields() {
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

    public void createDegree(ActionEvent event) {
        // Implementation left unchanged
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
        userDTO.setBirthDate(createBirthDate(studentDatePicker));
        // If degree and group information is needed
        if (user instanceof Student) {
            userDTO.setDegree(studentDegreeComboBox.getValue());
            userDTO.setGroup(studentGroupComboBox.getValue());
        }
        if (user instanceof Teacher teacher) {
            userDTO.setLicense(teacherLicenseTF.getText());
            userDTO.setSpecialization(teacherSpecializationTF.getText());
            userDTO.setDegree(teacherDegreeComboBox.getValue());
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

    private static final String CREATE_MODE = "Create";
    private static final String UPDATE_MODE = "Update";
    private static final String UNSELECT_TEXT = "Unselect";
    private static final String CANCEL_TEXT = "Cancel";
    private static final String CLEAR_CONFIRMATION_HEADER = "Are you sure you want to clear delete all?";
    private static final String CLEAR_CONFIRMATION_MESSAGE = "You wont be able to recover the information";

    public void teacherClearAllAttributes(ActionEvent event) {
        clearEntityAttributes(
                teacherAcceptButton,
                this::eraseAllTeacherAttributes,
                this::enableDisableTeacherAttributes,
                teacherEditButton,
                teacherCancelButton,
                null  // No image to clear for teachers
        );
    }

    public void studentClearAllAttributes(ActionEvent event) {
//        File defaultImagePath = new File("images/roblox.png");
        Image defaultImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/roblox.png")));
        clearEntityAttributes(
                studentAcceptButton,
                this::eraseAllStudentAttributes,
                this::enableDisableStudentAttributes,
                studentEditButton,
                studentCancelButton,
                () -> studentImageView.setImage(defaultImage)
        );
    }

    /**
     * Template method for clearing entity attributes with consistent UI state management
     */
    private void clearEntityAttributes(
            Button acceptButton,
            Runnable eraseAttributesAction,
            Consumer<Boolean> attributeStateToggler,
            Button editButton,
            Button cancelButton,
            Runnable additionalCleanupAction) {

        if (acceptButton.getText().equals(CREATE_MODE)) {
            if (confirmationAlertIf(CLEAR_CONFIRMATION_MESSAGE, CLEAR_CONFIRMATION_HEADER)) {
                performClearAttributes(eraseAttributesAction, attributeStateToggler, editButton,
                        cancelButton, acceptButton, additionalCleanupAction);
            }
        } else if (acceptButton.getText().equals(UPDATE_MODE)) {
            performClearAttributes(eraseAttributesAction, attributeStateToggler, editButton,
                    cancelButton, acceptButton, additionalCleanupAction);
        }
    }

    private void performClearAttributes(
            Runnable eraseAttributesAction,
            Consumer<Boolean> attributeStateToggler,
            Button editButton,
            Button cancelButton,
            Button acceptButton,
            Runnable additionalCleanupAction) {

        eraseAttributesAction.run();
        attributeStateToggler.accept(false);
        editButton.setDisable(true);
        cancelButton.setText(UNSELECT_TEXT);
        cancelButton.setDisable(true);
        acceptButton.setText(CREATE_MODE);
        acceptButton.setDisable(false);

        if (additionalCleanupAction != null) {
            additionalCleanupAction.run();
        }
    }

    public void studentSelectUser(MouseEvent mouseEvent) {
        int selectedIndex = studentList.getSelectionModel().getSelectedIndex();
        populateStudentFormFields(selectedIndex);
        setupStudentFormForEditing();
    }

    private void populateStudentFormFields(int index) {
        // Basic information
        studentNameTF.setText(studentNameTableColumn.getCellObservableValue(index).getValue());
        studentLastNameTF.setText(studentLNTableColumn.getCellObservableValue(index).getValue());
        studentPhoneTF.setText(studentPhoneTableColumn.getCellObservableValue(index).getValue());
        studentEmailTF.setText(studentEmailTableColumn.getCellObservableValue(index).getValue());

        // Address information
        studentStreetTF.setText(studentStreetTableColumn.getCellObservableValue(index).getValue());
        studentPCTF.setText(studentPCTableColumn.getCellObservableValue(index).getValue().toString());
        studentColonyTF.setText(studentColonyTableColumn.getCellObservableValue(index).getValue());
        studentCityTF.setText(studentCityTableColumn.getCellObservableValue(index).getValue());
        studentStateTF.setText(studentStateTableColumn.getCellObservableValue(index).getValue());
        studentCountryTF.setText(studentCountryTableColumn.getCellObservableValue(index).getValue());

        // Academic information
        studentGenderComboBox.setValue(studentGenderTableColumn.getCellObservableValue(index).getValue());
        studentDegreeComboBox.setValue(studentDegreeTableColumn.getCellObservableValue(index).getValue());
        studentGroupComboBox.setValue(studentGroupTableColumn.getCellObservableValue(index).getValue());

        // Birth date handling
        String studentId = studentIdTableColumn.getCellObservableValue(index).getValue();
        BirthDate studentBirthDate = findStudentBirthDateById(studentId);
        assert studentBirthDate != null;
        studentDatePicker.setValue(studentBirthDate.getLocalDate());

        Image pfp = findStudentPFPById(studentId);
        studentImageView.setImage(pfp);
    }

    private BirthDate findStudentBirthDateById(String studentId) {
        for (Student student : studentGroupComboBox.getValue().getStudentList()) {
            if (student.getID().equals(studentId)) {
                return student.getBirthDate();
            }
        }
        return null;
    }

    public Image findStudentPFPById(String studentId) {
        for (Student student : studentGroupComboBox.getValue().getStudentList()) {
            if (student.getID().equals(studentId)) {
                return student.getProfilePicture();
            }
        }
        return null;
    }

    private void setupStudentFormForEditing() {
        enableDisableStudentAttributes(true);
        studentEditButton.setDisable(false);
        studentCancelButton.setText(UNSELECT_TEXT);
        studentCancelButton.setDisable(false);
        studentAcceptButton.setText(UPDATE_MODE);
        studentAcceptButton.setDisable(true);
        studentNewButton.setDisable(false);
    }

    public void studentEditUser(ActionEvent event) {
        enableDisableStudentAttributes(false);
        studentAcceptButton.setDisable(false);
        studentEditButton.setDisable(true);
    }

    public void createSubject(ActionEvent actionEvent) {
        degreeService.setDegree(teacherDegreeComboBox.getValue());
        degreeService.createSubject(teacherSemesterSubjectComboBox.getValue(), teacherSubjectTF.getText());
        alertInfo("", "Subject created correctly", "");
        teacherSemesterSubjectComboBox.setValue(null);
        teacherSubjectTF.setText(null);
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
//        degreeService.setDegree(teacherDegreeComboBox.getValue());
//        teacherService.setTeacher(degreeService.readTeacher(teacherLicenseTF.getText()));
        teacherService.getTeacher().unassignSubject(teacherUnassignSubjectComboBox.getValue());
//        teacherUnassignSubjectComboBox.getItems().setAll(teacherService.getTeacher().getAssignedSubjectList(teacherAssignSubjectSemesterComboBox.getValue()));
        alertInfo("", "Subject unassigned correctly", "");
        teacherUnassignSubjectSemesterComboBox.setValue(null);
        teacherUnassignSubjectComboBox.setValue(null);
    }

    public void assignSubject(ActionEvent actionEvent) {
//        degreeService.setDegree(teacherDegreeComboBox.getValue());
//        teacherService.setTeacher(degreeService.readTeacher(teacherLicenseTF.getText()));
        teacherService.getTeacher().assignSubject(teacherAssignSubjectComboBox.getValue());
        alertInfo("", "Subject assigned correctly", "");
        teacherAssignSubjectSemesterComboBox.setValue(null);
        teacherAssignSubjectComboBox.setValue(null);
//        teacherAssignSubjectComboBox.getItems().setAll(degreeService.getDegree().getSubjectList(teacherAssignSubjectSemesterComboBox.getValue()));
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
        enableDisableTeacherAttributes(true);
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

        degreeService.setDegree(teacherDegreeComboBox.getValue());
        teacherService.setTeacher(degreeService.readTeacher(teacherLicenseTF.getText()));
//        teacherUnassignSubjectComboBox.getItems().setAll(teacherService.getTeacher().getAssignedSubjectList(teacherAssignSubjectSemesterComboBox.getValue()));
//        teacherAssignSubjectComboBox.getItems().setAll(degreeService.getDegree().getSubjectList(teacherAssignSubjectSemesterComboBox.getValue()));
    }

    public void teacherEditUser(ActionEvent actionEvent) {
        enableDisableTeacherAttributes(false);
        teacherAcceptButton.setDisable(false);
        teacherEditButton.setDisable(true);
    }

    public void updateDegreeComboBox(MouseEvent mouseEvent) {
        refreshCBDegree(teacherDegreeComboBox);
        teacherSemesterSubjectComboBox.setValue(null);
        teacherSubjectTF.clear();
        teacherAssignSubjectSemesterComboBox.setValue(null);
        teacherAssignSubjectComboBox.setValue(null);
        teacherUnassignSubjectSemesterComboBox.setValue(null);
        teacherUnassignSubjectComboBox.setValue(null);
    }
}