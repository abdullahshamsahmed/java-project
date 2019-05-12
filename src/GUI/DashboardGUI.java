package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import BusinessLogic.LoginDataAccess;

public class DashboardGUI extends JFrame {

	JButton btnUser;
	JButton btnCompany;
	
	JButton btnAddProduct;
	JButton btnViewProduct;
	
	JButton btnAddInvoice;
	JButton viewAddInvoice;
	
	JButton btnAddCustomer; 
	JButton ViewCustomer;
	
	JLabel loggedInUser; 
	JLabel name;
	
	
	public DashboardGUI(){
		initialComponent();
		
	}
	
	private void initialComponent() {
		this.setTitle("Dashboard");
        this.setBounds(300, 50, 1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        addComponent();
        this.setVisible(true); 
	}
	
	private void addComponent() {
		loggedInUser = new JLabel("Login user : " + LoginDataAccess.getCurrentUser()); 
		this.loggedInUser.setBounds(750, 05, 250, 30);
		this.loggedInUser.setFont(loggedInUser.getFont().deriveFont(22F));
		this.add(loggedInUser);
		
		name = new JLabel("INVOICE MAKER"); 
		this.name.setBounds(50, 20, 250, 30);
		this.name.setFont(loggedInUser.getFont().deriveFont(30F));
		this.add(name);
		
		
		btnAddProduct = new JButton("Add Product"); 
		this.btnAddProduct.setBounds(120, 180, 150, 80);
//		this.btnAddProduct.setFont(btnViewProduct.getFont().deriveFont(20F));
		this.add(btnAddProduct);
		
		this.btnAddProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				JButton_AddProductActionPerfomed(evt);
			}
		});
		
		btnViewProduct = new JButton("View Product"); 
		this.btnViewProduct.setBounds(120, 270, 150, 80);
	//	this.btnViewProduct.setFont(btnViewProduct.getFont().deriveFont(20F));
		this.add(btnViewProduct);
		this.btnViewProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				JButton_ViewProductActionPerfomed(evt);
			}
		});
		
		
		btnAddCustomer = new JButton("Add Customer"); 
		this.btnAddCustomer.setBounds(120, 370, 150, 80);
	//	this.btnViewProduct.setFont(btnViewProduct.getFont().deriveFont(20F));
		this.add(btnAddCustomer);
		this.btnAddCustomer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				JButton_AddCustomerActionPerfomed(evt);
			}
		});
		
		
		ViewCustomer = new JButton("View Customer"); 
		this.ViewCustomer.setBounds(120, 455, 150, 80);
		this.add(ViewCustomer);
		this.ViewCustomer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				JButton_ViewCustomerActionPerfomed(evt);
			}
		});
		
		
		
		btnAddInvoice = new JButton("Create Invoice"); 
		this.btnAddInvoice.setBounds(400, 180, 150, 80);
		this.add(btnAddInvoice);
		this.btnAddInvoice.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				JButton_AddInvoiceActionPerfomed(evt);
			}
		});
		
		
		viewAddInvoice = new JButton("View Invoice"); 
		this.viewAddInvoice.setBounds(400, 270, 150, 80);
		this.add(viewAddInvoice);
		this.viewAddInvoice.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				JButton_ViewInvoiceActionPerfomed(evt);
			}
		});
		
		
		
		btnUser = new JButton("User Management"); 
		this.btnUser.setBounds(400, 370, 150, 80);
		this.add(btnUser);
		this.btnUser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				JButton_UserActionPerfomed(evt);
			}
		});

		
		btnCompany = new JButton("Company Settings"); 
		this.btnCompany.setBounds(400, 455, 150, 80);
		this.add(btnCompany);
		this.btnCompany.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				JButton_CompanyActionPerfomed(evt);
			}
		});
	}
	
	
	
	private void JButton_ViewInvoiceActionPerfomed(ActionEvent evt){
		new ShowInvoiceGUI();
	}
	
	
	private void JButton_AddInvoiceActionPerfomed(ActionEvent evt){
		new AddInvoiceGUI();
	}
	private void JButton_AddCustomerActionPerfomed(ActionEvent evt){
		new AddCustomerGUI();
	}
	private void JButton_ViewCustomerActionPerfomed(ActionEvent evt){
		new ShowCustomerGUI();
	}
	private void JButton_AddProductActionPerfomed(ActionEvent evt){
		new AddProductGUI();
	}
	private void JButton_ViewProductActionPerfomed(ActionEvent evt){
		new ShowProductGUI();
	}
	private void JButton_CompanyActionPerfomed(ActionEvent evt){
		new CompanyGUI();
	}
	private void JButton_UserActionPerfomed(ActionEvent evt){
		new UserGUI();
	}
}