package com.tap.schoolplatform.controllers.teacherControllers.studentTeacherViewControllers;

import com.tap.schoolplatform.auth.LoginService;
import com.tap.schoolplatform.models.users.Student;
import com.tap.schoolplatform.models.users.Teacher;
import com.tap.schoolplatform.utils.SharedData;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import static com.tap.schoolplatform.controllers.teacherControllers.TeacherViewController.currentGroup;

public class StudentOptionTeacherViewController {
    public Label groupName;
    public Label semesterGroup;
    public Label groupShift;
    public TableColumn<Student, Integer> idTableColumn;
    public TableColumn<Student, String> lastNameTableColumn;
    public TableColumn<Student, String> nameTableColumn;
    public TableColumn<Student, String> emailTableColumn;
    public TableView<Student> table;
    Teacher currentTeacher = (Teacher) LoginService.getCurrentUser();

    private final SharedData sharedDataObject = SharedData.getInstance();

    public void initialize() {
        groupName.setText(currentGroup.getID());
        semesterGroup.setText(currentGroup.getSemester().toString());
        groupShift.setText(currentGroup.getShift().toString());
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        lastNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailTableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        initializeTable(table);
    }

    public void initializeTable(TableView<Student> table) {
        table.setItems(sharedDataObject.getStudents().filtered(student -> student.getGroup().equals(currentGroup)));
    }

}
