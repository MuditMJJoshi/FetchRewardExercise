package com.example.FetchRewardsWebService.model;

import java.util.Date;

public class Transactions {
	
	//TimeStamp of the transaction
	 private final Date timestamp;
	//Points for the transaction
	 private int points;
	//Payer Name for the transaction
	 private final String payer;
	 
	 public Transactions(String payer, int points, Date timestamp) 
	 {
		 this.timestamp = timestamp;
	     this.points = points;
	     this.payer = payer;
	 }
	 
	 // Getter Get TimeStamp
	 public Date getTimeStamp() 
	 {
	    return timestamp;
	 }
	 
	// Getter Get Points
	 public int getPoints() 
	 {
	    return points;
	 }

	// Getter Get Payer
	 public String getPayer() 
	 {
	     return payer;
	 }

	// Setter Set Points
	 public void setPoints(int points) 
	 {
	     this.points = points;
	 }

}
