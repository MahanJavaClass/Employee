package ir.mahan.train.view;

import ir.mahan.train.model.Ix;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MenuPanel extends JPanel implements WindowListener {

	JMenuBar menuBar;
	JMenu fileMenu;
	JMenuItem saveMenuItem;
	JMenuItem loadFileMenuItem;
	JMenuItem exitMenuItem;
	private Ix ix;
	MainFrame mf;

	public void setIx(Ix ix) {
		this.ix = ix;
	}

	public MenuPanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		saveMenuItem = new JMenuItem("Save to File");
		loadFileMenuItem = new JMenuItem("Load from File");
		exitMenuItem = new JMenuItem("Exit");
		fileMenu.add(saveMenuItem);
		fileMenu.add(loadFileMenuItem);
		fileMenu.add(exitMenuItem);
		add(menuBar);
		
	}


	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
