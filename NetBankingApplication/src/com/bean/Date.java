package com.bean;

//class for managing date data members
public class Date 
{
	
	//declaring variables
	private int dd;
	private int mm;
	private int yy;
	
	//declaring parameterized constructor to parse date from string
	public Date(String date) 
	{
		this.dd=Integer.parseInt(date.substring(8));
		this.mm=Integer.parseInt(date.substring(5, 7));
		this.yy=Integer.parseInt(date.substring(0, 4));
	}
	
	//declaring setter methods to update data members
	public void setDd(int dd) 
	{
		this.dd=dd;
	}
	
	public void setMm(int mm) 
	{
		this.mm=mm;
	}
	
	public void setYy(int yy) 
	{
		this.yy=yy;
	}
	
	//declaring getter methods to access data members
	public int getDd() 
	{
		return dd;
	}
	
	public int getMm() 
	{
		return mm;
	}
	
	public int getYy() 
	{
		return yy;
	}
	
	//overriding the toString method 
	public String toString() 
	{
		return yy+"-"+mm+"-"+dd;
	}
	
}
