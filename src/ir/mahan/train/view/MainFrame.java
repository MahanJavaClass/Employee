package ir.mahan.train.view;

import ir.mahan.train.model.Ix;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	MenuPanel menuPanel;
	TextPanel textPanel;
	FormPanel formPanel;

	public MainFrame(String title) {
		super(title);
		setView();
		addComponent();
	}

	private void setView() {
		this.setSize(600, 400);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void addComponent() {
		menuPanel = new MenuPanel();
		textPanel = new TextPanel();
		formPanel = new FormPanel();
		this.getContentPane().add(menuPanel, BorderLayout.NORTH);
		this.getContentPane().add(textPanel, BorderLayout.CENTER);
		this.getContentPane().add(formPanel, BorderLayout.WEST);

		formPanel.setIstringListener(new IstringListener() {
			public void stringEmmited(String input) {
				textPanel.setText(input);
			}
		});

	}

}
