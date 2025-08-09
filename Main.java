package com.gcash.main;

import com.gcash.auth.UserAuthentication;
import com.gcash.database.UserDatabase;
import com.gcash.balance.CheckBalance;

import java.util.Scanner;

public class Main {
    private static int userIdCounter = 4; // Start after dummy data

    public static void main(String[] args) {
        preloadDummyUsers();
        Scanner scanner = new Scanner(System.in);
        boolean runApp = true;

        while (runApp) {
            System.out.println("\nGcashApp Menu:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Change PIN");
            System.out.println("4. Logout");
            System.out.println("5. Check Balance");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newLine

            switch (choice) {
                case 1 -> register(scanner);
                case 2 -> login(scanner);
                case 3 -> changePin(scanner);
                case 4 -> logout(scanner);
                case 5 -> checkBalance(scanner);
                case 6 -> runApp = false;
            }
        }
        scanner.close();
    }

    private static void preloadDummyUsers() {
        UserDatabase.addUser(new UserAuthentication(1, "Ver", "ver@example.com", "09171234567", "1234"));
        UserDatabase.addUser(new UserAuthentication(1, "Minda", "minda@example.com", "09181234567", "5678"));
        UserDatabase.addUser(new UserAuthentication(1, "Mar", "mar@example.com", "09191234567", "9090"));
    }
     
    private static void register(Scanner scanner) {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone Number: ");
        String number = scanner.nextLine();
        System.out.print("PIN (4 digits): ");
        String pin = scanner.nextLine();

        if (validate(name, email, number, pin)) {
            UserAuthentication user = new UserAuthentication(userIdCounter++, name, email, number, pin);
            UserDatabase.adduser(user);
            System.out.println("User registered successfully!");
        } else {
            System.out.println("Invalid data. Try again.");
        }
    }

    private static boolean validate(String name, String email, String number, String pin) {
        return name.length() > 1 && email.contains("@") && number.matches("\\d{11}") && pin.matches("\\d{4}");
    }

    private static void login(Scanner scanner) {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("PIN: ");
        String pin = scanner.nextLine();

        UserAuthentication user = UserDatabase.getUserByEmail(email);
        if (user != null && user.getPin().equals(pin)) {
            System.out.println("Login successful. User ID: " + user.getId());
        } else {
            System.out.println("Authentication failed.");
        }
    }

    private static void changePin(Scanner scanner) {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Old PIN: ");
        String oldPin = scanner.nextLine();
        System.out.print("New PIN: ");
        String newPin = scanner.nextLine();

        UserAuthentication user = UserDatabase.getUserByEmail(email);
        if (user != null && user.getPin().equals(oldPin)) {
            if (newPin.matches("\\d{4}")) {
                user.setPin(newPin);
                System.out.println("PIN changed successfully.");
            } else {
                System.out.println("New PIN must be 4 digits.");
            }
        } else {
            System.out.println("Authentication failed.");
        }
    }

    private static void logout(Scanner scanner) {
        System.out.print("Enter your email to logout: ");
        String email = scanner.nextLine();
        System.out.println(email + " successfully logged out.");
    }

    private static void checkBalance(Scanner scanner) {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("PIN: ");
        String pin = scanner.nextLine();

        UserAuthentication user = UserDatabase.getUserByEmail(email);
        if (user != null && user.getPin().equals(pin)) {
            CheckBalance cb = new CheckBalance();
            double balance = cb.getBalance(user.getId());
            System.out.println("Your current balance is: P" + balance);
        } else {
            System.out.println("Authentication failed. Cannot retrieve balance.");
        }
    }
}