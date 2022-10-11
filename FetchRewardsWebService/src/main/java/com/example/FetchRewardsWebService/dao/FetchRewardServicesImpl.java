package com.example.FetchRewardsWebService.dao;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import com.example.FetchRewardsWebService.model.Payer;
import com.example.FetchRewardsWebService.model.Points;
import com.example.FetchRewardsWebService.model.SpendPoint;
import com.example.FetchRewardsWebService.model.Transactions;

@Repository("FetchRewardsServices")
public class FetchRewardServicesImpl implements FetchRewardServices{

	private final Map<Integer, Payer> payers = new HashMap<>();

	
	/**
	   * Retrieve transactions of Payer.
	   * @param id
	   * @return Map with Payer transaction info
	   */
	@Override
	public Map<String, Integer> getTransaction(int id) 
	{
		
		if (!payers.containsKey(id)) 
		{
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Payer exists.");
        }

        return payers.get(id).getAmount();
	}

	  /**
	   * Add points for each transactions for Payer
	   * @param transaction, id
	   * @return ResponseEntity with Ok for adding successfully
	   */
	@Override
	public ResponseEntity<String> addPoints(int id, Transactions transaction) 
	{
		// When the points are positive, simply append the transaction 
	    // When the points are negative, there are three conditions
	    //- payer exists and deducting the points gets negative points value
	    //- payer exists and deducting the points gets positive points value
	    //- when points is negative
		
		//validate Transaction
		if (transaction.getPoints() == 0) 
		{
            throw new IllegalArgumentException("Can't add 0 points.");
        }

        if (transaction.getPoints() < 0) 
        {
            if (!payers.containsKey(id)) 
            {
                throw new IllegalArgumentException("Payer doesn't exists.");
            } 
            else 
            {
                Payer newPayer = payers.get(id);
                Map<String, Integer> amount = newPayer.getAmount();
                if (!amount.containsKey(transaction.getPayer())) 
                {
                    throw new IllegalArgumentException("Payer point doesn't exists.");
                }

                int payerPoints = amount.get(transaction.getPayer());
                if (payerPoints - transaction.getPoints() < 0) 
                {
                    throw new IllegalArgumentException("Payer balance can't be negative.");
                }
            }
        }

        //add Transaction
        if (!payers.containsKey(id)) 
		{
            payers.put(id, new Payer(id));
        } 
        
        Payer newPayer2 = payers.get(id);
        newPayer2.getTransactions().add(transaction);

        return ResponseEntity.ok().body("Points added successfully.");
	}

	 /**
	  * Subtract spend the points for Payer to each payer point
      * @param points, id
      * @return List of SpendPoints spent for each Payer
      */
	@Override
	public List<SpendPoint> spendPoints(int id, Points points) 
	{
		
		if (!payers.containsKey(id)) 
		{
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Payer exists.");
        }

        if (points.points <= 0) 
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't spend negative points.");
        }

        Payer newPayer = payers.get(id);
        int totalAmount = newPayer.getTotalAmount();

        if (points.points > totalAmount) 
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payer balance can't be negative.");
        }

        int pointsSpend = points.points;
        
        Payer newPayer2 = payers.get(id);

        // Sort by TimeStamp oldest to be spent first
        List<Transactions> transactions = newPayer2.getTransactions();
        transactions.sort(Comparator.comparing(Transactions::getTimeStamp));
        Iterator<Transactions> counter = transactions.iterator();

        List<SpendPoint> spendAmount = new ArrayList<>();
        Map<String, Integer> spendAmountMapper = new HashMap<>();

        while (pointsSpend > 0) 
        {
            Transactions transaction = counter.next();
            String payer = transaction.getPayer();
            int transactionPoints = transaction.getPoints();
            int payerSpending = spendAmountMapper.getOrDefault(payer, 0);
            
            if (pointsSpend >= transactionPoints) 
            {
            	spendAmountMapper.put(payer, payerSpending + transactionPoints);

                transaction.setPoints(0);
                
                pointsSpend -= transactionPoints;
            } 
            else 
            {
            	spendAmountMapper.put(payer, payerSpending + pointsSpend);

                transaction.setPoints(transactionPoints - pointsSpend);
                
                pointsSpend = 0;
            }
        }

        for (String key : spendAmountMapper.keySet()) 
        {
        	SpendPoint newspendAmount = new SpendPoint(key, -1 * spendAmountMapper.get(key));
        	spendAmount.add(newspendAmount);
        }

        return spendAmount;
        
	}

}
