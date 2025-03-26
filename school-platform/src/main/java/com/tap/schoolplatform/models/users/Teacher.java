package com.tap.schoolplatform.models.users;

import com.tap.schoolplatform.models.academic.Subject;
import com.tap.schoolplatform.models.shared.Address;
import com.tap.schoolplatform.models.shared.BirthDate;
import com.tap.schoolplatform.models.enums.Gender;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends User {

    private String specialization;
    private List<Subject> assignedSubjects = new ArrayList<>();

    public Teacher(String name, String lastName, BirthDate birthDate, String email, String phone, Address address, Gender gender) {
        super(name, lastName, birthDate, email, phone, address, gender);
    }
}
