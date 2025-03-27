package com.tap.schoolplatform.utils;

import com.tap.schoolplatform.models.users.User;
import com.tap.schoolplatform.models.users.Administrator;
import com.tap.schoolplatform.models.academic.Degree;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SharedData {
    private static SharedData instance;

    private final ObservableList<User> users;
    private final ObservableList<Administrator> administrators;
    private final ObservableList<Degree> degrees;

    private SharedData() {
        users = FXCollections.observableArrayList();
        administrators = FXCollections.observableArrayList();
        degrees = FXCollections.observableArrayList();
    }

    public static SharedData getInstance() {
        if (instance == null) {
            instance = new SharedData();
        }
        return instance;
    }

    public ObservableList<User> getUsers() {
        return users;
    }
    public ObservableList<Administrator> getAdministrators() {
        return administrators;
    }
    public ObservableList<Degree> getDegrees() {
        return degrees;
    }
}
