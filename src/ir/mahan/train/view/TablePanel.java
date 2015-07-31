package ir.mahan.train.view;

import ir.mahan.train.model.Role;
import ir.mahan.train.model.Gender;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class TablePanel extends JPanel {


	JTable table;
	Object[][] data;

	public TablePanel() {
		setLayout(new BorderLayout());

		Dimension dim = getPreferredSize();
		dim.width = 500;
		setPreferredSize(dim);

		setBorder(BorderFactory.createTitledBorder("User Table"));
		add(new JScrollPane(table), BorderLayout.CENTER);
		String[] columnNames = { "First Name", "Last Name", "Role", "Age",
				 "Gender","City", "Favorite Sports", "Salary" };
		Object[][] data = {};

		 table = new JTable(new DefaultTableModel(data, columnNames));
		 JScrollPane scrollPane = new JScrollPane(table);
		 table.setFillsViewportHeight(true);
		 add(scrollPane);
	}

	public void setTable(FormEvent e) {
		
		String name = e.getName();
		String family = e.getFamily();
		Role role = e.getRole();
		String age = e.getAge();
		String city = e.getCity();
		String favSport = e.getFavoriteSport();
		Gender gender = e.getGender();
		String salary = e.getSalary();
		Boolean isEmp = e.getIsEmp();	

		Object[][] data = {{ name, family, role, age, city, favSport, gender,
				salary }};
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[]{name, family, role, age, gender , city, favSport,
				salary});
		
		
	}

}
