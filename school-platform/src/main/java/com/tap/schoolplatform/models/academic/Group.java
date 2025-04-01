package com.tap.schoolplatform.models.academic;

import com.tap.schoolplatform.models.academic.enums.Shift;
import com.tap.schoolplatform.models.users.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;

import java.util.*;

public class Group {
    private String id;
    private Degree degree;
    private int semester;
    private Shift shift;
    private final ObservableSet<Student> studentSet = FXCollections.observableSet(new TreeSet<>(Comparator.comparing(Student::getLastName))); // Convert to a set : OK
    private final ObservableList<Student> studentList = FXCollections.observableArrayList();

    public Group(Degree degree, int semester, Shift shift) { // Check to add instantaneously to the degree when create group : OK?
        this.degree = degree;
        this.semester = semester;
        this.shift = shift;
        degree.addGroup(this); // Check the degree service for this implementation
        generateId();
        studentList.addAll(studentSet);
        studentSet.addListener((SetChangeListener.Change<? extends Student> change) -> {
            if (change.wasAdded()) studentList.add(change.getElementAdded());
            else if (change.wasRemoved()) studentList.remove(change.getElementRemoved());
        });
    }

    private void generateId() {
        int index = degree.getGroupList(semester).size(); // Check index
        StringBuilder degreeInitials = new StringBuilder();
        for (char character : degree.getName().toCharArray()) {
            if (Character.isUpperCase(character)) {
                degreeInitials.append(character);
            }
        }
        String shift = this.shift == Shift.MORNINGS ? "M" : "E";
        id = String.format("%d%s-%d%s",
                semester,
                degreeInitials,
                index,
                shift
        );
    }

    public String getId() {
        return id;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
        generateId();
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
        generateId();
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
        generateId();
    }

    public ObservableList<Student> getStudentList() {
        return FXCollections.unmodifiableObservableList(studentList);
    }

    public void addStudent(Student student) {
        studentSet.add(student);
    }

    public void removeStudent(Student student) {
        studentSet.remove(student);
    }

    public ObservableList<Subject> getSubjectList() { // Should I make it unmodifiable?
        return degree.getSubjectList(semester);
    }
}