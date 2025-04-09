package com.tap.schoolplatform.models.users;

import com.tap.schoolplatform.models.academic.*;
import com.tap.schoolplatform.models.academic.enums.Semester;
import com.tap.schoolplatform.models.enums.UserRole;
import com.tap.schoolplatform.models.shared.*;
import com.tap.schoolplatform.models.enums.Gender;
import javafx.collections.*;

import java.util.HashSet;
import java.util.Objects;

public class Teacher extends User {

    private String license;
    private Degree degree;
    private String specialization;
    private final ObservableList<Subject> assignedSubjects = FXCollections.observableArrayList(); // Make it a set
    private final ObservableMap<Semester, ObservableList<Subject>> subjectLists = FXCollections.observableHashMap();
    private final ObservableMap<Semester, ObservableSet<Subject>> subjectSets = FXCollections.observableHashMap();

    public Teacher(/*Degree degree, String license, String specialization,*/ String name, String lastName, BirthDate birthDate, String email, String phone, Address address, Gender gender) {
        super(name, lastName, birthDate, email, phone, address, gender);
        super.setRole(UserRole.TEACHER);
//        this.license = license;
//        this.degree = degree;
//        this.specialization = specialization;
    }

    public String getLicense() {
        return license;
    }
    public void setLicense(String license) {
        this.license = license;
    }

    public Degree getDegree() {
        return degree;
    }
    public void setDegree(Degree degree) {
        if (this.degree != null) this.degree.removeTeacher(this);
        this.degree = degree;
        this.degree.addTeacher(this);
    }

    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public ObservableList<Subject> getAssignedSubjectList(Semester semester) {
//        return FXCollections.unmodifiableObservableList(assignedSubjects);
        System.out.println(subjectLists);
        return FXCollections.unmodifiableObservableList(subjectLists.get(semester));
    }

    public ObservableMap<Semester, ObservableList<Subject>> getSubjectLists() {
        return FXCollections.unmodifiableObservableMap(subjectLists);
    }

    public void assignSubject(Subject subject) {
        subject.setTeacher(this);
//        assignedSubjects.add(subject);
//        subjectLists.computeIfAbsent(subject.getSemester(), k -> FXCollections.observableArrayList()).add(subject);
        ObservableSet<Subject> subjectSet = subjectSets.get(subject.getSemester());
        if (subjectSet == null) {
            subjectSet = FXCollections.observableSet(new HashSet<>());
            ObservableList<Subject> subjectList = FXCollections.observableArrayList(subjectSet);
            subjectSets.put(subject.getSemester(), subjectSet);
            subjectLists.put(subject.getSemester(), subjectList);
            subjectSet.addListener((SetChangeListener.Change<? extends Subject> change) -> {
                if (change.wasAdded()) subjectList.add(change.getElementAdded());
                else if (change.wasRemoved()) subjectList.remove(change.getElementRemoved());
            });
        }
        subjectSet.add(subject);
    }
    public void unassignSubject(Subject subject) {
        subject.setTeacher(null);
//        assignedSubjects.remove(subject);
//        subjectLists.get(subject.getSemester()).remove(subject);
        ObservableSet<Subject> subjectSet = subjectSets.get(subject.getSemester());
        subjectSet.remove(subject);
        if (subjectSet.isEmpty()) subjectSets.remove(subject.getSemester());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Teacher teacher = (Teacher) object;
        return Objects.equals(license, teacher.getLicense());
    }
}
