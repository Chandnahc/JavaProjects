package com.utility;

//importing necessary updates
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

//creating class for providing database connection object through encapsulating it in a static method
public class DbConnectionProvider 
{
	//static method to read properties from file and establishing connection and returning it
	public static Connection getDbConnection() 
	{
		Connection con=null;	//creating connection object to return 
		
		try 
		{
			FileInputStream fis=new FileInputStream(".//resources//DbConfig.properties");	//reading .properties file
			Properties p=new Properties();
			p.load(fis);	//loading file input stream object in properties
			String classname=p.getProperty("driverclass");		//getting class name for jdbc driver
			String url=p.getProperty("url");	//getting connection url 
			String username=p.getProperty("username");		//getting username of database
			String password=p.getProperty("password");		//getting password of database
			
			Class.forName(classname);
			con=DriverManager.getConnection(url,username,password);		//establishing connection
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return con;		//finally returning connection object
	}
}
