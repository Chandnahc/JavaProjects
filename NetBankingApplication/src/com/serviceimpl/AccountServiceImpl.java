package com.serviceimpl;

import java.sql.ResultSet;
import java.sql.SQLException;

//importing necessary class,interfaces
import com.DAOinterface.DAOInterface;
import com.Exception.InsufficientBalanceException;
import com.Exception.InvalidAccountNoException;
import com.Interface.AccountServices;
import com.bean.Account;
import com.bean.Date;
import com.utility.DAOprovider;

//account service business logic class
public class AccountServiceImpl implements AccountServices 
{
	//declaring variables
	Account acc;
	long millis=System.currentTimeMillis();		//for current date and time
	java.sql.Date date=new java.sql.Date(millis);	
	
	static DAOInterface dao=DAOprovider.getDAOObject();		//object for database CRUD operation 
	
	//new account opening method
	public synchronized int openAccount(String type,float amount) 
	{
		
		acc=new Account(type,amount, String.valueOf(date));		//setting new account with current date in the form of string 
		dao.insertAccount(acc);		//for inserting the account in database 
		return acc.getAccNo();		//to return the account number generated
		
	}
	
	//for amount deposit in account
	public synchronized float deposit(int accNo,float amount) throws InvalidAccountNoException 
	{
		
		if(checkAcc(accNo)) 	//checking if account number present or not
		{
			
			if(dao.updateAccountDeposit(accNo,amount)) 	//checking if deposit is successful or not
			{
				acc.setAccBal(acc.getAccBal()+amount);
				System.out.println("successfully deposited");
			}
			
			return acc.getAccBal();		//returning the outstanding account balance
			
		}
		
		else 	//else throwing an exception
		{
			throw new InvalidAccountNoException("invalid account no.!!!");
		}
		
	}
	
	//for amount withdraw from account
	public synchronized float withdraw(int accNo,float amount) throws InvalidAccountNoException,InsufficientBalanceException 
	{
		
		if(checkAcc(accNo)) 	//checking if account number present or not
		{
			
			if(acc.getAccBal()<amount) 		//checking if amount is less than outstanding balance or not
			{
				throw new InsufficientBalanceException("Account balance is: "+acc.getAccBal());
			}
			
			else 
			{
				if(dao.updateAccountWithdraw(accNo, amount)) 		//checking if withdrawn is successful or not
				{
					System.out.println("successfully withdrawn");
					acc.setAccBal(acc.getAccBal()-amount);
				}
				
				return acc.getAccBal();		//returning the outstanding account balance
			}
			
		}
		else 	//else throwing an exception
		{
			throw new InvalidAccountNoException("invalid account no.!!!");
		}
		
	}
	
	//balance enquiry method
	public float balEnquiry(int accNo) throws InvalidAccountNoException 
	{
		
		if(checkAcc(accNo)) 	//checking if account no present or not
		{
			return dao.retrieveAccountBal(accNo);		//returning the fetched balance from account
		}
		
		else 		//else throwing an exception
		{
			throw new InvalidAccountNoException("invalid account no.!!!");
		}
		
	}
	
	//method to check account details
	public Account checkDetails(int accNo) throws InvalidAccountNoException 
	{
		
		if(checkAcc(accNo)) 	//checking if account no present or not
		{
			return dao.retrieveAccountData(accNo);		//returning fetched account data
		}
		
		else 		//else throwing an exception
		{
			throw new InvalidAccountNoException("invalid account no.!!!");
		}
		
	}
	
	//method for fund transfer from one account to another
	public synchronized void fundTransfer(int accNo,int accNo1,float amount) throws InvalidAccountNoException, InsufficientBalanceException 
	{
		
		withdraw(accNo, amount);	//withdrawing amount from one account
		deposit(accNo1, amount);		//depositing amount in second account
		
	}
	
	public void showAllAccount() 
	{
		try 
		{
			System.out.println("Account number \t Account Type \t Balance \t Opening Date");
			ResultSet rs=dao.showAllAccount();
			while(rs.next()) {
				acc.setAccNo(rs.getInt(1));
				acc.setAccType(rs.getString(2));
				acc.setAccBal(rs.getFloat(3));
				String date=String.valueOf(rs.getDate(4).toLocalDate());
				acc.setAccOpDate(date);
				
				System.out.println(""+acc.getAccNo()+"\t"+acc.getAccType()+"\t\t"+acc.getAccBal()+"\t\t"+acc.getAccOpDate());
			}
			System.out.println();
			
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Utility methods
	public static boolean checkAcc(int accNo) throws InvalidAccountNoException 		//method for checking account exits or not
	{
		
		boolean val=false;		//creating a boolean variable to return 
		Account accDb=dao.retrieveAccountData(accNo);	//retrieving account data with account no in an account variable
		
		if(!(accDb==null)) 		//checking if return value if null then true otherwise false
		{
			val=true;
		}
		else 
		{
			val=false;
		}
		
		return val;		//returning boolean value
	}
	
	
}

