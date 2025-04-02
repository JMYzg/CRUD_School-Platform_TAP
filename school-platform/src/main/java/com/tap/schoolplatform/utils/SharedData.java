package com.tap.schoolplatform.utils;

import com.tap.schoolplatform.models.enums.UserRole;
import com.tap.schoolplatform.models.users.Student;
import com.tap.schoolplatform.models.users.User;
import com.tap.schoolplatform.models.users.Administrator;
import com.tap.schoolplatform.models.academic.Degree;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class SharedData {
    private static SharedData instance;



    private final ObservableMap<UserRole, ObservableList<User>> users;
    private final ObservableList<Administrator> administrators;
    private final ObservableList<Degree> degrees;

    private SharedData() {
        users = FXCollections.observableHashMap();
        administrators = FXCollections.observableArrayList();
        degrees = FXCollections.observableArrayList();
    }

    public static SharedData getInstance() {
        if (instance == null) {
            instance = new SharedData();
        }
        return instance;
    }

    public ObservableList<User> getUsers(UserRole role) {
        return users.get(role);
    }
    public ObservableList<Administrator> getAdministrators() {
        return administrators;
    }
    public ObservableList<Degree> getDegrees() {
        return degrees;
    }
}
