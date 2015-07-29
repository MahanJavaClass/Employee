package ir.mahan.train.view;

import ir.mahan.train.Controller.Controller;
import ir.mahan.train.model.FileStream;
import ir.mahan.train.model.Ifile;
import ir.mahan.train.model.FormListener;

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.BufferOverflowException;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import org.omg.CORBA.portable.OutputStream;



public class MainFrame extends JFrame {

	TextPanel textPanel;
	FormPanel formPanel;
	// private JFileChooser fileChooser;
	private Ifile ifile;
	FileStream fileStream;
	JSplitPane splitPane;
	JTabbedPane tabbedPane;
	Controller controller;

	// User user;

	public void setIfile(Ifile ifile) {
		this.ifile = ifile;
	}

	public MainFrame(String title) {
		super(title);
		setView();
		addComponent();

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
		tabbedPane = new JTabbedPane();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel,
				tabbedPane);
		tabbedPane.add("Text Area", textPanel);
		splitPane.setOneTouchExpandable(true);
		createMenu();
		// this.getContentPane().add(textPanel, BorderLayout.EAST);
		// this.getContentPane().add(formPanel, BorderLayout.WEST);
		this.getContentPane().add(splitPane, BorderLayout.CENTER);
		formPanel.setIuser(new FormListener() {

			@Override
			public void formEventOccured(FormEvent e) {
				controller.addPerson(e);
				String output = e.toString();
				textPanel.setTextArea(output);
			}

		});
	}

	public JMenuBar createMenu() {

		JMenuBar menuBar;
		JMenu fileMenu;
		JMenu windowMenu;
		JMenuItem exportToFile;
		JMenuItem loadFileMenuItem;
		JMenuItem exitMenuItem;

		menuBar = new JMenuBar();

		fileMenu = new JMenu("File");
		windowMenu = new JMenu("Window");

		exportToFile = new JMenuItem("Export Data...");
		loadFileMenuItem = new JMenuItem("Import Data...");
		exitMenuItem = new JMenuItem("Exit");
		JMenuItem prefsItem = new JMenuItem("preferences");

		fileMenu.add(exportToFile);
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

		exportToFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		fileStream = new FileStream();
		loadFileMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (fileStream.getFileChooser().showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {

					try {

						textPanel.setTextArea(fileStream.readFromFile());

					} catch (Exception e) {

						e.printStackTrace();
					}
				}
			}
		});

		exportToFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (fileStream.getFileChooser().showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {

					try {

						fileStream.writeToFile(formPanel.makeUser());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
