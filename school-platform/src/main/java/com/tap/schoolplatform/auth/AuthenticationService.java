package com.tap.schoolplatform.auth;

import com.tap.schoolplatform.models.enums.UserRole;
import com.tap.schoolplatform.models.users.Administrator;
import com.tap.schoolplatform.models.users.Student;
import com.tap.schoolplatform.models.users.Teacher;
import com.tap.schoolplatform.models.users.User;
import com.tap.schoolplatform.services.Service;

public class AuthenticationService extends Service {
    public User login(String email, String password) {
//        for(UserRole role : UserRole.values()) {
//            for(User user : sharedData.getUsers(role)) {
//                if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
//                    return user;
//                }
//            }
//        }
//        return null;
        for(Administrator admin : sharedData.getAdministrators()) {
            if(admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
        for(Teacher teacher : sharedData.getTeachers()) {
            if(teacher.getEmail().equals(email) && teacher.getPassword().equals(password)) {
                return teacher;
            }
        }
        for(Student student : sharedData.getStudents()) {
            if(student.getEmail().equals(email) && student.getPassword().equals(password)) {
                return student;
            }
        }
        return null;
    }
}
