package DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionProvider {

	 private static Connection conn;
	    public static Connection getDBConnection(){
	        if(conn==null){
	            //No connection exist so, required to create the conection
	            try {
	                //STEP 1: Register JDBC driver
	                Class.forName("com.mysql.jdbc.Driver");
	                //STEP 3: Open a connection
	                conn = DriverManager.getConnection("jdbc:mysql://localhost/invoice", "root", "");
	            }catch(Exception ex){
	                System.out.println("Failed to get DBConn:: "+ex.getMessage());
	            }

	            
	            return conn;
	        }
	        else
	            return conn;
	    }
}


