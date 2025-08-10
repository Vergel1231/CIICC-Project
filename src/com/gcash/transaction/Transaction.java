package src.com.gcash.transaction;

import java.util.Date;

public class Transaction {
    private int id;
    private double amount;
    private String name;
    private int accountID;
    private Date date;
    private Integer transferToID;
    private Integer transferFromID;

    public Transaction(int id, double amount, String name, int accountID, Date date, Integer transferToID, Integer transferFromID) {
        this.id = id;
        this.amount = amount;
        this.name = name;
        this.accountID = accountID;
        this.date = date;
        this.transferToID = transferToID;
        this.transferFromID = transferFromID;
    }

    public int getId() { return id; }
    public double getAmount() { return amount; }
    public String getName() { return name; }
    public int getAccountID() { return accountID; }
    public Date getDate() { return date; }
    public Integer getTransferToID() { return transferToID; }
    public Integer getTransferFromID() { return transferFromID; }
}
