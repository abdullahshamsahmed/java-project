package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BusinessLogic.CustomerDataAccess;
import BusinessLogic.InvoiceDataAccess;
import Model.Customer;
import Model.Invoice;

public class ShowInvoiceGUI {
	
	DefaultTableModel model;
	JTable table;
	JFrame frame;
	
	public ShowInvoiceGUI() {
		addComponent();
		changeTable();
	}
	
	public void addComponent() {
		frame = new JFrame("Show Invoice");
        table = new JTable(); 
        

        Object[] columns = {"Id","Code","Customer","Payment","Total"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        
        table.setModel(model);
        
        table.setForeground(Color.BLACK);
        Font font = new Font("",Font.PLAIN,15);
        table.setFont(font);
        table.setRowHeight(25);

        JButton btnDelete = new JButton("Delete");
        JButton btnView = new JButton("View");     
        

        btnView.setBounds(600, 310, 120, 30);
        btnDelete.setBounds(750, 310, 120, 30);
        
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(20, 20, 850, 270);
        
        frame.setLayout(null);
        
        frame.add(pane);
        

        frame.add(btnDelete);
        frame.add(btnView);
        
        btnView.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg) {
				viewInvoiceListener(arg);
			}
			
		});
        
        btnDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg) {
				deleteInvoiceListener(arg);
			}
			
		});
        
        frame.setSize(900,400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
	}
	
	public void changeTable() {
		clearTable();
		Object[] row = new Object[5];
		ArrayList<Invoice> invoice = InvoiceDataAccess.getInvoiceList();
        for(Invoice i : invoice) {
        	row[0] = i.getId();
            row[1] = i.getCode();
            Customer c = CustomerDataAccess.getCustomer(i.getCustomerId());
            row[2] = c.getName();
            row[3] = i.getPayment();
            row[4] = i.getTotal();
            
            model.addRow(row);
        }
	}
	
	public void clearTable() {
		int rowCount = model.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
		    model.removeRow(i);
		}
	}
	
	public void viewInvoiceListener(ActionEvent arg) {
		int i = table.getSelectedRow();
        
        if(i >= 0) 
        { 
        	int index = Integer.parseInt( model.getValueAt(i, 0).toString() );
        	new ViewInvoiceGUI(index);
        }
        else{
        	JOptionPane.showMessageDialog(null, "Please select a row");
        }
	}
	
	public void deleteInvoiceListener(ActionEvent arg) {
		int i = table.getSelectedRow();
        
        if(i >= 0){
        	int index = Integer.parseInt( model.getValueAt(i, 0).toString() );
        	try {
    			
        		int dialogButton = JOptionPane.YES_NO_OPTION;
        		int dialogResult = JOptionPane.showConfirmDialog(this.frame, "Are you sure?", "Confirmation", dialogButton);
        		if(dialogResult == 0) {
        		  InvoiceDataAccess.deleteInvoice(index);
        		  InvoiceDataAccess.deleteProductInvoice(index);
        		  changeTable();
        		}			
    	
    		}
    		catch(Exception ex) {
    			System.out.println("Delete Invoice : "+ ex);
    		}
            
        }
        else{
        	JOptionPane.showMessageDialog(null, "Please select a row");
        }
	}
}
