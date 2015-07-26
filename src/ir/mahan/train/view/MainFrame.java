package ir.mahan.train.view;

import ir.mahan.train.model.IuserListener;
import ir.mahan.train.model.User;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame {

	TextPanel textPanel;
	FormPanel formPanel;

	public MainFrame(String title) {
		super(title);
		setView();
		addComponent();
	}

	private void setView() {
		this.setSize(740, 500);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void addComponent() {

		textPanel = new TextPanel();
		formPanel = new FormPanel();
		createMenu();
		this.getContentPane().add(textPanel, BorderLayout.EAST);
		this.getContentPane().add(formPanel, BorderLayout.WEST);

		formPanel.setIuser(new IuserListener() {

			@Override
			public void userEmitted(User user) {
				textPanel.setTextArea(user);
			}

		});
	}

	public void createMenu() {
		
		JMenuBar menuBar;
		JMenu fileMenu;
		JMenuItem saveMenuItem;
		JMenuItem loadFileMenuItem;
		JMenuItem exitMenuItem;

		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		
		menuBar.add(fileMenu);
		saveMenuItem = new JMenuItem("Save to File");
		loadFileMenuItem = new JMenuItem("Load from File");
		exitMenuItem = new JMenuItem("Exit");
		fileMenu.add(saveMenuItem);
		fileMenu.add(loadFileMenuItem);
		fileMenu.add(exitMenuItem);
		this.setJMenuBar(menuBar);
		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}
}
