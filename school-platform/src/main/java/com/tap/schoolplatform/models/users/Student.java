package com.tap.schoolplatform.models.users;

import com.tap.schoolplatform.models.academic.Grade;
import com.tap.schoolplatform.models.academic.Group;
import com.tap.schoolplatform.models.academic.keys.GradeKey;
import com.tap.schoolplatform.models.shared.*;
import com.tap.schoolplatform.models.enums.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.time.LocalDate;

public class Student extends User {

    private String ID;
    private Status status = Status.ACTIVE;
    private Group group;
    private int index;
    private final ObservableMap<GradeKey, Grade> grades = FXCollections.observableHashMap();

    public Student(/*Group group, int index,*/ String name, String lastName, BirthDate birthDate, String email, String phone, Address address, Gender gender) {
        super(name, lastName, birthDate, email, phone, address, gender);
        super.setRole(UserRole.STUDENT);
//        this.group = group;
//        group.addStudent(this); // Check this shit
//        generateID();
//        this.ID = LocalDate.now().getYear() % 100 + LocalDate.now().getMonth().toString() + index; // make student list into TreeSet
    }

    private void generateID() {
        for (Student student : group.getStudentList()) {
            if (student == this) { // May suppose this is right, but check if things go bad
                index = group.getStudentList().indexOf(student) + 1;
                this.ID = LocalDate.now().getYear() % 100 + LocalDate.now().getMonth().toString() + index;
            }
        }
    }

    public String getID() {
        return ID;
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
        group.addStudent(this); // Check this shit
        generateID();
    }

    public int getIndex() {
        return index;
    }

    public void addGrade(GradeKey key, Grade grade) {
        grades.put(key, grade);
    }
    public Grade getGrade(GradeKey key) {
        return grades.get(key);
    }

    @Override
    public String toString() {
        return super.getName();
    }
}
