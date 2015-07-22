package ir.mahan.train.view;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel implements ActionListener {

	JTextField nameText;
	JTextField familyText;
	JLabel nameLabel;
	JLabel familyLabel;

	JLabel roleLabel;
	JComboBox<Category> role;

	JRadioButton age01;
	JRadioButton age02;
	JRadioButton age03;
	JLabel ageLabel;

	JComboBox<Gender> gender;
	JLabel genderLabel;

	JLabel cityLabel;
	JCheckBox city01;
	JCheckBox city02;

	JLabel sportLabel;
	JList favoriteSport;

	JCheckBox isEmployee;

	JLabel sallaryLabel;
	JTextField sallary;

	JButton submitBtn;

	private IstringListener istringListener;

	public FormPanel() {
		setLayout(new GridBagLayout());
		setFormBorder();
		initializeFormComponents();
		layoutComponent();
		groupButton();
		submitBtn.addActionListener(this);
	}

	private void initializeFormComponents() {
		nameLabel = new JLabel("First Name:");
		familyLabel = new JLabel("Last Name:");
		nameText = new JTextField();
		familyText = new JTextField();

		roleLabel = new JLabel("Role:");
		role = new JComboBox<Category>();
		role.setModel(new DefaultComboBoxModel(Category.values()));

		ageLabel = new JLabel("age:");
		age01 = new JRadioButton("18");
		age02 = new JRadioButton("20-30");
		age03 = new JRadioButton(">30");

		genderLabel = new JLabel("Gender:");
		gender = new JComboBox<Gender>();
		gender.setModel(new DefaultComboBoxModel(Gender.values()));

		cityLabel = new JLabel("City:");
		city01 = new JCheckBox("Tehran");
		city02 = new JCheckBox("Kerman");

		sportLabel = new JLabel("Favorite Sport:");
		String listData[] = { "Football", "Volleyball", "Other" };
		favoriteSport = new JList(listData);
		favoriteSport.setSelectedIndex(1);

		isEmployee = new JCheckBox("Is Employee");
		isEmployee.addActionListener(this);

		sallaryLabel = new JLabel("Sallary");
		sallary = new JTextField();
		sallary.setEditable(false);
		submitBtn = new JButton("submit");
	}

	private void setFormBorder() {
		Border innerBorder = BorderFactory.createTitledBorder("User Panel");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
	}

	private void layoutComponent() {
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.LINE_START;
		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridy = 0;
		gc.gridx = 0;

		add(nameLabel, gc);

		gc.gridy = 0;
		gc.gridx = 1;

		gc.fill = GridBagConstraints.HORIZONTAL;
		add(nameText, gc);

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridy = 1;
		gc.gridx = 0;

		add(familyLabel, gc);

		gc.gridy = 1;
		gc.gridx = 1;

		gc.fill = GridBagConstraints.HORIZONTAL;
		add(familyText, gc);

		gc.gridy = 2;
		gc.gridx = 0;

		add(roleLabel, gc);

		gc.gridy = 2;
		gc.gridx = 1;

		add(role, gc);

		gc.gridy = 3;
		gc.gridx = 0;

		add(ageLabel, gc);

		gc.gridy = 3;
		gc.gridx = 1;

		add(age01, gc);

		gc.gridy = 3;
		gc.gridx = 2;

		add(age02, gc);

		gc.gridy = 3;
		gc.gridx = 3;

		add(age03, gc);

		gc.gridy = 4;
		gc.gridx = 0;

		add(genderLabel, gc);

		gc.gridy = 4;
		gc.gridx = 1;

		add(gender, gc);

		gc.gridy = 5;
		gc.gridx = 0;

		add(cityLabel, gc);

		gc.gridy = 5;
		gc.gridx = 1;

		add(city01, gc);

		gc.gridy = 5;
		gc.gridx = 2;

		add(city02, gc);

		gc.gridy = 6;
		gc.gridx = 0;
		add(sportLabel, gc);

		gc.gridy = 6;
		gc.gridx = 1;
		add(favoriteSport, gc);

		gc.gridy = 7;
		gc.gridx = 0;
		add(isEmployee, gc);

		gc.gridy = 8;
		gc.gridx = 0;
		add(sallaryLabel, gc);

		gc.gridy = 8;
		gc.gridx = 1;
		add(sallary, gc);

		gc.gridy = 9;
		gc.gridx = 0;
		add(submitBtn, gc);

	}

	public void setIstringListener(IstringListener istringListener) {
		this.istringListener = istringListener;
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(submitBtn))
			submitAction();
		if (event.getSource().equals(isEmployee))
			isEmployeeAction();

	}

	private void isEmployeeAction() {

		if (isEmployee.isSelected())
			sallary.setEditable(true);
		else
			sallary.setEditable(false);
	}

	private void submitAction() {
		String Result = "";
		if (istringListener != null) {
			if ((nameText.getText().equals("") || familyText.getText().equals(
					"")) == false) {
				new User(nameText.getText(), familyText.getText(),
						(Gender) gender.getSelectedItem(), getSelectedCities(),
						(Category) role.getSelectedItem(), getSelectedAge(),
						favoriteSport.getSelectedValue().toString());

				Result = nameText.getText() + "::" + familyText.getText()
						+ "::" + getSelectedCategory() + "::"
						+ getSelectedAge() + "::" + gender.getSelectedItem()
						+ "::" + getSelectedCities() + "::"
						+ favoriteSport.getSelectedValue();
				istringListener.stringEmmited(Result);
			} else
				JOptionPane.showMessageDialog(null, "لطفا دیتا وارد کنید",
						"خطا", JOptionPane.WARNING_MESSAGE);
		}

	}

	private void groupButton() {
		ButtonGroup ages = new ButtonGroup();
		ages.add(age01);
		ages.add(age02);
		ages.add(age03);
	}

	public String getSelectedCities() {
		String cities = "";
		if (city01.isSelected())
			cities = city01.getText();
		if (city02.isSelected())
			cities = cities + "," + city02.getText();
		return cities;
	}

	String getSelectedAge() {
		String age = "";
		if (age01.isSelected())
			age = age01.getText();
		else if (age02.isSelected())
			age = age02.getText();
		else if (age03.isSelected())
			age = age03.getText();
		return age;
	}

	String getSelectedCategory() {
		String category = "";
		category = role.getSelectedItem().toString();
		return category;
	}

}
