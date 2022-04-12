package com.bean;

import java.util.Random;

//class for managing the data members associated with accounting
public class Account 
{
		//declaring variables
		private int accNo;
		private String accType;
		private float accBal;
		static int temp = -1;
		Date accOpDate;		//date reference variable
		
		//parameterized constructor
		public Account(String accType,float accBal,String dateString) 
		{
			Random random=new Random();
			this.accType=accType;
			while(temp<0) 
			{
				temp=random.nextInt();
			}
			this.accNo=temp;
			temp=-1;
			this.accBal=accBal;
			this.accOpDate=new Date(dateString);
		}
		
		public Account() 
		{
			//This is the default account constructor
		}
		
		//declaring setter and getter methods for variables
		public void setAccOpDate(String dateString) 
		{
			this.accOpDate=new Date(dateString);
		}
		
		public String getAccOpDate() 
		{
			return String.valueOf(accOpDate);
		}
		
		public void setAccNo(int accNo) 
		{
			this.accNo=accNo;
		}
		
		public int getAccNo() 
		{
			return accNo;
		}
		
		public void setAccType(String accType) 
		{
			this.accType=accType;
		}
		
		public String getAccType() 
		{
			return accType;
		}
		
		public void setAccBal(float accBal) 
		{
			this.accBal=accBal;
		}
		
		public float getAccBal() 
		{
			return accBal;
		}
		
		//overriding toString method of object class
		public String toString() 
		{
			return "Account Details: "+accNo+"/"+accType+"/"+accBal+"/"+accOpDate;
		}
		
}
