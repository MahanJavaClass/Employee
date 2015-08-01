package ir.mahan.train.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PersonTableModel extends AbstractTableModel {

	private List<FormEvent> db;
	String[] columnNames = { "ID", "First Name", "Last Name", "Role", "Age",
			"Gender", "City", "Favorite Sports", "Salary" };

	public void setData(List<FormEvent> db) {
		this.db = db;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return db.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		FormEvent person = db.get(row);
		switch (col) {
		case 0:
			return person.ID;

		case 1:
			return person.getName();

		case 2:
			return person.getFamily();
		case 3:
			return person.getRole();

		case 4:
			return person.getAge();

		}
		return null;

	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

}
