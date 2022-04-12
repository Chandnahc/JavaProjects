package com.Exception;

//creating custom exception class for invalid account exception
public class InvalidAccountNoException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String msg="Account number does not exists ";	//creating string message to show when thrown 
	
	//parameterized constructor
	public InvalidAccountNoException(String msg) 
	{
		this.msg=this.msg+msg;
	}
	
	//overriding toString method
	public String toString() 
	{
		return " "+msg;
	}
}
