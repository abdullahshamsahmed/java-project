package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import BusinessLogic.LoginDataAccess;
import Model.User;

public class UserGUI extends JFrame  {
	
	
	JLabel labelPass;
	JLabel labelName;
	
	JTextField txtFieldPass;
	JTextField txtFieldName;
	
	JButton btnAdd;
	JButton btnDel; 
	
	
	JTable userTable;
	DefaultTableModel model;
	
	public UserGUI(){
			
		initialComponent();
		ShowTableData();
	}


	private void initialComponent() {
		this.setTitle("View User");
		this.setBounds(300, 50, 800, 500);
		this.setLocationRelativeTo(null);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		addComponent();
		this.setVisible(true);
	}
	
	
	private void addComponent() {
		
		labelName = new JLabel("User Name  : ");
		labelName.setBounds(20, 50, 100, 30);
		this.add(labelName); 
		
		txtFieldName = new JTextField();
		this.txtFieldName.setBounds(120,50,160,30);
		this.add(txtFieldName);
		
		labelPass = new JLabel("Password   : ");
		labelPass.setBounds(20, 90, 100, 30);
		this.add(labelPass); 
		
		txtFieldPass = new JTextField();
		this.txtFieldPass.setBounds(120,90,160,30);
		this.add(txtFieldPass);
		
		btnAdd = new JButton("Add User");
		btnAdd.setBounds(120,125,100,30);
		this.add(btnAdd);
		this.btnAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				JButton_AddActionPerfomed(evt);
			}
		});
		
		
		userTable = new JTable(); 
        Object[] columns = {"Id","User Name","PassWord"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        
        
        userTable.setModel(model);
        
       	userTable.setBackground(Color.LIGHT_GRAY);
        userTable.setForeground(Color.black);
        Font font = new Font("",1,14);
        userTable.setFont(font);
        userTable.setRowHeight(30);
        
        JScrollPane pane = new JScrollPane(userTable);
        pane.setBounds(0, 200, 800, 200);
        this.add(pane);
        
        btnDel = new JButton("Delete");
        btnDel.setBounds(640, 410, 100, 30);
        this.add(btnDel);
        this.btnDel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				JButton_DeleteActionPerfomed(evt);
			}
		});
        
	}
	
	
	private void JButton_DeleteActionPerfomed(ActionEvent evt){
		int id = userTable.getSelectedRow();
		if (id >= 0 ) { 
			int index = Integer.parseInt(model.getValueAt(id, 0).toString());
			LoginDataAccess.Delete(index);
			ShowTableData();
		}
		
	}
	private void JButton_AddActionPerfomed(ActionEvent evt){
		try{	
			User u = new User(txtFieldName.getText(), txtFieldPass.getText()); 
			LoginDataAccess.Insert(u);
			JOptionPane.showMessageDialog(null, "User Data Added successfully");
			Clear();
			ShowTableData();
		}catch(Exception e){
			//e.printStackTrace();
			System.out.println("can't fucked the user data");
		}
		
	}
	
	
	private void Clear(){
		txtFieldPass.setText("");
		txtFieldName.setText("");
	}
	private void ClearTable() {
		int rowCount = model.getRowCount(); 
		for(int i= rowCount - 1; i>=0; i--){
			model.removeRow(i);
		} 
	}
	private void ShowTableData() {
		ClearTable(); 
		Object[] row = new Object[3];
		ArrayList<User> users = LoginDataAccess.getUserList();
		System.out.println("");
		for(User u : users) {
			row[0] = u.getId(); 
			row[1] = u.getName(); 
			row[2] = u.getPass();
			
			model.addRow(row);
 		}
	}
}
