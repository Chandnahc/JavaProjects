package com.utility;

//importing necessary classes
import java.io.FileInputStream;
import java.util.Properties;

import com.DAOinterface.DAOInterface;

//creating class for providing object of DAO implementation class
public class DAOprovider 
{
	
	public static DAOInterface getDAOObject() 
	{
		DAOInterface dao=null;	//creating object reference
		
		try 
		{
			FileInputStream fis=new FileInputStream(".//resources//ObjectProperties.properties");	//reading .properties file
			Properties p=new Properties();
			p.load(fis);	//loading file input stream  object into properties
			String DAO=p.getProperty("DAOclass");	//getting class name
			dao=(DAOInterface) Class.forName(DAO).getDeclaredConstructor().newInstance();	//creating new object for DAO implementation class
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return dao;		//finally returning object of DAO implementation super interface
	}
}
