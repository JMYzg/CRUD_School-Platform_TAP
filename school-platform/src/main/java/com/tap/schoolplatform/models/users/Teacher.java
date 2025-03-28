package com.tap.schoolplatform.models.users;

import com.tap.schoolplatform.models.academic.Degree;
import com.tap.schoolplatform.models.academic.Group;
import com.tap.schoolplatform.models.academic.Subject;
import com.tap.schoolplatform.models.shared.Address;
import com.tap.schoolplatform.models.shared.BirthDate;
import com.tap.schoolplatform.models.enums.Gender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Teacher extends User {

    private String license;
    private Degree degree;
    private String specialization;
    private final Map<Group, List<Subject>> assignedSubjects = new HashMap<>();

    public Teacher(Degree degree, String license, String specialization, String name, String lastName, BirthDate birthDate, String email, String phone, Address address, Gender gender) {
        super(name, lastName, birthDate, email, phone, address, gender);
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

    public List<Subject> getAssignedSubjects(Group group) {
        return assignedSubjects.get(group);
    }

    public void setAssignedSubjects(Group group, List<Subject> subjects) {
        assignedSubjects.put(group, subjects);
    }

    public void assignSubject(Group group, Subject subject) {
        assignedSubjects.get(group).add(subject);
    }
    public void unassignSubject(Group group, Subject subject) {
        assignedSubjects.get(group).remove(subject);
    }

    public Subject getSubject(Group group, Subject subject) {
        return assignedSubjects.get(group).get(assignedSubjects.get(group).indexOf(subject));
    }
}
