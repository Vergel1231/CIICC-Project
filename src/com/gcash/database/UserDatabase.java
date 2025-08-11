package com.gcash.database;

import com.gcash.auth.UserAuthentication;
import com.gcash.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private static List<UserAuthentication> users = new ArrayList<>();
    private static List<Transaction> transactions = new ArrayList<>();

    public static List<UserAuthentication> getUsers() {
        return users;
    }

    public static void addUser(UserAuthentication user) {
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

    public static void addTransaction(Transaction tx) {
        transactions.add(tx);
    }

    public static List<Transaction> getTransactions() {
        return transactions;
    }
}
