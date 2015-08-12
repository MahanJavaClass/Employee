package ir.mahan.train.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton saveBtn;
	private JButton refBtn;
	JLabel welcomeLbl;
	String username;
	private ToolbarListener toolbarListener;

	public void setToolbarListener(ToolbarListener toolbarListener) {
		this.toolbarListener = toolbarListener;
	}

	public ToolBar(String username) {

		Dimension dim = new Dimension();
		dim.setSize(650, this.HEIGHT);

		this.username = username;
		setBorder(BorderFactory.createEtchedBorder());
		setFloatable(false);

		saveBtn = new JButton();
		saveBtn.setIcon(Utils.createIcon("/images/Save16.gif"));
		saveBtn.setToolTipText("Save To DataBase");

		refBtn = new JButton();
		refBtn.setIcon(Utils.createIcon("/images/Refresh16.gif"));
		refBtn.setToolTipText("Refresh Data");

		welcomeLbl = new JLabel("Dear " + username + " welcome to my page");
		add(saveBtn);
		addSeparator();
		add(refBtn);
		addSeparator(dim);
		add(welcomeLbl);

		saveBtn.addActionListener(this);
		refBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == saveBtn) {

			toolbarListener.saveEventOccured();
		} else {
			try {
				toolbarListener.refreshEventOccured();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}
