package com.DAOimpl;

//importing necessary classes or interfaces
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.DAOinterface.DAOInterface;
import com.bean.Account;
import com.utility.DbConnectionProvider;

//creating DAOImpl for CRUD operations of database
public class DAOImpl implements DAOInterface 
{

	Connection con=DbConnectionProvider.getDbConnection();	//to get connection object
	PreparedStatement pst;	//to write SQL statements with runtime values
	ResultSet rs;	//object of result set 
	
	//method to insert account details in database
	public int insertAccount(Account acc) 
	{
		
		int countUpdate=0;	//for returning the no. of updated rows
		
		try 
		{
			pst=con.prepareStatement("insert into Account values (?,?,?,TO_DATE(?,'YYYY/MM/DD'))");		//declaring preparedStatement
			pst.setInt(1, acc.getAccNo());
			pst.setString(2, acc.getAccType());
			pst.setFloat(3, acc.getAccBal());
			pst.setString(4, acc.getAccOpDate());
			countUpdate=pst.executeUpdate();	//executing the update
		} 
		
		catch (SQLException e) 
		{
			System.out.println("unknown exception");
			//e.printStackTrace();
		}
		
		return countUpdate;		//returning no. of row updated
		
	}

	//method to retrieve account details from database
	public Account retrieveAccountData(int accNo) 
	{
		
		Account acc=new Account();		//creating account reference to return the data in form of Account
		
		try 
		{
			pst=con.prepareStatement("select * from Account where accNo=?");	//declaring prepared statement
			pst.setInt(1, accNo);	//setting the accNo in place of ? in prepared statement
			rs=pst.executeQuery();	//executing query
			
			if(rs.next()) 	//checking if rs.next exists or not
			{
				acc.setAccNo(rs.getInt(1));
				acc.setAccType(rs.getString(2));
				acc.setAccBal(rs.getFloat(3));
				String date=String.valueOf(rs.getDate(4).toLocalDate());	//creating string variable to catch date in local format and in the form of string
				acc.setAccOpDate(date);		//setting the account opening date by passing date in string literal
			}
			else 
			{
				acc=null;	//if no such account exists then setting null value
			}
			
		}
		
		catch(Exception e) 
		{
			System.out.println("system unknown exception");
			//e.printStackTrace();
		}
		
		return acc;		//returning the account object
		
	}

	//method for reflecting the deposited amount in database
	public boolean updateAccountDeposit(int accNo,float amount) 
	{
		
		int updateCount=0;		//variable to count the no. of row updated
		
		try 
		{
			pst=con.prepareStatement("update Account set accBal=accBal+? where accNo=?");	//declaring the prepared statement
			pst.setFloat(1, amount);
			pst.setInt(2, accNo);
			updateCount=pst.executeUpdate();	//executing update
		}
		
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		
		if(updateCount==0)		//checking if no row updated then return false else return true
			return false;
		else
			return true;
		
	}

	//method for reflecting remaining balance from database
	public float retrieveAccountBal(int accNo) 
	{
		
		float bal = 0;		//float variable to return the account balance from database
		
		try 
		{
			pst=con.prepareStatement("select accBal from Account where accNo=?");	//declaring prepared statement
			pst.setInt(1, accNo);		//setting placeholder
			rs=pst.executeQuery();
			
			while(rs.next()) 
			{
				bal=rs.getFloat(1);		//getting value at index 1 which in balance in this case
			}
			
		}
		
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		
		return bal;		//returning balance
		
	}

	//method for reflecting the withdrawn amount in database
	public boolean updateAccountWithdraw(int accNo, float amount) 
	{
		
		int updateCount=0;		//integer variable for counting no. of rows updated
		
		try 
		{
			pst=con.prepareStatement("update Account set accBal=accBal-? where accNo=?");		//declaring prepared statement
			pst.setFloat(1, amount);	//setting placeholder
			pst.setInt(2, accNo);
			updateCount=pst.executeUpdate();	//executing update
		}
		
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		
		if(updateCount==0)		//checking if no row updated then return false else return true
			return false;
		else
			return true;
		
	}
	
	//method for returning all the data exists in account table
	public ResultSet showAllAccount() 
	{
		try 
		{
			pst=con.prepareStatement("select * from Account");
			rs=pst.executeQuery();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		return rs;
	}
}
