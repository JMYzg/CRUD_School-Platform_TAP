package com.tap.schoolplatform.models.users;

import com.tap.schoolplatform.models.academic.Degree;
import com.tap.schoolplatform.models.academic.Subject;
import com.tap.schoolplatform.models.enums.UserRole;
import com.tap.schoolplatform.models.shared.Address;
import com.tap.schoolplatform.models.shared.BirthDate;
import com.tap.schoolplatform.models.enums.Gender;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends User {

    private UserRole role = UserRole.TEACHER;
    private String license;
    private Degree degree;
    private String specialization;
    private final List<Subject> assignedSubjects = new ArrayList<>();

    public Teacher(Degree degree, String license, String specialization, String name, String lastName, BirthDate birthDate, String email, String phone, Address address, Gender gender) {
        super(name, lastName, birthDate, email, phone, address, gender);
        this.license = license;
        this.degree = degree;
        this.specialization = specialization;
    }

    public UserRole getRole() {
        return role;
    }
    public void setRole(UserRole role) {
        this.role = role;
    } // Temp

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

    public List<Subject> getAssignedSubjects() {
        return assignedSubjects;
    }

    public void assignSubject(Subject subject) {
        this.assignedSubjects.add(subject);
    }
    public void unassignSubject(Subject subject) {
        this.assignedSubjects.remove(subject);
    }

    public Subject getSubject(Subject subject) {
        return this.assignedSubjects.get(this.assignedSubjects.indexOf(subject));
    }
}
