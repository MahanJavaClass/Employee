package ir.mahan.train.view;

import ir.mahan.train.Controller.Controller;
import ir.mahan.train.model.User;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class LoginFrame extends JDialog {
	private static final long serialVersionUID = 1L;
	LoginPanel loginPanel;
	User user;
	Controller controller;

	LoginFrame(final Controller controller) {
		this.controller = controller;
		setView();
		addComponent();
		connect();
	}

	private void setView() {
		this.setSize(370, 240);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setLocation(600, 300);
	}

	private void addComponent() {

		loginPanel = new LoginPanel();
		this.add(loginPanel);
		loginPanel.setLoginListener(new LoginListener() {
			@Override
			public void login() {
				try {
					final String username = loginPanel.userNameTxt.getText();
					String password = new String(loginPanel.passPassword
							.getPassword());
					if (!username.isEmpty() && !password.isEmpty()) {

						boolean userExist = controller.authenticate(username,
								password);

						if (userExist) {

							SwingUtilities.getWindowAncestor(loginPanel)
									.dispose();
							SwingUtilities.invokeLater(new Runnable() {

								@Override
								public void run() {
									new MainFrame("SZ App", username,
											controller);
								}
							});

						} else {

							throw new Exception(
									"Incorrect username or password!");
						}
					} else {
						loginPanel.checkValidity(username, password);
						throw new Exception("Username or Password is Empty!");

					}

				} catch (SQLException e) {

					JOptionPane.showMessageDialog(loginPanel, e.getMessage(),
							"خطا", JOptionPane.ERROR_MESSAGE);

				} catch (Exception e) {

					JOptionPane.showMessageDialog(loginPanel, e.getMessage(),
							"خطا", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

	}

	private void connect() {
		try {
			controller.connect();
			loginPanel.connectionMsgLabel.setText("Connected");

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(loginPanel, e.getMessage(), "خطا",
					JOptionPane.ERROR_MESSAGE);

		} catch (ClassNotFoundException e) {

			JOptionPane.showMessageDialog(loginPanel, e.getMessage(), "خطا",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}