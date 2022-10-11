package com.example.FetchRewardsWebService.dao;

import java.util.*;

import org.springframework.http.ResponseEntity;

import com.example.FetchRewardsWebService.model.Points;
import com.example.FetchRewardsWebService.model.SpendPoint;
import com.example.FetchRewardsWebService.model.Transactions;

public interface FetchRewardServices {
	
	 public Map<String, Integer> getTransaction(int id);
	 
	 public ResponseEntity<String> addPoints(int id, Transactions transaction);
	 
	 public List<SpendPoint> spendPoints(int id, Points points);
	 	 
}
