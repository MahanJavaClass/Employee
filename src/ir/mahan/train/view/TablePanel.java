package ir.mahan.train.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JTable table;
	private PersonTableModel personTableModel;
	private PersonTableListener personTableListener;

	public void setPersonTableListener(PersonTableListener personTableListener) {
		this.personTableListener = personTableListener;
	}

	JPopupMenu popupMenu;

	public TablePanel() {
		setLayout(new BorderLayout());
		Dimension dim = getPreferredSize();
		dim.width = 500;
		setPreferredSize(dim);
		setMinimumSize(dim);

		personTableModel = new PersonTableModel();
		table = new JTable(personTableModel);
		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);

		JMenuItem saveItem = new JMenuItem("Save");
		JMenuItem deleteItem = new JMenuItem("delete");
		JMenuItem refreshItem = new JMenuItem("refresh");

		popupMenu = new JPopupMenu();
		popupMenu.add(saveItem);
		popupMenu.add(deleteItem);
		popupMenu.add(refreshItem);

		saveItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if (personTableListener != null) {
					personTableListener.rowSave(row);
					personTableModel.fireTableDataChanged();
				}

			}

		});
		deleteItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if (personTableListener != null) {
					personTableListener.rowDeleted(row);
					personTableModel.fireTableDataChanged();
				}

			}

		});
		refreshItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if (personTableListener != null) {
					personTableListener.rowRefresh(row);
					personTableModel.fireTableDataChanged();
				}

			}

		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				table.getSelectionModel().setSelectionInterval(row, row);
				if (e.getButton() == MouseEvent.BUTTON3) {
					popupMenu.show(table, e.getX(), e.getY());
				}
			}
		});

	}

	public void setData(List<FormEvent> db) {
		personTableModel.setData(db);
	}

	public void refresh() {
		personTableModel.fireTableDataChanged();
	}

}
