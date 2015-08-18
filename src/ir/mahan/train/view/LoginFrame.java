package ir.mahan.train.view;

import ir.mahan.train.Controller.Controller;
import ir.mahan.train.model.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginFrame extends JDialog {

	private static final long serialVersionUID = 1L;

	LoginPanel loginPanel;
	User user;
	Controller controller;

	LoginFrame() {
		setView();
		addComponent();
		loginPanel.setActionLoginListener(new ActionLoginListener() {
			@Override
			public void login() {
				loginToForm();

			}
		});
		// to do
		loginPanel.setKeyLoginListener(new KeyLoginListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				loginToForm();
			}

		});
	}

	private void setView() {
		this.setSize(370, 240);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setLocation(600, 300);

	}

	private void addComponent() {
		loginPanel = new LoginPanel();
		this.getContentPane().add(loginPanel);
	}

	void loginToForm() {
		controller = new Controller();
		user = new User();
		user.setUserName(loginPanel.userNameTxt.getText());
		user.setPassword(loginPanel.passPassword.getPassword());
		String passString = new String(user.getPassword());
		Boolean success;
		try {
			success = controller.authenticate(user.getUserName(), passString);
			if (success) {
				SwingUtilities.getWindowAncestor(loginPanel).dispose();
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						new MainFrame("User Form", user.getUserName());

					}
				});

			} else
				JOptionPane.showMessageDialog(null, "Login Failed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}