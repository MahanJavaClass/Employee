package ir.mahan.train.view;

import ir.mahan.train.model.Role;
import ir.mahan.train.model.Gender;
import ir.mahan.train.model.IuserListener;
import ir.mahan.train.model.Sport;
import ir.mahan.train.model.User;
import ir.mahan.train.model.Validate;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel implements ActionListener {

	JLabel nameLbl;
	JLabel familyLbl;
	JLabel roleLbl;
	JLabel ageLbl;
	JLabel cityLbl;
	JLabel genderLbl;
	JLabel sportLbl;
	JLabel salaryLbl;

	JTextField nameTxt;
	JTextField familyTxt;
	JTextField salaryTxt;

	JRadioButton ageOption1;
	JRadioButton ageOption2;
	JRadioButton ageOption3;

	JComboBox<Role> roleCB;
	JComboBox<Gender> genderCB;

	JList favoriteSportList;

	JCheckBox city01;
	JCheckBox city02;
	JCheckBox isEmp;

	JButton submitBtn;

	private IuserListener iuser;

	public void setIuser(IuserListener iuser) {
		this.iuser = iuser;
	}

	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 320;
		setPreferredSize(dim);
		setLayout(new GridBagLayout());
		setFormBorder();
		initializeFormComponents();
		layoutComponent();
		groupButton();
		submitBtn.addActionListener(this);
	}

	private void initializeFormComponents() {
		nameLbl = new JLabel("Name:");
		familyLbl = new JLabel("Family:");
		nameTxt = new JTextField();
		familyTxt = new JTextField();

		roleLbl = new JLabel("Role:");
		roleCB = new JComboBox<Role>();
		roleCB.setModel(new DefaultComboBoxModel(Role.values()));

		ageLbl = new JLabel("age:");
		ageOption1 = new JRadioButton("18");
		ageOption2 = new JRadioButton("20-30");
		ageOption3 = new JRadioButton(">30");

		genderLbl = new JLabel("Gender:");
		genderCB = new JComboBox<Gender>();
		genderCB.setModel(new DefaultComboBoxModel(Gender.values()));

		cityLbl = new JLabel("City:");
		city01 = new JCheckBox("Tehran");
		city02 = new JCheckBox("Kerman");

		sportLbl = new JLabel("Favorite Sport:");
		favoriteSportList = new JList(Sport.values());
		favoriteSportList.setSelectedIndex(0);

		isEmp = new JCheckBox("Is Employee");
		isEmp.addActionListener(this);

		salaryLbl = new JLabel("Salary");
		salaryTxt = new JTextField();
		salaryTxt.setEditable(false);
		submitBtn = new JButton("submit");
		submitBtn.setBackground(Color.decode("#16a126"));
		submitBtn.setForeground(Color.white);
	}

	private void setFormBorder() {
		Border innerBorder = BorderFactory.createTitledBorder("User Panel");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
	}

	private void layoutComponent() {
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridy = 0;
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(nameLbl, gc);

		gc.gridy = 0;
		gc.gridx = 1;
		gc.gridwidth = 2;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(nameTxt, gc);

		gc.gridy = 1;
		gc.gridx = 0;
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(familyLbl, gc);

		gc.gridy = 1;
		gc.gridx = 1;
		gc.gridwidth = 2;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(familyTxt, gc);

		gc.gridy = 2;
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(roleLbl, gc);

		gc.gridy = 2;
		gc.gridx = 1;
		gc.gridwidth = 2;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(roleCB, gc);

		gc.gridy = 3;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		add(ageLbl, gc);

		gc.gridy = 3;
		gc.gridx = 1;
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(ageOption1, gc);

		gc.gridy = 3;
		gc.gridx = 2;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(ageOption2, gc);

		gc.gridy = 3;
		gc.gridx = 3;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(ageOption3, gc);

		gc.gridy = 4;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		add(genderLbl, gc);

		gc.gridy = 4;
		gc.gridx = 1;
		gc.gridwidth = 2;
		gc.anchor = GridBagConstraints.LINE_START;
		add(genderCB, gc);

		gc.gridy = 5;
		gc.gridx = 0;
		gc.gridwidth = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(cityLbl, gc);

		gc.gridy = 5;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(city01, gc);

		gc.gridy = 5;
		gc.gridx = 2;
		gc.anchor = GridBagConstraints.LINE_START;
		add(city02, gc);

		gc.gridy = 6;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		add(sportLbl, gc);

		gc.gridy = 6;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LAST_LINE_START;
		add(favoriteSportList, gc);

		gc.gridy = 7;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(isEmp, gc);

		gc.gridy = 8;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		add(salaryLbl, gc);

		gc.gridy = 8;
		gc.gridx = 1;
		gc.gridwidth = 2;
		gc.anchor = GridBagConstraints.LINE_START;
		add(salaryTxt, gc);

		gc.gridy = 9;
		gc.gridx = 1;
		gc.gridwidth = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(submitBtn, gc);

	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(submitBtn))
			submitAction();
		if (event.getSource().equals(isEmp))
			isEmployeeAction();

	}

	private void isEmployeeAction() {

		if (isEmp.isSelected())
			salaryTxt.setEditable(true);
		else {
			salaryTxt.setText("");
			salaryTxt.setEditable(false);
		}
	}

	private void submitAction() {
		User user;
		Validate validate;
		String error = "";
		validate = new Validate();
		if (!validate.stringValidate(nameTxt.getText())) {
			error += "Name invalid!\n";
		}
		if (!validate.stringValidate(familyTxt.getText())) {
			error += "Family invalid!\n";
		}
		if (!validate.numberValidate(salaryTxt.getText()) && isEmp.isSelected()) {
			error += "Salary invalid!\n";
		}

		if (error != "")
			JOptionPane.showMessageDialog(null, error, "خطا",
					JOptionPane.ERROR_MESSAGE);
		else {
			user = new User(nameTxt.getText(), familyTxt.getText(),
					(Role) roleCB.getSelectedItem(), getSelectedCities(),
					(Gender) genderCB.getSelectedItem(), getSelectedAge(),
					favoriteSportList.getSelectedValue().toString(),
					isEmp.isSelected(), salaryTxt.getText());
			iuser.userEmitted(user);
		}

	}

	private void groupButton() {
		ButtonGroup ages = new ButtonGroup();
		ages.add(ageOption1);
		ages.add(ageOption2);
		ages.add(ageOption3);
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
		if (ageOption1.isSelected())
			age = ageOption1.getText();
		else if (ageOption2.isSelected())
			age = ageOption2.getText();
		else if (ageOption3.isSelected())
			age = ageOption3.getText();
		return age;
	}

	String getSelectedCategory() {
		String category = "";
		category = roleCB.getSelectedItem().toString();
		return category;
	}

}
