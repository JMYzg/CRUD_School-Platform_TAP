package com.tap.schoolplatform.controllers.teacherControllers.studentTeacherViewControllers;

import com.tap.schoolplatform.controllers.ViewController;
import com.tap.schoolplatform.models.academic.Grade;
import com.tap.schoolplatform.models.academic.keys.GradeKey;
import com.tap.schoolplatform.models.academic.keys.GroupKey;
import com.tap.schoolplatform.models.users.Student;
import com.tap.schoolplatform.utils.SharedData;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import static com.tap.schoolplatform.controllers.teacherControllers.TeacherViewController.currentGroup;

public class GradeOptionTeacherViewController extends ViewController {

    public Label groupIdLabel;
    public Label semesterLabel;
    public Label shiftLabel;
    public TableView<Student> gradesTable;
    public TableColumn<Student, String> idTableColumn;
    public TableColumn<Student, String> lastNameTableColumn;
    public TableColumn<Student, Integer> unit1TableColumn;
    public TableColumn<Student, Integer> unit2TableColumn;
    public TableColumn<Student, Integer> unit3TableColumn;
    public TableColumn<Student, Integer> unit4TableColumn;
    public TableColumn<Student, Integer> unit5TableColumn;
    public TableColumn<Student, Integer> unit6TableColumn;
    public TableColumn<Student, Integer> unit7TableColumn;

    private final SharedData sharedDataObject = SharedData.getInstance();
    public Label groupNameLabel;
    public Label groupSemesterLabel;
    public Label groupShiftLabel;

//    GroupKey groupKey = new GroupKey(currentGroup.getSubjectList().get(0), currentGroup.getUnit);

    public void initialize() {
        groupNameLabel.setText(currentGroup.getID());
        groupSemesterLabel.setText(currentGroup.getSemester().toString());
        groupShiftLabel.setText(currentGroup.getShift().toString());
    idTableColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
    lastNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    unit1TableColumn.setCellValueFactory(new PropertyValueFactory<>("unit1"));
    unit2TableColumn.setCellValueFactory(new PropertyValueFactory<>("unit2"));
    unit3TableColumn.setCellValueFactory(new PropertyValueFactory<>("unit3"));
    unit4TableColumn.setCellValueFactory(new PropertyValueFactory<>("unit4"));
    unit5TableColumn.setCellValueFactory(new PropertyValueFactory<>("unit5"));
    unit6TableColumn.setCellValueFactory(new PropertyValueFactory<>("unit6"));
    unit7TableColumn.setCellValueFactory(new PropertyValueFactory<>("unit7"));
    initializeTable(gradesTable);
    }

    public void initializeTable(TableView<Student> table) {
        gradesTable.setItems(sharedDataObject.getStudents().filtered(student -> student.getGroup().equals(currentGroup)));
    }
}
