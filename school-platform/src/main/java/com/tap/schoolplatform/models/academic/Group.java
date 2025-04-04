package com.tap.schoolplatform.models.academic;

import com.tap.schoolplatform.models.academic.enums.Shift;
import com.tap.schoolplatform.models.users.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;

import java.util.*;

public class Group {
    private String ID;
    private Degree degree;
    private Integer semester;
    private Shift shift;
    private final ObservableSet<Student> studentSet = FXCollections.observableSet(new TreeSet<>(Comparator.comparing(Student::getLastName))); // Convert to a set : OK
    private final ObservableList<Student> studentList = FXCollections.observableArrayList();

    public Group(Degree degree, int semester, Shift shift) { // Check to add instantaneously to the degree when create group : OK?
        this.degree = degree;
        this.semester = semester;
        this.shift = shift;
        degree.addGroup(this); // Check the degree service for this implementation
        generateID();
        studentList.addAll(studentSet);
        studentSet.addListener((SetChangeListener.Change<? extends Student> change) -> {
            if (change.wasAdded()) studentList.add(change.getElementAdded());
            else if (change.wasRemoved()) studentList.remove(change.getElementRemoved());
        });
    }

    private void generateID() {
        int index = degree.getGroupList(semester).size(); // Check index
        StringBuilder degreeInitials = new StringBuilder();
        for (char character : degree.getName().toCharArray()) {
            if (Character.isUpperCase(character)) {
                degreeInitials.append(character);
            }
        }
        String shift = this.shift == Shift.MORNINGS ? "M" : "E";
        ID = String.format("%d%s-%d%s",
                semester,
                degreeInitials,
                index,
                shift
        );
    }

    public String getID() {
        return ID;
    }

    public Degree getDegree() {
        return degree;
    }

    /**Remove {@code this} group from the current degree, set the new {@code degree} as the current degree and add {@code this} group to the current degree on the current semester using {@link Degree#addGroup(Group)}:
     * <blockquote><pre>
     *     public void setDegree(Degree degree) {
     *         this.degree = degree;
     *         degree.addGroup(this);
     *         generateId();
     *     }
     * </pre></blockquote>*/
    public void setDegree(Degree degree) {
        this.degree.removeGroup(this);
        this.degree = degree;
        this.degree.addGroup(this);
        generateID();
    }

    public Integer getSemester() {
        return semester;
    }

    /**Remove {@code this} group from the current degree, set the new {@code semester} as the current semester and add {@code this} group to the current degree on the current semester using {@link Degree#addGroup(Group)}:
     * <blockquote><pre>
     *     public void setSemester(int semester) {
     *         degree.removeGroup(this);
     *         this.semester = semester;
     *         degree.addGroup(this);
     *         generateId();
     *     }
     * </pre></blockquote>*/
    public void setSemester(int semester) {
        degree.removeGroup(this);
        this.semester = semester;
        degree.addGroup(this);
        generateID();
    }

    public Shift getShift() {
        return shift;
    }
    public void setShift(Shift shift) {
        this.shift = shift;
        generateID();
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

    public ObservableList<Subject> getSubjectList() { // Should I make it unmodifiable? : I think it works
        return degree.getSubjectList(semester);
    }
}