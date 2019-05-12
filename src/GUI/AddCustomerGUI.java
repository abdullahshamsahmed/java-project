package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import BusinessLogic.CustomerDataAccess;
import Model.Customer;

public class AddCustomerGUI {
	Customer customer;
	JFrame frame;
	
	JLabel levelAddCustomer;
	
	JLabel levelName;
	JLabel levelPhone;
	JLabel levelEmail;
	JLabel levelAddress;
	
	JTextField textFieldName;
	JTextField textFieldPhone;
	JTextField textFieldEmail;
	JTextField textFieldAddress;
	
	JButton buttonAdd;
	
	public AddCustomerGUI() {
		addComponenet();
	}
	public AddCustomerGUI(Customer customer) {
		this.customer = customer;
		addComponenet();
		setOrClear();
		this.textFieldName.setText(customer.getName());
		this.textFieldEmail.setText(customer.getEmail());
		this.textFieldPhone.setText(customer.getPhone());
		this.textFieldAddress.setText(customer.getAddress());
	}
	
	private void addComponenet(){
		this.frame = new JFrame("Add Customer");
		this.frame.setBounds(300,100,500,500);
		this.frame.setLayout(null);
		
		this.levelAddCustomer = new JLabel("Add Customer");
		this.levelAddCustomer.setBounds(100,70,200,30);
		this.levelAddCustomer.setFont(levelAddCustomer.getFont().deriveFont(25f));
		this.frame.add(this.levelAddCustomer);
		
		this.levelName = new JLabel("Name : ");
		this.levelName.setBounds(100,150,100,25);
		this.frame.add(this.levelName);
		
		this.levelPhone = new JLabel("Phone : ");
		this.levelPhone.setBounds(100,200,100,25);
		this.frame.add(this.levelPhone);
		
		this.levelEmail = new JLabel("Email : ");
		this.levelEmail.setBounds(100,250,100,25);
		this.frame.add(this.levelEmail);
		
		this.levelAddress = new JLabel("Address : ");
		this.levelAddress.setBounds(100,300,100,25);
		this.frame.add(this.levelAddress);
		
		this.textFieldName = new JTextField();
		this.textFieldName.setBounds(200,150,200,25);
		this.frame.add(this.textFieldName);
		
		this.textFieldPhone = new JTextField();
		this.textFieldPhone.setBounds(200,200,200,25);
		this.frame.add(this.textFieldPhone);
		
		this.textFieldEmail = new JTextField();
		this.textFieldEmail.setBounds(200,250,200,25);
		this.frame.add(this.textFieldEmail);
		
		this.textFieldAddress = new JTextField();
		this.textFieldAddress.setBounds(200,300,200,25);
		this.frame.add(this.textFieldAddress);
		
		this.buttonAdd = new JButton("Save Customer");
		this.buttonAdd.setBounds(100,370,200,30);
		this.frame.add(this.buttonAdd);
		this.buttonAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg) {
				saveProductListener(arg);
			}
			
		});
			
		this.frame.setVisible(true);
	}
	private void setOrClear() {
		this.buttonAdd.setText("Update Customer");
	}
	
	private void saveProductListener(ActionEvent arg) {
		if(arg.getActionCommand()=="Save Customer") {
			
			Customer customer = new Customer(this.textFieldName.getText(), this.textFieldPhone.getText() ,this.textFieldEmail.getText(),this.textFieldAddress.getText());
			
			CustomerDataAccess.insertCustomer(customer );
			
			JOptionPane.showMessageDialog(null, "Customer Added Sucessfully");
			clearAll();
		}
		else if(arg.getActionCommand()=="Update Customer") {
			Customer c = new Customer(this.customer.getId(),this.textFieldName.getText(), this.textFieldPhone.getText(),this.textFieldEmail.getText(),this.textFieldAddress.getText());
			CustomerDataAccess.updateCustomer(c);			
			JOptionPane.showMessageDialog(null, "Customer Update Sucessfully");
			new ShowCustomerGUI();
			this.frame.dispatchEvent(new WindowEvent(this.frame, WindowEvent.WINDOW_CLOSING));
		}
	}
	private void clearAll() {
		this.textFieldName.setText("");
		this.textFieldPhone.setText("");
		this.textFieldEmail.setText("");
		this.textFieldAddress.setText("");
	}
}
