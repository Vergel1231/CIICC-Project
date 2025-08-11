package com.gcash.transaction;

import com.gcash.auth.UserAuthentication;
import com.gcash.database.UserDatabase;
import java.util.Date;


public class CashIn {
    public static void cashIn(UserAuthentication user, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
            return;
        }

        user.adjustBalance(amount);

        int transactionID = UserDatabase.getTransactions().size() + 1;
        com.gcash.transaction.Transaction tx = new com.gcash.transaction.Transaction(
            transactionID,
            amount,
            user.getName(),
            user.getId(),
            new Date(),
            null,
            null
        );

        UserDatabase.addTransaction(tx);
        System.out.println("Cash-in successful. New balance: ₱" + user.getBalance());
    }
}