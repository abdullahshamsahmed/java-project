package BusinessLogic;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import DB.DBConnectionProvider;
import Model.Product;

public class ProductDataAccess {
	public static ArrayList<Product> getProductList() {
		
		ArrayList<Product> productList = new ArrayList<Product>();
		try {
			Connection con = DBConnectionProvider.getDBConnection();
			Statement  stmt = con.createStatement();
			String query = "select * from product order by Id asc";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				Product product = new Product(rs.getInt("ID"),rs.getString("Name"),rs.getInt("UnitPrice"),rs.getString("Category"),rs.getString("Description"));
				productList.add(product);
			}
		}
		catch(Exception ex) {
			System.out.println("getProductList : "+ ex);
		}
		
		return productList;
	}
	
	public static void insertProduct(Product product) {
		try {
			Connection con = DBConnectionProvider.getDBConnection();
			Statement  stmt = con.createStatement();
			String query = "INSERT INTO `product`(`Name`,`UnitPrice` , `Category`, `Description`) VALUES ('"+product.getName()+"','"+product.getUnitPrice()+"','"+product.getCategory()+"','"+product.getDescription()+"')";                        
			stmt.execute(query);
	
		}
		catch(Exception ex) {
			System.out.println("Insert Product : "+ ex);
		}
	}
	
	public static void deleteProduct(int index) {
		try {
			Connection con = DBConnectionProvider.getDBConnection();
			Statement  stmt = con.createStatement();
			String query = "DELETE FROM `product` WHERE Id='"+index+"'";                        
			stmt.execute(query);
		}
		catch(Exception ex) {
			System.out.println("Delete Product : "+ ex);
		}
	}
	public static void updateProduct(Product product) {
		try {
			Connection con = DBConnectionProvider.getDBConnection();
			Statement  stmt = con.createStatement();
			String q = "UPDATE `product` SET `Name`='"+product.getName()+"',`UnitPrice`='"+product.getUnitPrice()+"',`Category`='"+product.getCategory()+"',`Description`='"+product.getDescription()+"' WHERE Id='"+product.getId()+"'";
			stmt.execute(q);
	
		}
		catch(Exception ex) {
			System.out.println("Update Product : "+ ex);
		}
	}
	
	public static Product getProduct(int id) {
		Product product = null;
		try {
			Connection con = DBConnectionProvider.getDBConnection();
			Statement  stmt = con.createStatement();
			String query = "select * from product WHERE Id='"+id+"'";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
				product = new Product(rs.getInt("ID"),rs.getString("Name"),rs.getInt("UnitPrice"),rs.getString("Category"),rs.getString("Description"));
		}
		catch(Exception ex) {
			System.out.println("getProductList : "+ ex);
		}
		return product;
	}
	
	
	
}
