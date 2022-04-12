package com.clientcode;

//importing necessary classes
import java.util.Scanner;

import com.Exception.InsufficientBalanceException;
import com.Exception.InvalidAccountNoException;
import com.Interface.AccountServices;
import com.serviceimpl.AccountServiceImpl;
import com.serviceimpl.UserThread;
import com.utility.BusinessLogicProvider;

//creating class for client operation
public class Client 
{
	//run it once to use the services

	public static void main(String[] args) 		//entry point of client
	{
		
		//creating object of necessary classes
		AccountServices s1=BusinessLogicProvider.getBusinessLogicObject();		//creating account service object for using its methods
		Scanner sc=new Scanner(System.in);			//scanner object for taking input from user at runtime
		UserThread u=new UserThread(s1, "Saving", 122.30f);		//this object of thread class will creating an account using object of account
		
		synchronized(s1) 	//block of synchronized activities
		{
			System.out.println("Which account do you want to open? ");
			String accountType=sc.next();
			System.out.println("Please enter the account opening amount ");
			float accountBal=sc.nextFloat();
			
			int accNo=s1.openAccount(accountType, accountBal);		//this will create an account and return its account number
			System.out.println("Account no. generated is "+accNo);		//for printing generated account number
			try 
			{	
				
				System.out.println("\nPls. enter your account number to deposit the amount ");
				accNo=sc.nextInt();
				System.out.println("\nEnter the amount you want to deposit ");
				float amount=sc.nextFloat();
				System.out.println("Current outstanding balance left: "+s1.deposit(accNo, amount));
				
				System.out.println("Pls. enter your account number to withdraw the amount ");
				accNo=sc.nextInt();
				System.out.println("\nEnter the amount you want to withdraw ");
				amount=sc.nextFloat();
				System.out.println("Current outstanding balance left: "+s1.withdraw(accNo, amount));
				
				System.out.println("\nEnter your account number to check the remaining balance ");
				accNo=sc.nextInt();
				System.out.println("Current outstanding balance left: "+s1.balEnquiry(accNo));
				
				System.out.println("\nEnter your account number for details ");
				accNo=sc.nextInt();
				System.out.println(s1.checkDetails(accNo));
				
				System.out.println("\nDo You Want to check fund transfer services? y or n");
				String c=sc.next();
				if(c.equals("y"))
				{
					System.out.println("All accounts that exists in the system \n");
					s1.showAllAccount();
					System.out.println("Enter senders account number ");
					accNo=sc.nextInt();
					System.out.println("Enter receivers account number ");
					int accNo1=sc.nextInt();
					System.out.println("Enter amount ");
					amount=sc.nextFloat();
					s1.fundTransfer(accNo, accNo1, amount);
					System.out.println("\nshowing account details after fund transfer\n");
					s1.showAllAccount();
					System.out.println("\nThank You for using our services :)");
				}
				else 
				{
					System.out.println("Thank You for using our services :)");
				}
				
				
				
			} 
			
			catch (InvalidAccountNoException e) 
			{
				e.printStackTrace();
			}
			catch(InsufficientBalanceException e) 
			{
				e.printStackTrace();
			}
			
			
		}
		
	}

}
