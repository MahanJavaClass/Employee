package ir.mahan.train.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JTable table;
	private PersonTableModel personTableModel;

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
	}

	public void setData(List<FormEvent> db) {
		personTableModel.setData(db);
	}

	public void refresh() {
		personTableModel.fireTableDataChanged();
	}

}
