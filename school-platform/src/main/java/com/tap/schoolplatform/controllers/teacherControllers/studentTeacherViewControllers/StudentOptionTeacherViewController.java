package com.tap.schoolplatform.controllers.teacherControllers.studentTeacherViewControllers;

import com.tap.schoolplatform.controllers.ViewController;
import com.tap.schoolplatform.models.users.Student;
import com.tap.schoolplatform.utils.SharedData;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.UUID;

public class StudentOptionTeacherViewController extends ViewController {

    public TableColumn<Student, String> emailTableColumn;
    public TableColumn<Student, String> nameTableColumn;
    public TableColumn<Student, String> lastNameTableColumn;
    public TableColumn<Student, UUID> idTableColumn;
    public TableView<Student> table;
    private final SharedData sharedDataObject = SharedData.getInstance();

    public void initialize() {
        emailTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        lastNameTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        idTableColumn.setCellValueFactory(new PropertyValueFactory<Student, UUID>("ID"));
        table.setItems(sharedDataObject.getStudents());
    }

}
