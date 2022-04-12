package com.Interface;

//importing necessary classes or interface or exceptions
import com.Exception.InsufficientBalanceException;	
import com.Exception.InvalidAccountNoException;
import com.bean.Account;

//creating account services interface for summary of account service class
public interface AccountServices 	
{
	
	public int openAccount(String type,float amount);		//for opening account
	public float deposit(int accNo,float amount) throws InvalidAccountNoException;		//for depositing amount in any account
	public float withdraw(int accNo,float amount) throws InvalidAccountNoException, InsufficientBalanceException;		//for withdrawing amount from any account
	public float balEnquiry(int accNo) throws InvalidAccountNoException;		//for checking balance in any account
	public Account checkDetails(int accNo) throws InvalidAccountNoException;	//for checking all details of any account
	public void fundTransfer(int accNo,int accNo1,float amount) throws InvalidAccountNoException, InsufficientBalanceException;	//for transfer of fund from account a1 to account a2
	public void showAllAccount(); 		//for showing all accounts that exists
}
