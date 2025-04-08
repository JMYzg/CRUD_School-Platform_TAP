package com.tap.schoolplatform.models.users;

import com.tap.schoolplatform.models.academic.*;
import com.tap.schoolplatform.models.academic.enums.Semester;
import com.tap.schoolplatform.models.enums.UserRole;
import com.tap.schoolplatform.models.shared.*;
import com.tap.schoolplatform.models.enums.Gender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class Teacher extends User {

    private String license;
    private Degree degree;
    private String specialization;
    private final ObservableList<Subject> assignedSubjects = FXCollections.observableArrayList(); // Make it a set
    private final ObservableMap<Semester, ObservableList<Subject>> subjectLists = FXCollections.observableHashMap();

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
        return FXCollections.unmodifiableObservableList(subjectLists.get(semester));
    }

    public void assignSubject(Subject subject) {
        subject.setTeacher(this);
//        assignedSubjects.add(subject);
        subjectLists.computeIfAbsent(subject.getSemester(), k -> FXCollections.observableArrayList()).add(subject);
    }
    public void unassignSubject(Subject subject) {
        subject.setTeacher(null);
//        assignedSubjects.remove(subject);
        subjectLists.get(subject.getSemester()).remove(subject);
    }
}
