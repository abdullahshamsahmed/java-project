package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DBDataProvider {

	public static int countRows( String tableName){
        // select the number of rows in the table
		Connection conn = DBConnectionProvider.getDBConnection();
        int rowCount = -1;
        try {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM " + tableName);
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
}
