package com.serviceimpl;

import com.Interface.AccountServices;	//importing necessary interface

//creating user thread to work for a particular user at a time
public class UserThread implements Runnable 
{
	//declaring variables
	AccountServices acc;	//business logic super interface reference object
	String accType;
	float openingBal;
	Thread t1;	//using thread reference variable
	
	//parameterized constructor
	public UserThread(AccountServices acc, String accType, float openingBal) 
	{
		this.accType=accType;
		this.openingBal=openingBal;
		this.acc=acc;
		t1=new Thread(this);
		t1.start();	//starting the thread i.e. runnable state
		t1.setPriority(10);
	}
	
	//overriding run method of thread
	public synchronized void run() 
	{
		//invoking the open Account method for a particular object of business logic
		int accNo=acc.openAccount(accType, openingBal);
		System.out.println("Account no. generated is "+accNo);
		
	}

}
