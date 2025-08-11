package com.gcash.balance;

import java.util.HashMap;
import java.util.Map;

public class CheckBalance {
    private static Map<Integer, Double> balanceMap = new HashMap<>();

    static {
        balanceMap.put(1, 1500.00);
        balanceMap.put(2, 3200.50);
        balanceMap.put(3, 0.00);
    }

    public double getBalance(int userId) {
        return balanceMap.getOrDefault(userId, 0.0);
    }
}
