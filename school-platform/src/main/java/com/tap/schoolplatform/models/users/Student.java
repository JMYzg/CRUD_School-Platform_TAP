package com.tap.schoolplatform.models.users;

import com.tap.schoolplatform.models.shared.Address;
import com.tap.schoolplatform.models.shared.BirthDate;
import com.tap.schoolplatform.models.enums.Gender;
import com.tap.schoolplatform.models.enums.Status;

public class Student extends User {

    private Status status = Status.ACTIVE;
    protected Student(String name, String lastName, BirthDate birthDate, String email, String phone, Address address, Gender gender) {
        super(name, lastName, birthDate, email, phone, address, gender);
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
