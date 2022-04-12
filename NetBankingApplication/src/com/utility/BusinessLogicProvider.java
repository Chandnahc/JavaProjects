package com.utility;

//importing necessary classes or interfaces
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.Interface.AccountServices;
import com.serviceimpl.AccountServiceImpl;

//creating class for providing business logic class object to client code
public class BusinessLogicProvider 
{
	
	static String classname=null;	//creating static variable for catching class name from .properties file
	
	public static void readRepository()		//method to read the .properties file
	{
		FileInputStream fis=null;	
		
		try 
		{
			fis=new FileInputStream(".//resources//ObjectProperties.properties");	//reading .properties file
			Properties p=new Properties();
			p.load(fis); 	//loading the fileinputstream object in properties
			classname=p.getProperty("classname"); 	//setting class name
		} 
		
		catch(Exception e) 
		{
			e.printStackTrace();
		} 
		
		finally 	//finally block for closing the connection between java application and file
		{
			try 
			{
				fis.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	//static method to return object of business logic class super interface
	public static AccountServices getBusinessLogicObject() 
	{
		
		AccountServices a=null;
		
		try
		{
			readRepository();	//first reading the file
			a=(AccountServices) Class.forName(classname).getDeclaredConstructor().newInstance();	//creating new object of business logic class using reflection
		} 
		
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		return a;	//finally returning object
	}
}
