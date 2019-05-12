package GUI;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BusinessLogic.InvoiceDataAccess;
import BusinessLogic.ProductDataAccess;
import Model.Product;
import Model.ProductsInvoice;


public class ViewInvoiceGUI {
	DefaultTableModel model;
	JTable table;
	JFrame frame;
	
	int index;
	int total;
	
	JLabel levelInvoice;
	JLabel levelTotal;
	
	public ViewInvoiceGUI(int index) {
		this.index = index;
		addComponent();
		loadTable();
		this.total = 0;
	}
	
	private void addComponent() {
		frame = new JFrame("View Invoice");
        table = new JTable(); 

        Object[] columns = {"Product Id","Product Name","Quantity","Amount","Total"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setForeground(Color.BLACK);
        Font font = new Font("",Font.PLAIN,15);
        table.setFont(font);
        table.setRowHeight(25);    
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(20, 100, 600, 250);
        frame.setLayout(null);
        frame.add(pane);
        
        this.levelInvoice = new JLabel("Invoice Code : "+index);
		this.levelInvoice.setBounds(20,50,150,25);
		this.frame.add(this.levelInvoice);
		
		this.levelTotal = new JLabel("Total : ");
		this.levelTotal.setBounds(500,360,200,25);
		this.frame.add(this.levelTotal);

        frame.setBounds(100, 100, 650, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	
	private void loadTable() {
		clearTable();
		Object[] row = new Object[5];
		ArrayList<ProductsInvoice> piList = InvoiceDataAccess.getProductsInvoiceList(index);
		for(ProductsInvoice pi : piList) {
        	int id = pi.getProductId();
			row[0] = id;
        	
        	Product p = ProductDataAccess.getProduct(id);
        	
            row[1] = p.getName();
            row[2] = p.getUnitPrice();
            
            row[3] = pi.getQuantity();
            int sum = pi.getSubTotal();
            row[4] = sum;
            
            total += sum;
            
            model.addRow(row);
        }
		
		this.levelTotal.setText("Total : "+ total);
		
	}
	
	private void clearTable() {
		int rowCount = model.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
		    model.removeRow(i);
		}
	}
}
