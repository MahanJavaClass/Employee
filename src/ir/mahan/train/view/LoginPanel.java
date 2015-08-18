package ir.mahan.train.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JLabel userNameLbl, passLbl;
	JTextField userNameTxt;
	JPasswordField passPassword;
	JButton loginBtn;
	ActionLoginListener actionLoginListener;

	public LoginPanel() {
		setLayout(null);
		setFormBorder();
		layoutComponent();
	}

	public void setActionLoginListener(ActionLoginListener loginListener) {
		this.actionLoginListener = loginListener;
	}

	private void setFormBorder() {
		Border innerBorder = BorderFactory.createTitledBorder("Login");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
	}

	private void layoutComponent() {

		userNameLbl = new JLabel("User Name :");
		userNameLbl.setBounds(40, 40, 80, 25);
		add(userNameLbl);

		userNameTxt = new JTextField(20);
		userNameTxt.setBounds(130, 40, 160, 25);
		add(userNameTxt);

		passLbl = new JLabel("Password :");
		passLbl.setBounds(40, 70, 80, 25);
		add(passLbl);

		passPassword = new JPasswordField(20);
		passPassword.setBounds(130, 70, 160, 25);
		add(passPassword);

		loginBtn = new JButton("login");
		loginBtn.setBounds(140, 120, 80, 25);
		add(loginBtn);
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				actionLoginListener.login();

			}
		});

	}
}
