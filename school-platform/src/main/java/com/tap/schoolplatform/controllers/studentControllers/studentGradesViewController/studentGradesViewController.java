package com.tap.schoolplatform.controllers.studentControllers.studentGradesViewController;

import com.tap.schoolplatform.auth.LoginService;
import com.tap.schoolplatform.controllers.ViewController;
import com.tap.schoolplatform.models.academic.Subject;
import com.tap.schoolplatform.models.users.Student;
import com.tap.schoolplatform.models.users.Teacher;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class studentGradesViewController extends ViewController {
    public TableView<Subject> gradesTable;
    public TableColumn<Subject, String> subjectsTableColumn;
    public TableColumn<Subject, Integer> unit1TableColumn;
    public TableColumn<Subject, Integer> unit2TableColumn;
    public TableColumn<Subject, Integer> unit3TableColumn;
    public TableColumn<Subject, Integer> unit4TableColumn;
    public TableColumn<Subject, Integer> unit5TableColumn;
    public TableColumn<Subject, Integer> unit6TableColumn;
    public TableColumn<Subject, Integer> unit7TableColumn;

    Student currentStudent = (Student) LoginService.getCurrentUser();

    public void initialize() {
        subjectsTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        unit1TableColumn.setCellValueFactory(new PropertyValueFactory<>("unit1"));
        unit2TableColumn.setCellValueFactory(new PropertyValueFactory<>("unit2"));
        unit3TableColumn.setCellValueFactory(new PropertyValueFactory<>("unit3"));
        unit4TableColumn.setCellValueFactory(new PropertyValueFactory<>("unit4"));
        unit5TableColumn.setCellValueFactory(new PropertyValueFactory<>("unit5"));
        unit6TableColumn.setCellValueFactory(new PropertyValueFactory<>("unit6"));
        unit7TableColumn.setCellValueFactory(new PropertyValueFactory<>("unit7"));
        gradesTable.setItems(currentStudent.getGroup().getSubjectList());
    }
}
