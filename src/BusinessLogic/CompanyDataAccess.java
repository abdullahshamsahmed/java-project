package BusinessLogic;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DB.DBConnectionProvider;
import Model.Company;

public class CompanyDataAccess {
	
	public static int CompanyCount(){
        // select the number of rows in the table
		Connection conn = DBConnectionProvider.getDBConnection();
        int rowCount = -1;
        try {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM " + "Company");
          // get the number of rows from the result set
          rs.next();
          rowCount = rs.getInt(1);
        }
        catch(SQLException e){
        	e.printStackTrace();
        	return 0;
        }
        return rowCount;
      }
	
	
	public static Company getCompany(){
		
		Connection conn = DBConnectionProvider.getDBConnection();
		String query = "SELECT * FROM  `company` ";
	    Statement st;
	      ResultSet rs;
	      Company company = null;
	       
	       try {
	           st = conn.createStatement();
	           rs = st.executeQuery(query);
	           while(rs.next())
	           {
	        	   company =  new Company(rs.getInt("Id"),rs.getString("Code"),rs.getString("Name"),rs.getString("Web"),rs.getString("Email"), rs.getString("Phone"), rs.getString("Address"));
	           }
	       } catch (Exception e) {
	           e.printStackTrace();
	           System.out.println("can't get the company data");
	       }
		return company;
	}
	
	
	public static void InsertContact(Company c){ 
		Connection conn = DBConnectionProvider.getDBConnection();
		try { 
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO company (`Code`,`Name`,`Web`,  `Email`, `Phone`,`Address`) VALUES ('"+c.getCode()+"','"+c.getName()+"', '"+c.getWeb()+"', '"+c.getEmail()+"', '"+c.getPhone()+"', '"+c.getAddress()+"' )";
			stmt.execute(query);
		}
		catch(SQLException e) { 
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
			String query = "DELETE FROM `company` WHERE Id='"+id+"'";
			stmt.execute(query);
		}
		catch(SQLException e) { 
			e.printStackTrace();
		}
	}
	
	
	

}
