package ir.mahan.train.Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import ir.mahan.train.model.DataBase;
import ir.mahan.train.model.Gender;
import ir.mahan.train.model.Person;
import ir.mahan.train.model.Role;
import ir.mahan.train.view.FormEvent;

public class Controller {
	DataBase db;
	Person person;
	File selectedFile;
	String name;
	String family;
	Gender gender;
	String city;
	Role role;
	String age;
	String favoriteSport;
	String salary;
	Boolean isEmp;

	public Controller() {
		db = new DataBase();
	}

	public void addPerson(FormEvent e) throws IOException {

		person = ConvertFormEventToPerson(e);
		db.addPerson(person);
	}

	public void SavePerson(File file) throws IOException {

		db.saveToFile(file);
	}

	public List<FormEvent> loadPeople(File file) throws IOException {

		List<Person> people = db.loadFromFile(file);
		List<FormEvent> formEvents = new ArrayList<>();
		for (Person person : people) {
			formEvents.add(convertPersonToFormEvent(person));
		}
		return formEvents;
	}

	private FormEvent convertPersonToFormEvent(Person p) {
		// TODO Auto-generated method stub
		String name = p.getName();
		String family = p.getFamily();
		Role role = p.getRole();
		String age = p.getAge();
		String city = p.getCity();
		String favSport = p.getFavoriteSport();
		Gender gender = p.getGender();
		String salary = p.getSalary();
		Boolean isEmp = p.getIsEmp();
		FormEvent e = new FormEvent(name, family, role, age, gender, city,
				favSport, isEmp, salary);

		return e;
	}

	private Person ConvertFormEventToPerson(FormEvent e) {

		String name = e.getName();
		String family = e.getFamily();
		Role role = e.getRole();
		String age = e.getAge();
		String city = e.getCity();
		String favSport = e.getFavoriteSport();
		Gender gender = e.getGender();
		String salary = e.getSalary();
		Boolean isEmp = e.getIsEmp();
		Person person = new Person(name, family, role, city, gender, age,
				favSport, isEmp, salary);
		return person;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getFavoriteSport() {
		return favoriteSport;
	}

	public void setFavoriteSport(String favoriteSport) {
		this.favoriteSport = favoriteSport;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public Boolean getIsEmp() {
		return isEmp;
	}

	public void setIsEmp(Boolean isEmp) {
		this.isEmp = isEmp;
	}

}
