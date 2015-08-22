package ir.mahan.train.view;

import ir.mahan.train.Controller.Controller;
import ir.mahan.train.model.User;

import javax.swing.*;

import java.awt.*;

public class LoginFrame extends JDialog {

	private static final long serialVersionUID = 1L;

	LoginPanel loginPanel;
	User user;
	Controller controller;

	LoginFrame(final Controller controller) {
		this.controller = controller;
		setView();
		addComponent();
	}

	private void setView() {
		this.setSize(370, 240);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setLocation(600, 300);
	}

	private void addComponent()  {
		loginPanel = new LoginPanel();
		this.getContentPane().add(loginPanel);
		if (controller.getConnectionStatus() == true)
			loginPanel.connectionMsgLabel.setText("Connected");
		loginPanel.setActionLoginListener(new ActionLoginListener() {
			@Override
			public void login() {
				user = new User();
				user.setUserName(loginPanel.userNameTxt.getText());
				user.setPassword(loginPanel.passPassword.getPassword());
				String passString = new String(user.getPassword());
				Boolean success;
				try {
					success = controller.authenticate(user.getUserName(),
							passString);
					if (success) {
						SwingUtilities.getWindowAncestor(loginPanel).dispose();
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								new MainFrame("User Form", user.getUserName(),
										controller);

							}
						});

					} else
						JOptionPane.showMessageDialog(null, "Login Failed");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}

			}
		});
	}

	public void refresh() {
		if (controller.getConnectionStatus() == true)
			loginPanel.connectionMsgLabel.setText("Connected");;
		
	}
}