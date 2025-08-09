package com.gcash.database;

import com.gcash.auth.UserAuthentication;
import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private static List<UserAuthentication> users = new ArrayList<>();

    public static List<UserAuthentication> getUsers() {
        return users;
    }

    public static void adduser(UserAuthentication user) {
        users.add(user);
    }

    public static UserAuthentication getUserByEmail(String email) {
        for (UserAuthentication user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
}