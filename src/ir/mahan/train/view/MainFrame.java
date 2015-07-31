package ir.mahan.train.view;

import ir.mahan.train.Controller.Controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

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

public class MainFrame extends JFrame {

	//TextPanel textPanel;
	TablePanel tablePanel;
	FormPanel formPanel;
	JSplitPane splitPane;
	JTabbedPane tabbedPane;
	Controller controller;
	JFileChooser fileChooser;

	public MainFrame(String title) {
		super(title);
		setView();
		addComponent();

		setJMenuBar(createMenu());
	}

	private void setView() {
		this.setSize(940, 500);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void addComponent() {

		//textPanel = new TextPanel();
		tablePanel = new TablePanel();
		formPanel = new FormPanel();
		tabbedPane = new JTabbedPane();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel,
				tabbedPane);
		//tabbedPane.add("Text Area", textPanel);
		tabbedPane.add("Text Area", tablePanel);
		splitPane.setOneTouchExpandable(true);
		createMenu();
		this.getContentPane().add(splitPane, BorderLayout.CENTER);
		controller = new Controller();
		formPanel.setformListener(new FormListener() {

			@Override
			public void formEventOccured(FormEvent e) {

				try {
					controller.addPerson(e);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				//textPanel.setTextArea(e);
				tablePanel.setTable(e);
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

		fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new userFileFilter());

		// fileStream = new FileStream();
		loadFileMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					try {
						List<FormEvent> formEvents = controller.loadPeople(selectedFile);
								for (FormEvent e : formEvents) {
									
									//textPanel.setTextArea(e);
									tablePanel.setTable(e);
								}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});

		exportToFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					try {
						controller.SavePerson(selectedFile);
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
