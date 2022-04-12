package com.DAOinterface;

import java.sql.ResultSet;

import com.bean.Account;	//importing necessary classes

//creating interface for summary of DAO implementation class
public interface DAOInterface 
{
	
	public int insertAccount(Account acc);		//for inserting account in database
	public Account retrieveAccountData(int accNo);		//for retrieving account data
	public float retrieveAccountBal(int accNo);		//for retrieving account balance
	public boolean updateAccountDeposit(int accNo, float amount);		//for reflecting amount deposit in database
	public boolean updateAccountWithdraw(int accNo, float amount);		//for reflecting amount withdrawn in database
	public ResultSet showAllAccount();		//for showing all account exists
	
}
