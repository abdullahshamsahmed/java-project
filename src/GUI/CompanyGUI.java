package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import BusinessLogic.CompanyDataAccess;
import Model.Company;

public class CompanyGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	JLabel labelCode;
	JLabel labelName;
	JLabel labelWeb;
	JLabel labelEmail;
	JLabel labelPhone;
	JLabel labelAddress;
	
	JTextField txtFieldCode;
	JTextField txtFieldName;
	JTextField txtFieldWeb;
	JTextField txtFieldEmail;
	JTextField txtFieldPhone;
	JTextArea txtFieldAddress;
	
	JButton BtnSaveUpdate;
	JButton Clear;
	int Id;
	public CompanyGUI(){
		
		initialComponent();
		setOrClear();
	}
	
	void initialComponent() { 
		this.setTitle("Company form");
        this.setBounds(300, 50, 400, 450);
        this.setLayout(null);
        addComponent();
        this.setVisible(true);   
	}
	public void addComponent(){ 
		
		this.labelCode= new JLabel("Code : ");
		this.labelCode.setBounds(30, 50, 70, 25);
		this.add(this.labelCode);
		
		this.txtFieldCode = new JTextField();
		this.txtFieldCode.setBounds(90, 50, 200, 25);
		this.add(this.txtFieldCode);
		
		
		this.labelName = new JLabel("Name : ");
		this.labelName.setBounds(30, 90, 70, 25);
		this.add(this.labelName);
		
		this.txtFieldName = new JTextField();
		this.txtFieldName.setBounds(90, 90, 200, 25);
		this.add(this.txtFieldName);
		
		
		this.labelEmail = new JLabel("Email : ");
		this.labelEmail.setBounds(30, 130, 70, 25);
		this.add(this.labelEmail);
			
		this.txtFieldEmail = new JTextField();
		this.txtFieldEmail.setBounds(90, 130, 200, 25);
		this.add(this.txtFieldEmail);
		
		
		this.labelPhone = new JLabel("Phone : ");
		this.labelPhone.setBounds(30, 170, 70, 25);
		this.add(this.labelPhone);
			
		this.txtFieldPhone= new JTextField();
		this.txtFieldPhone.setBounds(90, 170, 200, 25);
		this.add(this.txtFieldPhone);
		
		this.labelWeb = new JLabel("Web : ");
		this.labelWeb.setBounds(30, 210, 70, 25);
		this.add(this.labelWeb);
			
		this.txtFieldWeb= new JTextField();
		this.txtFieldWeb.setBounds(90, 210, 200, 25);
		this.add(this.txtFieldWeb);
		
		this.labelAddress = new JLabel("Address : ");
		this.labelAddress.setBounds(30, 250, 70, 25);
		this.add(this.labelAddress);
			
		this.txtFieldAddress= new JTextArea();
		this.txtFieldAddress.setBounds(90, 250, 200, 65);
		this.add(this.txtFieldAddress);
		
		
		this.BtnSaveUpdate = new JButton("Save Changes");
		this.BtnSaveUpdate.setBounds(90, 320, 120, 30);
		this.add(BtnSaveUpdate);	
		this.BtnSaveUpdate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				JButton_addActionPerfomed(evt);
			}
		});
		
		this.Clear = new JButton("Clear");
		this.Clear.setBounds(215, 320, 75, 30);
		this.add(Clear);	
		Clear.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	                jButton_clearActionPerformed(evt);
	            }
	        });
	}
	
	
	private void JButton_addActionPerfomed(ActionEvent evt){
		
			if(CompanyDataAccess.CompanyCount() > 0) {
				//update
				Company c = new Company(Id, txtFieldCode.getText(), txtFieldName.getText(), txtFieldWeb.getText(), txtFieldEmail.getText(),txtFieldPhone.getText(), txtFieldAddress.getText());
				CompanyDataAccess.Update(c);
				JOptionPane.showMessageDialog(null, "Data updated successfully");
				//this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			} else {
				// insert
				Company c = new Company(txtFieldCode.getText(), txtFieldName.getText(), txtFieldWeb.getText(), txtFieldEmail.getText(),txtFieldPhone.getText(), txtFieldAddress.getText());
				CompanyDataAccess.InsertContact(c);
				JOptionPane.showMessageDialog(null, "Data added successfully");
				clearAll();
			}
	}
	 private void jButton_clearActionPerformed(java.awt.event.ActionEvent evt) {                                               
	       clearAll();
	    }

	 private void setOrClear(){
		 try { 
			 if(CompanyDataAccess.CompanyCount() > 0) { 
				 // set 
				 Company c = CompanyDataAccess.getCompany(); 
				 txtFieldCode.setText(c.getCode());
				 txtFieldName.setText(c.getName());
				 txtFieldWeb.setText(c.getWeb());
				 txtFieldEmail.setText(c.getEmail());
				 txtFieldPhone.setText(c.getPhone());
				 txtFieldAddress.setText(c.getAddress());
				 Id = c.getId();
			 }
			 else {
				 clearAll();
			 }
		 }
		 catch(Exception e) { 
			 e.printStackTrace();
			 System.out.println("can't set the company data");
		 }
	 }
	 private void clearAll() {
		this.txtFieldAddress.setText("");
	       this.txtFieldCode.setText("");
	       this.txtFieldName.setText("");
	       this.txtFieldWeb.setText("");
	       this.txtFieldEmail.setText("");
	       this.txtFieldPhone.setText("");
	}              
}
