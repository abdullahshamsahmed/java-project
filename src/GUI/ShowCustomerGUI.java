package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BusinessLogic.CustomerDataAccess;
import Model.Customer;

public class ShowCustomerGUI {
	
	DefaultTableModel model;
	JTable table;
	JFrame frame;
	
	public ShowCustomerGUI() {
		addComponent();
		changeTable();
	}
	
	public void addComponent() {
		frame = new JFrame("Show and Update customer");
        table = new JTable(); 

        Object[] columns = {"Id","Name","Phone","Email","Address"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        
        table.setModel(model);
        
        table.setForeground(Color.BLACK);
        Font font = new Font("",Font.PLAIN,15);
        table.setFont(font);
        table.setRowHeight(25);

        JButton btnDelete = new JButton("Delete");
        JButton btnUpdate = new JButton("Update");     
        

        btnUpdate.setBounds(600, 310, 120, 30);
        btnDelete.setBounds(750, 310, 120, 30);
        
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(20, 20, 850, 270);
        
        frame.setLayout(null);
        
        frame.add(pane);
        

        frame.add(btnDelete);
        frame.add(btnUpdate);
        
        btnUpdate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg) {
				updateCustomerListener(arg);
			}
			
		});
        
        btnDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg) {
				deleteCustomerListener(arg);
			}
			
		});
        
        frame.setSize(900,400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
	}
	
	public void changeTable() {
		clearTable();
		Object[] row = new Object[5];
		ArrayList<Customer> customer = CustomerDataAccess.getCustomerList();
        for(Customer c : customer) {
        	row[0] = c.getId();
            row[1] = c.getName();
            row[2] = c.getPhone();
            row[3] = c.getEmail();
            row[4] = c.getAddress();
            
            model.addRow(row);
        }
	}
	
	public void clearTable() {
		int rowCount = model.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
		    model.removeRow(i);
		}
	}
	
	public void updateCustomerListener(ActionEvent arg) {
		int i = table.getSelectedRow();
        
        if(i >= 0) 
        {
        	Customer customer = new Customer(Integer.parseInt( model.getValueAt(i, 0).toString() ),model.getValueAt(i, 1).toString(),model.getValueAt(i, 2).toString() ,model.getValueAt(i, 3).toString(),model.getValueAt(i, 4).toString());
        	       	
        	new AddCustomerGUI(customer);
        	this.frame.dispatchEvent(new WindowEvent(this.frame, WindowEvent.WINDOW_CLOSING)); 
        }
        else{
        	JOptionPane.showMessageDialog(null, "Please select a row");
        }
	}
	
	public void deleteCustomerListener(ActionEvent arg) {
		int i = table.getSelectedRow();
        
        if(i >= 0){
        	int index = Integer.parseInt( model.getValueAt(i, 0).toString() );
        	try {
    			
        		int dialogButton = JOptionPane.YES_NO_OPTION;
        		int dialogResult = JOptionPane.showConfirmDialog(this.frame, "Are you sure?", "Confirmation", dialogButton);
        		if(dialogResult == 0) {
        		  CustomerDataAccess.deleteCustomer(index);
        		  changeTable();
        		}			
    	
    		}
    		catch(Exception ex) {
    			System.out.println("Delete Customer : "+ ex);
    		}
            
        }
        else{
        	JOptionPane.showMessageDialog(null, "Please select a row");
        }
	}
}
