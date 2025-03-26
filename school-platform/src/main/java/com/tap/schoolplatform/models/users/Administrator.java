package com.tap.schoolplatform.models.users;

import com.tap.schoolplatform.models.shared.Address;
import com.tap.schoolplatform.models.shared.BirthDate;
import com.tap.schoolplatform.models.enums.Gender;
import com.tap.schoolplatform.models.enums.UserRole;
import com.tap.schoolplatform.utils.SharedData;

public class Administrator extends User {
    private UserRole role = UserRole.ADMIN;

    public Administrator(String name, String lastName, BirthDate birthDate, String email, String phone, Address address, Gender gender) {
        super(name, lastName, birthDate, email, phone, address, gender);
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
