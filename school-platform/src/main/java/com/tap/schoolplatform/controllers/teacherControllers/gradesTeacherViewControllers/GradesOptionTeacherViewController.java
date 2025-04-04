package com.tap.schoolplatform.controllers.teacherControllers.gradesTeacherViewControllers;

import com.tap.schoolplatform.controllers.ViewController;
import com.tap.schoolplatform.models.users.Student;
import com.tap.schoolplatform.utils.SharedData;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.UUID;

public class GradesOptionTeacherViewController extends ViewController {
    public TableView<Student> tableView;
    public TableColumn<Student, UUID> idTableColumn;
    public TableColumn<Student, String> lastNameTableColumn;
    public TableColumn<Student, Integer> unit1TableColumn;
    public TableColumn<Student, Integer> unit2TableColumn;
    public TableColumn<Student, Integer> unit3TableColumn;
    public TableColumn<Student, Integer> unit4TableColumn;
    public TableColumn<Student, Integer> unit5TableColumn;
    public TableColumn<Student, Integer> unit6TableColumn;
    public TableColumn<Student, Integer> unit7TableColumn;
    private final SharedData sharedDataObject = SharedData.getInstance();

    public void initialize() {
        idTableColumn.setCellValueFactory(new PropertyValueFactory<Student, UUID>("ID"));
        lastNameTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));

        tableView.setItems(sharedDataObject.getStudents());

    }

}
