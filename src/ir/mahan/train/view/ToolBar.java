package ir.mahan.train.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import ir.mahan.train.model.ToolbarListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar implements ActionListener {
	private JButton saveBtn;
	private JButton refBtn;
	private ToolbarListener toolbarListener;
	
	public void setToolbarListener(ToolbarListener toolbarListener){
		this.toolbarListener=toolbarListener;
	}
	
	public ToolBar() {
		setBorder(BorderFactory.createEtchedBorder());
		setFloatable(false);
		
		saveBtn = new JButton();
		saveBtn.setIcon(Utils.createIcon("/images/Save16.gif"));
		saveBtn.setToolTipText("Save To DataBase");
		
		refBtn = new JButton();
		refBtn.setIcon(Utils.createIcon("/images/Refresh16.gif"));
		refBtn.setToolTipText("Refresh Data");
		
		add(saveBtn);
		addSeparator();
		add(refBtn);
		
		saveBtn.addActionListener(this);
		refBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()== saveBtn){
			
			toolbarListener.saveEventOccured();
		}
		else {
			try {
				toolbarListener.refreshEventOccured();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}
