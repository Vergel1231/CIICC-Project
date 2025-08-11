package com.gcash.transaction;

import com.gcash.auth.UserAuthentication;
import com.gcash.database.UserDatabase;

import java.util.Date;

public class CashTransfer {
    public static void cashTransfer(UserAuthentication sender, String recipientEmail, double amount) {
        // Restriction 1: Validate amount
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
            return;
        }

        // Restriction 2: Prevent self-transfer
        if (sender.getEmail().equals(recipientEmail)) {
            System.out.println("You cannot transfer money to yourself.");
            return;
        }

        // Restriction 3: Check sender balance
        if (sender.getBalance() < amount) {
            System.out.println("Insufficient balance.");
            return;
        }

        // Find recipient
        UserAuthentication recipient = UserDatabase.getUserByEmail(recipientEmail);
        if (recipient == null) {
            System.out.println("Recipient account not found.");
            return;
        }

        // Proceed with transfer
        sender.adjustBalance(-amount);
        recipient.adjustBalance(amount);

        // Log transaction for sender
        int txIdSender = UserDatabase.getTransactions().size() + 1;
        Transaction txSender = new Transaction(
            txIdSender,
            amount,
            sender.getName(),
            sender.getId(),
            new Date(),
            recipient.getId(),
            null
        );
        UserDatabase.addTransaction(txSender);

        // Log transaction for recipient
        int txIdRecipient = txIdSender + 1;
        Transaction txRecipient = new Transaction(
            txIdRecipient,
            amount,
            recipient.getName(),
            recipient.getId(),
            new Date(),
            null,
            sender.getId()
        );
        UserDatabase.addTransaction(txRecipient);

        System.out.println("Transfer successful.");
        System.out.println(" " + amount + " transferred from " + sender.getName() + " to " + recipient.getName());
        System.out.println("New balance: ₱" + sender.getBalance());
    }
}