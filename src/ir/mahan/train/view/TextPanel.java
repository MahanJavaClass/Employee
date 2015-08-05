package ir.mahan.train.view;

import ir.mahan.train.model.Role;
import ir.mahan.train.model.Gender;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JTextArea textArea;

	public TextPanel() {
		textArea = new JTextArea();
		setLayout(new BorderLayout());

		Dimension dim = getPreferredSize();
		dim.width = 400;
		setPreferredSize(dim);

		setBorder(BorderFactory.createTitledBorder(""));
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		setFont(new Font(Font.SERIF, Font.PLAIN, 15));
	}

	public void setTextArea(FormEvent e) {

		String name = e.getName();
		String family = e.getFamily();
		Role role = e.getRole();
		String age = e.getAge();
		String city = e.getCity();
		String favSport = e.getFavoriteSport();
		Gender gender = e.getGender();
		String salary = e.getSalary();
		Boolean isEmp = e.getIsEmp();
		String sep = "::";

		String result = name + sep + family + sep + role + sep + age + sep
				+ gender + sep + city + sep + favSport;
		if (isEmp) {
			String isEmployee;
			isEmployee = sep + salary;
			result += isEmployee;
			this.textArea.append(result + "\n");
		} else
			this.textArea.append(result + "\n");

	}

}
