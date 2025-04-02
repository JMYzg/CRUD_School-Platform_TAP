package com.tap.schoolplatform.auth;

import com.tap.schoolplatform.models.users.User;
import com.tap.schoolplatform.services.Service;

public class LoginService extends Service {

    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        LoginService.currentUser = currentUser;
    }

    public static void logout() {
        currentUser = null;
    }
}
