package com.tap.schoolplatform.models.users;

import com.tap.schoolplatform.models.academic.*;
import com.tap.schoolplatform.models.enums.UserRole;
import com.tap.schoolplatform.models.shared.*;
import com.tap.schoolplatform.models.enums.Gender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Teacher extends User {

    private String license;
    private Degree degree;
    private String specialization;
    private final ObservableList<Subject> assignedSubjects = FXCollections.observableArrayList(); // Make it a set

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

    public ObservableList<Subject> getAssignedSubjectList() {
        return FXCollections.unmodifiableObservableList(assignedSubjects);
    }

    public void assignSubject(Subject subject) {
        subject.setTeacher(this);
        assignedSubjects.add(subject);
    }
    public void unassignSubject(Subject subject) {
        subject.setTeacher(null);
        assignedSubjects.remove(subject);
    }

    @Override
    public String toString() {
        return super.getName();
    }
}
