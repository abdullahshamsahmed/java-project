package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import BusinessLogic.LoginDataAccess;
import Model.User;

public class LoginGUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	JButton loginButton;
	JButton exitButton; 
	
	JLabel label1;
	JLabel label2; 
	
	JTextField nameTxtField;
	JPasswordField passTxtField; 
	
	public LoginGUI(){
		initialComponent();
	}
	
	private void initialComponent() {
		this.setTitle("Login");
        this.setBounds(300, 50, 400, 200);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        addComponent();
        this.setVisible(true);   
	}
	
	private void addComponent(){
		this.label1 = new JLabel("User Name : "); 
		this.label1.setBounds(20, 20, 100, 30);
		this.add(label1);
		
		this.nameTxtField = new JTextField(); 
		this.nameTxtField.setBounds(130,20,180,30);
		this.add(nameTxtField);

		this.label2 = new JLabel("Password : "); 
		this.label2.setBounds(20, 60, 100, 30);
		this.add(label2);
		
		this.passTxtField = new JPasswordField(); 
		this.passTxtField.setBounds(130,60,180,30);
		this.add(passTxtField);
		
		this.loginButton = new JButton("Login"); 
		this.loginButton.setBounds(130, 100, 105, 30);
		this.add(loginButton);
		this.loginButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				JButton_loginActionPerfomed(evt);
			}
		});
		
		this.exitButton = new JButton("Clear"); 
		this.exitButton.setBounds(240, 100, 70, 30);
		this.add(exitButton);
		this.exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				JButton_exitActionPerfomed(evt);
			}
		});
	}
	
	private void JButton_loginActionPerfomed(ActionEvent evt) {
		User u = new User(nameTxtField.getText().trim(),passTxtField.getText().trim());
		System.out.println("login : " + LoginDataAccess.validUser(u));
		if (LoginDataAccess.validUser(u)) {
			//close this frame 
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			// show dashboard
			new DashboardGUI();
		} else {
			JOptionPane.showMessageDialog(null, "Invalid UserName and Password");
		}
	}
	private void JButton_exitActionPerfomed(ActionEvent evt) {
		clearData();
	}
	private void clearData() {
		passTxtField.setText("");
		nameTxtField.setText("");
	}
}
