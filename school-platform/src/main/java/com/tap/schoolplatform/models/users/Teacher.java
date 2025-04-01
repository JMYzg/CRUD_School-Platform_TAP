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
    private final ObservableList<Subject> assignedSubjects = FXCollections.observableArrayList();

    public Teacher(Degree degree, String license, String specialization, String name, String lastName, BirthDate birthDate, String email, String phone, Address address, Gender gender) {
        super(name, lastName, birthDate, email, phone, address, gender);
        super.setRole(UserRole.TEACHER);
        this.license = license;
        this.degree = degree;
        this.specialization = specialization;
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
        this.degree = degree;
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
        assignedSubjects.add(subject);
        subject.setTeacher(this);
    }
    public void unassignSubject(Subject subject) {
        assignedSubjects.remove(subject);
        subject.setTeacher(null);
    }
}
