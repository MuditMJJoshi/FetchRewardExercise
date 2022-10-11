package com.example.FetchRewardsWebService.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.FetchRewardsWebService.dao.FetchRewardServices;
import com.example.FetchRewardsWebService.model.Points;
import com.example.FetchRewardsWebService.model.SpendPoint;
import com.example.FetchRewardsWebService.model.Transactions;

@Service
public class PayerServices {
	
	private final FetchRewardServices services;
	
	@Autowired
	public PayerServices(@Qualifier("FetchRewardsServices") FetchRewardServices services)
	{
		this.services = services;
	}
	
	/**
	   * Retrieve transactions of Payer.
	   * @param id
	   * @return Map with Payer transaction info
	   */
	public Map<String, Integer> getTransaction(int id)
	{
		return services.getTransaction(id);
	}
	
	/**
	   * Add points for each transactions for Payer
	   * @param transaction, id
	   * @return ResponseEntity with Ok for adding successfully
	   */
	public ResponseEntity<String> addPoints(int id, Transactions transaction)
	{
		return services.addPoints(id, transaction);
	}
	
	/**
	  * Subtract spend the points for Payer to each payer point
     * @param points, id
     * @return List of SpendPoints spent for each Payer
     */
	public List<SpendPoint> spendPoints(int id, Points points)
	{
		return services.spendPoints(id, points);
	}
		 
}
