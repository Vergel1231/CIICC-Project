package src.com.gcash.auth;

import src.com.gcash.auth.UserAuthentication;

public class UserAuthentication {
    private int id;
    private String name;
    private String email;
    private String number;
    private String pin;
    private double balance;

    public UserAuthentication(int id, String name, String email, String number, String pin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.number = number;
        this.pin = pin;
        this.balance = 1500.0;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getNumber() { return number; }
    public String getPin() { return pin; }
    public double getBalance() { return balance; }

    public void setPin(String newPin) {
        this.pin = newPin;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void adjustBalance(double amount) {
        this.balance += amount;
    }
}
