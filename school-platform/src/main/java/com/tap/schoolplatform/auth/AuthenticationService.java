package com.tap.schoolplatform.auth;

import com.tap.schoolplatform.models.enums.UserRole;
import com.tap.schoolplatform.models.users.User;
import com.tap.schoolplatform.services.Service;

public class AuthenticationService extends Service {
    public User login(String email, String password) {
        for(UserRole role : UserRole.values()) {
            for(User user : sharedData.getUsers(role)) {
                if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
                    return user;
                }
            }
        }
        return null;
    }
}
