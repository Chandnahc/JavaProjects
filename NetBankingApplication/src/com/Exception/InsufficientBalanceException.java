package com.Exception;

//declaring user defined exception for insufficient balance
public class InsufficientBalanceException extends Exception 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String msg="Insufficient Balance !! ";		//creating string message to show when thrown
	
	//creating parameterized constructor
	public InsufficientBalanceException(String msg) 
	{
		this.msg=this.msg+msg;
	}
	
	//overriding toString method
	public String toString() 
	{
		return " "+msg;
	}
}
