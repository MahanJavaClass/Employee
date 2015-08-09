package ir.mahan.train.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class LoginPanel extends JPanel{
	JLabel loginLbl, userNameLbl, passLbl;
	JTextField userNameTxt;
	JButton loginBtn;
	JPasswordField passPassword;
	
	public LoginPanel(){
		Dimension dim = getPreferredSize();
		dim.width = 500;
		dim.height=500;
		setPreferredSize(dim);
		setLayout(new GridBagLayout());
		setFormBorder();
		initializeFormComponents();
		layoutComponent();
		
	}
	private void initializeFormComponents() {
		loginLbl = new JLabel("Login Form :");
		userNameLbl = new JLabel("Enter Email:");
		passLbl = new JLabel("Enter Password:");
		userNameTxt = new JTextField();
		passPassword = new JPasswordField();
		loginBtn = new JButton("Login");
		
	}
	private void setFormBorder() {
		Border innerBorder = BorderFactory.createTitledBorder("Login");
		Border outerBorder = BorderFactory.createEmptyBorder(20, 20, 20, 100);
		setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
	}


	
	private void layoutComponent() {
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridy = 0;
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		add(userNameLbl, gc);

		gc.gridy = 0;
		gc.gridx = 1;
		//gc.gridwidth = 2;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(userNameTxt, gc);

		gc.gridy = 1;
		gc.gridx = 0;
		//gc.gridwidth = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		add(passLbl, gc);

		gc.gridy = 1;
		gc.gridx = 1;
		//gc.gridwidth = 2;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(passPassword, gc);

		gc.gridy = 2;
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(loginBtn, gc);

	}
	
//	
//	public void showData() {
//		JFrame f1 = new JFrame();
//		JLabel l, l0;
//
//		String userName = userNameTxt.getText();
//		char[] password = passPassword.getPassword();
//		String passString = new String(password);
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			Connection con = DriverManager.getConnection(
//					"jdbc:oracle:thin:@mcndesktop07:1521:xe", "sandeep",
//					"welcome");
//			PreparedStatement ps = con
//					.prepareStatement("select name from user where username=? and password=?");
//			ps.setString(1, userName);
//			ps.setString(2, passString);
//			ResultSet rs = ps.executeQuery();
//			if (rs.next()) {
//				f1.setVisible(true);
//				f1.setSize(600, 600);
//				f1.setLayout(null);
//				l = new JLabel();
//				l0 = new JLabel("you are succefully logged in..");
//				l0.setForeground(Color.blue);
//				l0.setFont(new Font("Serif", Font.BOLD, 30));
//				l.setBounds(60, 50, 400, 30);
//				l0.setBounds(60, 100, 400, 40);
//
//				f1.add(l);
//				f1.add(l0);
//				l.setText("Welcome " + rs.getString(1));
//				l.setForeground(Color.red);
//				l.setFont(new Font("Serif", Font.BOLD, 30));
//
//			} else {
//				JOptionPane
//						.showMessageDialog(null,
//								"Incorrect email-Id or password..Try Again with correct detail");
//			}
//		} catch (Exception ex) {
//			System.out.println(ex);
//		}
//	}
	

}
