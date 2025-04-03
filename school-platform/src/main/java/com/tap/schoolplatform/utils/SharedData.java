package com.tap.schoolplatform.utils;

import com.tap.schoolplatform.models.academic.Group;
import com.tap.schoolplatform.models.academic.Subject;
import com.tap.schoolplatform.models.academic.enums.Shift;
import com.tap.schoolplatform.models.enums.Gender;
import com.tap.schoolplatform.models.enums.UserRole;
import com.tap.schoolplatform.models.shared.Address;
import com.tap.schoolplatform.models.shared.BirthDate;
import com.tap.schoolplatform.models.users.Student;
import com.tap.schoolplatform.models.users.Teacher;
import com.tap.schoolplatform.models.users.User;
import com.tap.schoolplatform.models.users.Administrator;
import com.tap.schoolplatform.models.academic.Degree;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

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

    public void initialize() {
        Administrator admin =
                new Administrator(
                        "Jeremy",
                        "Zarate",
                        new BirthDate(23, 2, 2004),
                        "jmy_zg@icloud.com",
                        "624-227-5260",
                        new Address("Sago Palm", 23477, "The Palms", "C.S.L.", "B.C.S.", "Mexico"),
                        Gender.MALE
                );
        users.computeIfAbsent(UserRole.ADMIN, k -> FXCollections.observableArrayList()).add(admin);
        Degree SE = new Degree("Software Engineering");
        degrees.add(SE);
        Subject subject =
                new Subject(
                        SE,
                        1,
                        "Integral Calculus",
                        "Integral calculus involves finding the total size or value by summing infinitesimal parts, such as areas and volumes."
                );
        Group M1 = new Group(SE, 1, Shift.MORNINGS);
        Student student = getStudent(M1);
        users.computeIfAbsent(UserRole.STUDENT, k -> FXCollections.observableArrayList()).add(student);
        Teacher teacher = getTeacher(SE);
        teacher.assignSubject(subject);
        users.computeIfAbsent(UserRole.TEACHER, k -> FXCollections.observableArrayList()).add(teacher);
    }

    private static @NotNull Student getStudent(Group M1) {
        Student student =
                new Student(
                        "Gary",
                        "Juarez",
                        new BirthDate(5, 9, 2005),
                        "gry_jp@gmail.com",
                        "624-243-8538",
                        new Address("Dolomite", 23456, "Saint Bernard", "S.J.D.C.", "B.C.S.", "Mexico"),
                        Gender.MALE
                );
        student.setGroup(M1);
        return student;
    }

    private static @NotNull Teacher getTeacher(Degree SE) {
        Teacher teacher =
                new Teacher(
                        "Brisa",
                        "Bautista",
                        new BirthDate(14, 2, 2004),
                        "brs.bc@hotmail.com",
                        "624-160-8038",
                        new Address("Pango", 23477, "Cabo Valley", "C.S.L.", "B.C.S.", "Mexico"),
                        Gender.FEMALE
                );
        teacher.setLicense("123-PANGO-456");
        teacher.setDegree(SE);
        teacher.setSpecialization("Math");
        return teacher;
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
