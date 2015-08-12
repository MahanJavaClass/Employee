package ir.mahan.train.view;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

	LoginPanel loginPanel;

	LoginFrame() {
		setView();
		addComponent();
	}

	private void addComponent() {
		loginPanel = new LoginPanel();
		this.getContentPane().add(loginPanel);

	}

	private void setView() {
		this.setSize(400, 250);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}