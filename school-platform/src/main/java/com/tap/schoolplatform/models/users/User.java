package com.tap.schoolplatform.models.users;

import com.tap.schoolplatform.models.shared.Address;
import com.tap.schoolplatform.models.shared.BirthDate;
import com.tap.schoolplatform.models.enums.Gender;

import java.util.UUID;

public abstract class User {
    private final UUID uuid;
    String name;
    String lastName;
    BirthDate birthDate;
    String email;
    String phone;
    Address address;
    Gender gender;

    protected User(String name, String lastName, BirthDate birthDate, String email, String phone, Address address, Gender gender) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
    }

    public UUID getId() {
        return uuid;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BirthDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(BirthDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
