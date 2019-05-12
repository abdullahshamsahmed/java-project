package BusinessLogic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DB.DBConnectionProvider;
import Model.Company;
import Model.User;

public class LoginDataAccess {
	private static String uName;
	public static String getCurrentUser(){
		return uName;
	}
	public static void setCurrentUser(String uname){
		uName = uname;
	}
	
	
	public static boolean validUser(User u) {
		Connection conn = DBConnectionProvider.getDBConnection();
		//String query = "SELECT * FROM  `user` ";
		String query = "SELECT UserName,Password from User where UserName='"+u.getName()+"'and Password='"+u.getPass()+"'";
	    
	    boolean valid= false;
	    try {
	          Statement st = conn.createStatement();
	          ResultSet rs = st.executeQuery(query);
	          while(rs.next()){
	        	 setCurrentUser(rs.getString("UserName"));
	        	 System.out.println(rs.getString("UserName"));
	        	 valid = true;
	          }
	       } 
	    catch (SQLException e) {
	         e.printStackTrace();
	         System.out.println("can't get the user data");
	       }
	    return valid;
	}
	
	
	public static void Insert(User u){ 
		Connection conn = DBConnectionProvider.getDBConnection();
		try { 
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO User (`UserName`,`Password`) VALUES ('"+u.getName()+"','"+u.getPass()+"')";
			stmt.execute(query);
		}
		catch(SQLException e) {
			System.out.println("can't insert the user data");
			e.printStackTrace();
		}
	}
	
	public static void Update(Company c) { 
		Connection conn = DBConnectionProvider.getDBConnection();
		try { 
			Statement stmt = conn.createStatement();
			// working query
			String query = "update company SET Name= '"+c.getName()+"', Email= '"+c.getEmail()+"', '"+c.getPhone()+"'  WHERE Id="+c.getId()+"";
			//String query = "UPDATE contact SET Name= '"+c.Name+"' WHERE Id='"+c.Id+"'";
			stmt.execute(query);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void Delete(int id) { 
		Connection conn = DBConnectionProvider.getDBConnection();
		try { 
			Statement stmt = conn.createStatement();
			String query = "DELETE FROM `User` WHERE Id='"+id+"'";
			stmt.execute(query);
		}
		catch(SQLException e) { 
			e.printStackTrace();
		}
	}
	
	public static ArrayList<User> getUserList() {
		ArrayList<User> users = new ArrayList<User>(); 
		Connection conn = DBConnectionProvider.getDBConnection(); 
		try{
			Statement stmt = conn.createStatement();
			String query = "SELECT  * FROM `User` ";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				User u = new User(rs.getInt("Id"),rs.getString("UserName"), rs.getString("Password"));
				 users.add(u);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return users;
	} 
}
