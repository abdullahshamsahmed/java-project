package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import BusinessLogic.ProductDataAccess;
import Model.Product;

public class AddProductGUI {
	Product product;
	JFrame frame;
	
	JLabel levelAddProduct;
	
	JLabel levelName;
	JLabel levelUnitPrice;
	JLabel levelCategory;
	JLabel levelDescription;
	
	JTextField textFieldName;
	JTextField textFieldUnitPrice;
	JTextField textFieldCategory;
	JTextField textFieldDescription;
	
	JButton buttonAdd;
	
	public AddProductGUI() {
		addComponenet();
	}
	public AddProductGUI(Product product) {
		this.product = product;
		addComponenet();
		setOrClear();
		this.textFieldName.setText(product.getName());
		this.textFieldCategory.setText(product.getCategory());
		this.textFieldUnitPrice.setText(String.valueOf(product.getUnitPrice()) );
		this.textFieldDescription.setText(product.getDescription());
	}
	
	private void addComponenet(){
		this.frame = new JFrame("Add Product");
		this.frame.setBounds(300,100,500,500);
		this.frame.setLayout(null);
		
		this.levelAddProduct = new JLabel("Add Product");
		this.levelAddProduct.setBounds(100,70,200,30);
		this.levelAddProduct.setFont(levelAddProduct.getFont().deriveFont(25f));
		this.frame.add(this.levelAddProduct);
		
		this.levelName = new JLabel("Name : ");
		this.levelName.setBounds(100,150,100,25);
		this.frame.add(this.levelName);
		
		this.levelUnitPrice = new JLabel("Unit Price : ");
		this.levelUnitPrice.setBounds(100,200,100,25);
		this.frame.add(this.levelUnitPrice);
		
		this.levelCategory = new JLabel("Category : ");
		this.levelCategory.setBounds(100,250,100,25);
		this.frame.add(this.levelCategory);
		
		this.levelDescription = new JLabel("Description : ");
		this.levelDescription.setBounds(100,300,100,25);
		this.frame.add(this.levelDescription);
		
		this.textFieldName = new JTextField();
		this.textFieldName.setBounds(200,150,200,25);
		this.frame.add(this.textFieldName);
		
		this.textFieldUnitPrice = new JTextField();
		this.textFieldUnitPrice.setBounds(200,200,200,25);
		this.frame.add(this.textFieldUnitPrice);
		
		this.textFieldCategory = new JTextField();
		this.textFieldCategory.setBounds(200,250,200,25);
		this.frame.add(this.textFieldCategory);
		
		this.textFieldDescription = new JTextField();
		this.textFieldDescription.setBounds(200,300,200,25);
		this.frame.add(this.textFieldDescription);
		
		this.buttonAdd = new JButton("Save Product");
		this.buttonAdd.setBounds(100,370,150,30);
		this.frame.add(this.buttonAdd);
		this.buttonAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg) {
				saveProductListener(arg);
			}
			
		});
			
		this.frame.setVisible(true);
	}
	private void setOrClear() {
		this.buttonAdd.setText("Update Product");
	}
	
	private void saveProductListener(ActionEvent arg) {
		if(arg.getActionCommand()=="Save Product") {
			
			Product product = new Product(this.textFieldName.getText(),Integer.parseInt( this.textFieldUnitPrice.getText() ),this.textFieldCategory.getText(),this.textFieldDescription.getText());
			
			ProductDataAccess.insertProduct(product);
			
			JOptionPane.showMessageDialog(null, "Product Added Sucessfully");
			clearAll();
		}
		else if(arg.getActionCommand()=="Update Product") {
			Product p = new Product(this.product.getId(),this.textFieldName.getText(),Integer.parseInt( this.textFieldUnitPrice.getText() ),this.textFieldCategory.getText(),this.textFieldDescription.getText());
			ProductDataAccess.updateProduct(p);			
			JOptionPane.showMessageDialog(null, "Product Update Sucessfully");
			new ShowProductGUI();
			this.frame.dispatchEvent(new WindowEvent(this.frame, WindowEvent.WINDOW_CLOSING));
		}
	}
	private void clearAll() {
		this.textFieldName.setText("");
		this.textFieldUnitPrice.setText("");
		this.textFieldCategory.setText("");
		this.textFieldDescription.setText("");
	}
}
