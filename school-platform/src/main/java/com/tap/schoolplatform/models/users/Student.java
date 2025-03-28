package com.tap.schoolplatform.models.users;

import com.tap.schoolplatform.models.shared.Address;
import com.tap.schoolplatform.models.shared.BirthDate;
import com.tap.schoolplatform.models.enums.Gender;
import com.tap.schoolplatform.models.enums.Status;

import java.time.LocalDate;

public class Student extends User {

    private final String studentId;
    private Status status = Status.ACTIVE;

    public Student(int index, String name, String lastName, BirthDate birthDate, String email, String phone, Address address, Gender gender) {
        super(name, lastName, birthDate, email, phone, address, gender);
        this.studentId = LocalDate.now().getYear() % 100 + LocalDate.now().getMonth().toString() + index;
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
}
