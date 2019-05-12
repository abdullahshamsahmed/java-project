package BusinessLogic;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import DB.DBConnectionProvider;
import Model.Customer;

public class CustomerDataAccess {
	public static ArrayList<Customer> getCustomerList() {
		
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		try {
			Connection con = DBConnectionProvider.getDBConnection();
			Statement  stmt = con.createStatement();
			String query = "select * from customer order by Id asc";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				Customer customer = new Customer(rs.getInt("ID"),rs.getString("Name"),rs.getString("Phone"),rs.getString("Email"),rs.getString("Address"));
				customerList.add(customer);
			}
		}
		catch(Exception ex) {
			System.out.println("getCustomerList : "+ ex);
		}
		
		return customerList;
	}
	
	public static void insertCustomer(Customer customer) {
		try {
			Connection con = DBConnectionProvider.getDBConnection();
			Statement  stmt = con.createStatement();
			String query = "INSERT INTO `customer`(`Name`,`Phone` , `Email`, `Address`) VALUES ('"+customer.getName()+"','"+customer.getPhone()+"','"+customer.getEmail()+"','"+customer.getAddress()+"')";                        
			stmt.execute(query);
	
		}
		catch(Exception ex) {
			System.out.println("Insert Customer : "+ ex);
		}
	}
	
	public static void deleteCustomer(int index) {
		try {
			Connection con = DBConnectionProvider.getDBConnection();
			Statement  stmt = con.createStatement();
			String query = "DELETE FROM `customer` WHERE Id='"+index+"'";                        
			stmt.execute(query);
		}
		catch(Exception ex) {
			System.out.println("Delete ustomer : "+ ex);
		}
	}
	public static void updateCustomer(Customer customer) {
		try {
			Connection con = DBConnectionProvider.getDBConnection();
			Statement  stmt = con.createStatement();
			String q = "UPDATE `customer` SET `Name`='"+customer.getName()+"',`Phone`='"+customer.getPhone()+"',`Email`='"+customer.getEmail()+"',`Address`='"+customer.getAddress()+"' WHERE Id='"+customer.getId()+"'";
			stmt.execute(q);
	
		}
		catch(Exception ex) {
			System.out.println("Update Customer : "+ ex);
		}
	}
	
	public static Customer getCustomer(int id) {
		Customer customer = null;
		try {
			Connection con = DBConnectionProvider.getDBConnection();
			Statement  stmt = con.createStatement();
			String query = "SELECT * FROM `customer` WHERE Id='"+id+"'";                        
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				customer = new Customer(rs.getInt("ID"),rs.getString("Name"),rs.getString("Phone"),rs.getString("Email"),rs.getString("Address"));
			}
			
		}
		catch(Exception ex) {
			System.out.println("get Customer : "+ ex);
		}
		
		return customer;
	}
}
