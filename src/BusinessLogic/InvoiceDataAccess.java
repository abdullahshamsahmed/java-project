package BusinessLogic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import DB.DBConnectionProvider;
import Model.Invoice;
import Model.ProductsInvoice;

public class InvoiceDataAccess {
	
	public static int insetInvoice(Invoice i) {
		int id=0;
		try {
			Connection con = DBConnectionProvider.getDBConnection();
			Statement  stmt = con.createStatement();
			String query = "INSERT INTO `invoice`(`Code`,`Payment` , `Total`, `CustomerId`) VALUES ('"+i.getCode()+"','"+i.getPayment()+"','"+i.getTotal()+"','"+i.getCustomerId()+"')";                        
			stmt.execute(query);
			
			query = "SELECT * FROM `invoice` order by id desc limit 1";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				id =  rs.getInt("Id");
			}
	
		}
		catch(Exception ex) {
			System.out.println("Insert Invoice : "+ ex);
		}
		
		return id;
	}
	
	public static void insertProductsInvoice(ArrayList<ProductsInvoice> pi) {
		try {
			Connection con = DBConnectionProvider.getDBConnection();
			Statement  stmt = con.createStatement();
			
			for(ProductsInvoice p : pi ) { 
				String query = "INSERT INTO `productsinvoice`(`ProductId`, `Subtotal`, `Quantity`, `invoiceId`) VALUES ('"+p.getProductId()+"','"+p.getSubTotal()+"','"+p.getQuantity()+"','"+p.getInvoiceId()+"')";
				stmt.execute(query);
			}
	
		}
		catch(Exception ex) {
			System.out.println("Insert ProductsInvoice : "+ ex);
		}
	}
	
	public static ArrayList<Invoice> getInvoiceList() {
		
		ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
		try {
			Connection con = DBConnectionProvider.getDBConnection();
			Statement  stmt = con.createStatement();
			String query = "select * from invoice order by Id asc";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				Invoice invoice = new Invoice(rs.getInt("ID"),rs.getString("Code"),rs.getString("Payment"),rs.getInt("Total"),rs.getInt("customerId"));
				invoiceList.add(invoice);
			}
		}
		catch(Exception ex) {
			System.out.println("getinvoicetList : "+ ex);
		}
		
		return invoiceList;
	}
	
	public static void deleteInvoice(int index) {
		try {
			Connection con = DBConnectionProvider.getDBConnection();
			Statement  stmt = con.createStatement();
			String query = "DELETE FROM `invoice` WHERE Id='"+index+"'";                        
			stmt.execute(query);
		}
		catch(Exception ex) {
			System.out.println("Delete Invoice : "+ ex);
		}
	}
	
	public static ArrayList<ProductsInvoice> getProductsInvoiceList(int index) {
		
		ArrayList<ProductsInvoice> piList = new ArrayList<ProductsInvoice>();
		try {
			Connection con = DBConnectionProvider.getDBConnection();
			Statement  stmt = con.createStatement();
			String query = "select * FROM `productsinvoice` WHERE invoiceId='"+index+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				ProductsInvoice pi = new ProductsInvoice(rs.getInt("Id"),rs.getInt("ProductId"),rs.getInt("Subtotal"),rs.getInt("Quantity"),rs.getInt("invoiceId"));
				piList.add(pi);
			}
		}
		catch(Exception ex) {
			System.out.println("getinvoicetList : "+ ex);
		}
		
		return piList;
	}
	
	public static void deleteProductInvoice(int index) {
		try {
			Connection con = DBConnectionProvider.getDBConnection();
			Statement  stmt = con.createStatement();
			String query = "DELETE FROM `productsinvoice` WHERE invoiceId='"+index+"'";                        
			stmt.execute(query);
		}
		catch(Exception ex) {
			System.out.println("Delete Product Invoice : "+ ex);
		}
	}

}
