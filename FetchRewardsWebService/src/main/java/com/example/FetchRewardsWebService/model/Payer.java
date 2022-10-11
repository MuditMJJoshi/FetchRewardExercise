package com.example.FetchRewardsWebService.model;

import java.util.*;

public class Payer {
	
	// List of Payer Transaction
    private final List<Transactions> payerTransaction = new ArrayList<>();
    // Payer Transaction id
    private final int id;
    
    public Payer(int id) 
    {
        this.id = id;
    }

    public int getId() 
    {
        return id;
    }

     /**
	   * Retrieve amount of the Transaction
	   * @return Map with Payer transaction info
	   */
    public Map<String, Integer> getAmount() 
    {
        Map<String, Integer> amount = new HashMap<>();
        
        payerTransaction.forEach(vals -> 
        {
            String payer = vals.getPayer();
            int points = amount.getOrDefault(payer, 0);
            amount.put(payer, points + vals.getPoints());
        });

        return amount;
    }
    
     /**
	   * Retrieve total amount of the Transaction
	   * @return int total amount
	   */
    public int getTotalAmount() 
    {
        int totalamount = 0;
        Map<String, Integer> amount = getAmount();
        
        for (String key : amount.keySet()) {
        	totalamount += amount.get(key);
        }

        return totalamount;
    }

     /**
	   * Retrieve updated payer Transaction
	   * @return List of Payer Transaction
	   */
    public List<Transactions> getTransactions() 
    {
        return payerTransaction;
    }
    
}
