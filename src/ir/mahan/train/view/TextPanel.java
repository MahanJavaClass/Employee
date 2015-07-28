package ir.mahan.train.view;

import ir.mahan.train.model.Role;
import ir.mahan.train.model.Gender;
import ir.mahan.train.model.User;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextPanel extends JPanel {

	JTextArea textArea;

	public TextPanel() {
		textArea = new JTextArea();
		setLayout(new BorderLayout());

		Dimension dim = getPreferredSize();
		dim.width = 400;
		setPreferredSize(dim);

		setBorder(BorderFactory.createTitledBorder("TextArea"));
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}
	public void setTextArea(User user) {
		String name = user.getName();
		String family = user.getFamily();
		Role role = user.getRole();
		String age = user.getAge();
		String city = user.getCity();
		String favSport = user.getFavoriteSport();
		Gender gender = user.getGender();
		String salary = user.getSalary();
		String sep = "::";

		String result = name + sep + family + sep + role + sep + age + sep
				+ gender + sep + city + sep + favSport;
		if (user.getIsEmp()) {
			String isEmployee;
			isEmployee = sep + salary;
			result += isEmployee;
			this.textArea.append(result + "\n");
		} else
			this.textArea.append(result + "\n");

	}

}
