package ir.mahan.train.view;

import ir.mahan.train.Controller.Controller;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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

	private static final long serialVersionUID = 1L;
	private TextPanel textPanel;
	private TablePanel tablePanel;
	private FormPanel formPanel;
	private ToolBar toolbar;
	private JSplitPane splitPane;
	private JTabbedPane tabbedPane;
	private JFileChooser fileChooser;
	private List<FormEvent> dbForm;
	private String username;
	private Controller controller;

	public MainFrame(String title, String username, Controller controller) {
		this.controller = controller;
		this.username = username;
		setView();
		addComponent();
		setJMenuBar(createMenu());
		dbForm = new ArrayList<FormEvent>();
		tablePanel.setData(dbForm);

	}

	private void setView() {
		this.setSize(940, 500);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setLocation(400, 200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					controller.disconnect();
				} catch (SQLException e1) {
					showStackTraceMessage(e1);
				}
			}
		});
	}

	private void addComponent() {
		textPanel = new TextPanel();
		tablePanel = new TablePanel();
		formPanel = new FormPanel();
		toolbar = new ToolBar(username);
		this.getContentPane().add(toolbar, BorderLayout.NORTH);
		tabbedPane = new JTabbedPane();
		// tabbedPane.add("Text Area", textPanel);
		tabbedPane.add("Person DB", tablePanel);
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel,
				tabbedPane);
		splitPane.setOneTouchExpandable(true);
		this.getContentPane().add(splitPane, BorderLayout.CENTER);
		createMenu();
		formPanel.setformListener(new FormListener() {
			@Override
			public void formEventOccured(FormEvent e) {
				dbForm.add(e);
				textPanel.setTextArea(e);
				tablePanel.refresh();
				controller.addPerson(e);
			}
		});

		tablePanel.setPersonTableListener(new PersonTableListener() {
			@Override
			public void saveRow(int[] rows) {
				for (int i = 0; i < rows.length; i++) {
					FormEvent e = dbForm.get(rows[i]);
					try {
						controller.updatePerson(e);
					} catch (SQLException e1) {
						showStackTraceMessage(e1);
					}
				}
			}

			@Override
			public void refreshRow() {
				try {
					dbForm = controller.loadFromDB();
				} catch (SQLException e) {
					showStackTraceMessage(e);
				}
				tablePanel.setData(dbForm);
				tablePanel.refresh();
				if (dbForm.size() > 0) {
					int dbformSize = dbForm.size() - 1;
					FormEvent.count = dbForm.get(dbformSize).getID() + 1;
				}
			}

			@Override
			public void deleteRow(int row) {
				try {
					controller.deletePerson(row);
				} catch (SQLException e) {
					System.out.println("tgdbh");
				}
				dbForm.remove(row);
			}
		});
		toolbar.setToolbarListener(new ToolbarListener() {
			@Override
			public void saveEventOccured() {
				controller.checkEditedCells(dbForm);
				try {
					controller.saveToDB();
				} catch (SQLException e) {
					showStackTraceMessage(e);
				}
			}

			@Override
			public void refreshEventOccured() {

				try {
					dbForm = controller.loadFromDB();
				} catch (SQLException e) {
					showStackTraceMessage(e);
				}
				for (FormEvent e : dbForm) {
					textPanel.setTextArea(e);
				}
				tablePanel.setData(dbForm);
				tablePanel.refresh();
				if (dbForm.size() > 0) {
					int dbformSize = dbForm.size() - 1;
					FormEvent.count = dbForm.get(dbformSize).getID() + 1;
				}
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

		loadFileMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					dbForm.clear();
					FormEvent.count = 0;
					textPanel.textArea.setText("");
					File selectedFile = fileChooser.getSelectedFile();

					List<FormEvent> formEvents;
					try {
						formEvents = controller.loadFromFile(selectedFile);

						for (FormEvent e : formEvents) {
							textPanel.setTextArea(e);
							dbForm.add(e);

							tablePanel.refresh();
						}
					} catch (ClassNotFoundException | IOException e1) {
						showStackTraceMessage(e1);
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
						controller.SaveToFile(selectedFile);
					} catch (IOException e) {
						showStackTraceMessage(e);
					}
				}
			}
		});

		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int action = JOptionPane.showConfirmDialog(MainFrame.this,
						"Do You want to exit?", "Confirm Text",
						JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION)
					System.exit(0);
			}
		});

		return menuBar;
	}

	private void showStackTraceMessage(Exception e) {
		JOptionPane.showMessageDialog(null, e.getMessage(), "خطا",
				JOptionPane.ERROR_MESSAGE);
	}
}
