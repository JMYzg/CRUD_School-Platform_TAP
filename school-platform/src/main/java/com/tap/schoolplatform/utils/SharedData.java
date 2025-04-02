package com.tap.schoolplatform.utils;

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

import com.tap.schoolplatform.services.users.AdministratorService;
import com.tap.schoolplatform.utils.dtos.UserDTO;
import com.tap.schoolplatform.utils.dtos.academic.SubjectDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class SharedData {
    private static SharedData instance;

    private final ObservableMap<UserRole, ObservableList<User>> users;
    private final ObservableList<Administrator> administrators;
    private final ObservableList<Degree> degrees;

    private void initialize() {

        User administrator =
                new Administrator(
                        "Jeremy",
                        "Zarate",
                        new BirthDate(23, 2, 2004),
                        "jmy_zg@icloud.com",
                        "624-227-5260",
                        new Address("Palma Sago", 23477, "Las Palmas", "C.S.L.", "B.C.S", "Mexico"),
                        Gender.MALE
                );
        users.get(administrator.getRole()).add(administrator);

        Degree degree = new Degree("Computer Science");
        degrees.add(degree);

        AdministratorService administratorService = new AdministratorService(degree);
        administratorService.createGroup(1, Shift.MORNINGS);

        User Gary =
                new Student(
                        "Gary",
                        "Juarez",
                        new BirthDate(5, 9, 2005),
                        "gry_jp@gmail.com",
                        "624-243-8558",
                        new Address("Gigiri", 23436, "San Bernabe", "S.J.D.C.", "B.C.S", "Mexico"),
                        Gender.MALE
                );
        UserDTO garyDTO = new UserDTO();
        garyDTO.setGroup(administratorService.readGroup(1, "1CS-1M"));
        administratorService.createUser(Gary, garyDTO);
        administratorService.createSubject(
                1,
                "Integral Calculus",
                "Description bla bla bla bla bla"
        );

        User Brisa =
                new Teacher(
                        "Brisa",
                        "Bautista",
                        new BirthDate(14, 2, 2004),
                        "brs_bc@hotmail.com",
                        "624-160-8038",
                        new Address("Pango", 23477, "Cabo Valley", "C.S.L.", "B.C.S.", "Mexico"),
                        Gender.FEMALE
                );
        UserDTO brisaDTO = new UserDTO();
        brisaDTO.setLicense("123456789");
        brisaDTO.setSpecialization("Math");
        administratorService.createUser(Brisa, brisaDTO);
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setTeacher(administratorService.getDegree().getTeacherList().get(0));
        administratorService.updateSubject(administratorService.readSubject(1, "Integral Calculus"), subjectDTO);
    }

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
