package ir.mahan.train.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JLabel userNameLbl, passLbl, starUserNameLbl, starPasswordLbl;
	JTextField userNameTxt;
	JPasswordField passPassword;
	JButton loginBtn;
	LoginListener loginListener;
	JLabel connectionMsgLabel;

	public LoginPanel() {
		setLayout(null);
		this.setName("tablooo");
		setFormBorder();
		layoutComponent();
	}

	public void setLoginListener(LoginListener loginListener) {
		this.loginListener = loginListener;
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
		userNameTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					KeyboardFocusManager.getCurrentKeyboardFocusManager()
							.focusNextComponent();

				}
			}

		});
		passLbl = new JLabel("Password :");
		passLbl.setBounds(40, 70, 80, 25);
		add(passLbl);

		passPassword = new JPasswordField(20);
		passPassword.setBounds(130, 70, 160, 25);
		add(passPassword);

		passPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					loginListener.login();
				}
			}

		});
		loginBtn = new JButton("login");
		loginBtn.setBounds(140, 120, 80, 25);
		loginBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				loginListener.login();

			}
		});
		add(loginBtn);

		starUserNameLbl = new JLabel("*");
		starUserNameLbl.setVisible(false);
		this.showRequiredField(300, 40, starUserNameLbl);

		starPasswordLbl = new JLabel("*");
		starPasswordLbl.setVisible(false);
		this.showRequiredField(300, 70, starPasswordLbl);

		connectionMsgLabel = new JLabel();
		connectionMsgLabel.setBounds(40, 170, 80, 25);
		Font font = new Font("TimesNewRoman", Font.BOLD, 10);
		connectionMsgLabel.setFont(font);
		add(connectionMsgLabel);

	}

	void showRequiredField(int x, int y, JLabel starLbl) {
		starLbl.setBounds(x, y, 10, 25);
		starLbl.setFont(new Font("TimesNewRoman", Font.BOLD, 18));
		starLbl.setForeground(Color.red);
		this.add(starLbl);
	}

	public void checkValidity(String user, String password) {

		if (user.isEmpty()) {
			starUserNameLbl.setVisible(true);
		} else {
			starUserNameLbl.setVisible(false);
		}

		if (password.isEmpty()) {
			starPasswordLbl.setVisible(true);
		} else {
			starPasswordLbl.setVisible(false);
		}

	}
}
