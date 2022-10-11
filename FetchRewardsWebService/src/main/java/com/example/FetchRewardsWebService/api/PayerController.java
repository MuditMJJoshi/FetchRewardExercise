package com.example.FetchRewardsWebService.api;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FetchRewardsWebService.model.Points;
import com.example.FetchRewardsWebService.model.SpendPoint;
import com.example.FetchRewardsWebService.model.Transactions;
import com.example.FetchRewardsWebService.services.PayerServices;

@RequestMapping("api/v1/FetchRewardServices")
@RestController
public class PayerController {
	
	private final PayerServices services;
	
	@Autowired
	public PayerController(PayerServices services)
	{
		this.services = services;
	}
	
	/**
	   * GET Method Retrieve transactions of Payer.
	   * @param id
	   * @return Map with Payer transaction info
	   */
	@GetMapping("/balance/{id}")
    public Map<String, Integer> getTransaction(@PathVariable int id)
    {
		return services.getTransaction(id);
    }
	
	/**
	   * POST Method Add points for each transactions for Payer
	   * @param Request Body transaction, id
	   * @return ResponseEntity with Ok for adding successfully
	   */
	@PostMapping("/addPoints/{id}")
    public ResponseEntity<String> addPoints(@PathVariable int id, @RequestBody Transactions transaction)
    {
		return services.addPoints(id, transaction);
    }
	
	/**
	  * POST Method Subtract spend the points for Payer to each payer point
     * @param Request Body points, id
     * @return List of SpendPoints spent for each Payer
     */
	@PostMapping("/spendPoints/{id}")
	public List<SpendPoint> spendPoints(@PathVariable int id, @RequestBody Points points)
	{
		return services.spendPoints(id, points);
	}
}
