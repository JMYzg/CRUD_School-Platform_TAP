package com.tap.schoolplatform.models.users;

import com.tap.schoolplatform.models.academic.Grade;
import com.tap.schoolplatform.models.academic.Group;
import com.tap.schoolplatform.models.academic.keys.GradeKey;
import com.tap.schoolplatform.models.shared.*;
import com.tap.schoolplatform.models.enums.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student extends User {

    private final String studentId;
    private Status status = Status.ACTIVE;
    private Group group;
    private final Map<GradeKey, List<Grade>> grades = new HashMap<>();

    public Student(Group group, int index, String name, String lastName, BirthDate birthDate, String email, String phone, Address address, Gender gender) {
        super(name, lastName, birthDate, email, phone, address, gender);
        this.studentId = LocalDate.now().getYear() % 100 + LocalDate.now().getMonth().toString() + index;
        this.group = group;
    }

    public String getStudentId() {
        return studentId;
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
    }
}
