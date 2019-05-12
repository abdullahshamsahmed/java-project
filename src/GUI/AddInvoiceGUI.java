package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import BusinessLogic.CustomerDataAccess;
import BusinessLogic.InvoiceDataAccess;
import BusinessLogic.ProductDataAccess;
import Model.Customer;
import Model.Invoice;
import Model.Product;
import Model.ProductsInvoice;

public class AddInvoiceGUI {
	
	DefaultTableModel model;
	JTable table;
	JFrame frame;
	int sum;
	ArrayList<Customer> cl;
	
	JComboBox<Customer> customerCombo;
	JComboBox<Product> productCombo;
	JComboBox<String> paymentCombo;
	
	JTextField textFieldInvice;
	JTextField textFieldQuality;
	
	JButton btnAdd;
	JButton btnSave;
	
	JLabel levelCode;
	JLabel levelCustomer;
	JLabel levelProduct;
	JLabel levelQuantity;
	JLabel levelPayment;
	JLabel levelTotal;
	
	public AddInvoiceGUI() {
		addComponent();
		loadCombo();
	}
	
	public void addComponent() {
		frame = new JFrame("Add Invoice");
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
        pane.setBounds(20, 230, 600, 150);
        frame.setLayout(null);
        frame.add(pane);
        
        this.levelCode = new JLabel("Invoice Code : ");
		this.levelCode.setBounds(20,50,150,25);
		this.frame.add(this.levelCode);
		
		this.levelCustomer = new JLabel("Customer : ");
		this.levelCustomer.setBounds(400,50,150,25);
		this.frame.add(this.levelCustomer);
		
		this.textFieldInvice = new JTextField();
		this.textFieldInvice.setBounds(20,80,200,25);
		this.frame.add(this.textFieldInvice);
		
		this.customerCombo = new JComboBox<Customer>();
		this.customerCombo.setBounds(400,80,200,25);
		this.frame.add(customerCombo);
		
		this.levelProduct = new JLabel("Product : ");
		this.levelProduct.setBounds(20,150,150,25);
		this.frame.add(this.levelProduct);
		
		this.levelQuantity = new JLabel("Quantity : ");
		this.levelQuantity.setBounds(270,150,150,25);
		this.frame.add(this.levelQuantity);
		
		this.productCombo = new JComboBox<Product>();
		this.productCombo.setBounds(20,180,200,25);
		this.frame.add(productCombo);
		
		this.textFieldQuality = new JTextField();
		this.textFieldQuality.setBounds(270,180,160,25);
		this.frame.add(this.textFieldQuality);
		
		this.btnAdd = new JButton("ADD");
		this.btnAdd.setBounds(500,180,110,25);
		this.frame.add(this.btnAdd);
		
		this.levelPayment = new JLabel("Payment : ");
		this.levelPayment.setBounds(20,380,150,25);
		this.frame.add(this.levelPayment);
		
		this.paymentCombo = new JComboBox<String>();
		this.paymentCombo.setBounds(20,410,200,25);
		this.frame.add(paymentCombo);
		
		this.levelTotal = new JLabel("Total : ");
		this.levelTotal.setBounds(500,380,200,25);
		this.frame.add(this.levelTotal);
		
		this.btnSave = new JButton("SAVE");
		this.btnSave.setBounds(300,450,150,30);
		this.frame.add(this.btnSave);
        
        
		btnAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg) {
				addListener(arg);
			}
			
		});
        
        btnSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg) {
				saveInvoiceListener(arg);
			}
			
		});
        
        sum = 0;

        frame.setBounds(100, 100, 650, 580);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
	}
	
	private void loadCombo() {
		cl = CustomerDataAccess.getCustomerList();
		this.customerCombo.removeAllItems();
		for(Customer c : cl) {
			this.customerCombo.addItem(c);
		}
		
		ArrayList<Product> pl = ProductDataAccess.getProductList();
		this.productCombo.removeAllItems();
		for(Product p : pl) {
			this.productCombo.addItem(p);
		}
		
		this.paymentCombo.addItem("Cash");
		this.paymentCombo.addItem("Check");
		this.paymentCombo.addItem("Paypal");
	}
	
	
	public void addListener(ActionEvent arg) {
        
		Product p  = (Product)this.productCombo.getSelectedItem();
		
		Object[] row = new Object[5];
        	row[0] = p.getId();
            row[1] = p.getName();
            row[2] = textFieldQuality.getText();
            row[3] = p.getUnitPrice();
            int temp = p.getUnitPrice() * Integer.parseInt(textFieldQuality.getText()); 
            row[4] = temp;
            
            sum += temp;
            
            this.levelTotal.setText("Total : "+sum);
            
            model.addRow(row);
	}
	
	public void saveInvoiceListener(ActionEvent arg) {
    	try {
    		String code = this.textFieldInvice.getText();
    		String payment =(String) this.paymentCombo.getSelectedItem();
    		Customer name =(Customer) this.customerCombo.getSelectedItem();
    		
    		Invoice i = new Invoice(code,payment,sum,name.getId());
    		
    		int id = InvoiceDataAccess.insetInvoice(i);
    		 		
    		ArrayList<ProductsInvoice> piList = new ArrayList<ProductsInvoice>();
    		
    		for (int r = 0; r < model.getRowCount(); r++) {
    			
    			ProductsInvoice pi = new ProductsInvoice(Integer.parseInt( model.getValueAt(r, 0).toString() ),Integer.parseInt( model.getValueAt(r, 4).toString() ),Integer.parseInt( model.getValueAt(r, 2).toString() ), id);
    			
    			piList.add(pi);
    		}
    		
    		InvoiceDataAccess.insertProductsInvoice(piList);
    		
    		JOptionPane.showMessageDialog(null, "Invoice Save Sucessfully");
			clearAll();
    		
		}
		catch(Exception ex) {
			System.out.println("Add Invoice : "+ ex);
		}
	}
	
	private void clearAll() {
		this.textFieldInvice.setText("");
		this.textFieldQuality.setText("");
		this.levelTotal.setText("Total : ");
		model.setRowCount(0);
	}
	
	
}
