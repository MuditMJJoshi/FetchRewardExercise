package com.example.FetchRewardsWebService.model;

public class SpendPoint {
	
	//Spending points
	 private int points;
	//Payer Name for spending amount
	 private String payer;
	 
	 public SpendPoint(String payer, int points) 
	 {
	     this.payer = payer;
	     this.points = points;
     }
	 
	// Getter Get Spend Points
	 public int getPoints() 
	 {
	     return points;
	 }

	// Getter Get Payer
	 public String getPayer() 
     {
	     return payer;
	 }

}
