package ir.mahan.train.view;

import ir.mahan.train.model.IuserListener;
import ir.mahan.train.model.User;

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class MainFrame extends JFrame {

	TextPanel textPanel;
	FormPanel formPanel;
	private JFileChooser fileChooser;

	public MainFrame(String title) {
		super(title);
		setView();
		addComponent();

		fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new userFileFilter());

		setJMenuBar(createMenu());
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

	public JMenuBar createMenu() {

		JMenuBar menuBar;
		JMenu fileMenu;
		JMenu windowMenu;
		JMenuItem saveMenuItem;
		JMenuItem loadFileMenuItem;
		JMenuItem exitMenuItem;

		menuBar = new JMenuBar();

		fileMenu = new JMenu("File");
		windowMenu = new JMenu("Window");

		saveMenuItem = new JMenuItem("Export Data...");
		loadFileMenuItem = new JMenuItem("Import Data...");
		exitMenuItem = new JMenuItem("Exit");
		JMenuItem prefsItem = new JMenuItem("preferences");

		fileMenu.add(saveMenuItem);
		fileMenu.add(loadFileMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);

		JMenu showMenu = new JMenu("Show");
		JMenuItem showFormItem = new JMenuItem("Person Form");

		JCheckBoxMenuItem showFormCheckBoxItem = new JCheckBoxMenuItem(
				"SHow The Form");
		showFormCheckBoxItem.setSelected(true);

		showMenu.add(showFormItem);
		showMenu.add(showFormCheckBoxItem);
		windowMenu.add(showMenu);
		windowMenu.add(prefsItem);
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);

		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitMenuItem.setMnemonic(KeyEvent.VK_X);

		prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.CTRL_MASK));

		exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));

		loadFileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
				ActionEvent.CTRL_MASK));

		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));

		loadFileMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
				}
			}
		});

		saveMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {

				}
			}
		});

		this.setJMenuBar(menuBar);

		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int action = JOptionPane.showConfirmDialog(MainFrame.this,
						"Do You Really", "Confirm Text",
						JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION)
					System.exit(0);
			}
		});

		return menuBar;

	}
}
