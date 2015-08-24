package ir.mahan.train.view;

import java.awt.BorderLayout;
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
	private JTable table;
	private JPopupMenu popupMenu;
	private JMenuItem saveItem;
	private JMenuItem deleteItem;
	private JMenuItem refreshItem;
	private PersonTableModel personTableModel;
	private PersonTableListener personTableListener;

	public void setPersonTableListener(PersonTableListener personTableListener) {
		this.personTableListener = personTableListener;
	}

	public TablePanel() {
		setTableView();
		initialize();
		addPopUpMenu();
		addMouseListenerToTable();
		addActionListenerToSaveItem();
		addActionListenerToDeleteItem();
		addActionListenerToRefreshItem();
	}

	private void setTableView() {
		setLayout(new BorderLayout());
		getPreferredSize().width = 500;
		setPreferredSize(getPreferredSize());
		personTableModel = new PersonTableModel();
		table = new JTable(personTableModel);
		add(new JScrollPane(table), BorderLayout.CENTER);
	}

	private void addMouseListenerToTable() {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (table.getSelectedRow() == -1)
					return;
				int[] rows = table.getSelectedRows();
				table.getSelectionModel().setSelectionInterval(rows[0],
						rows[rows.length - 1]);
				if (e.getButton() == MouseEvent.BUTTON3) {
					popupMenu.show(table, e.getX(), e.getY());
				}
			}
		});
	}

	private void addActionListenerToRefreshItem() {
		refreshItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (personTableListener != null) {
					personTableListener.refreshRow();
					personTableModel.fireTableDataChanged();
				}

			}

		});

	}

	private void addActionListenerToDeleteItem() {
		deleteItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if (personTableListener != null) {
					personTableListener.deleteRow(row);
					personTableModel.fireTableDataChanged();
				}
			}
		});

	}

	private void addActionListenerToSaveItem() {
		saveItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int[] rows = table.getSelectedRows();
				if (personTableListener != null) {
					personTableListener.saveRow(rows);
				}
			}
		});

	}

	private void initialize() {
		popupMenu = new JPopupMenu();
		saveItem = new JMenuItem("Save");
		deleteItem = new JMenuItem("delete");
		refreshItem = new JMenuItem("refresh");

	}

	private void addPopUpMenu() {
		popupMenu.add(saveItem);
		popupMenu.add(deleteItem);
		popupMenu.add(refreshItem);

	}

	public void setData(List<FormEvent> db) {
		personTableModel.setData(db);
	}

	public void refresh() {
		personTableModel.fireTableDataChanged();
	}

}
