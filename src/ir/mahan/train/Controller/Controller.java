package ir.mahan.train.Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

	public void saveToDB() {
		try {
			db.save();
		} catch (Exception e) {
			System.out.println("No connection found");
		}
	}

	public List<FormEvent> load() throws Exception {
		List<Person> people = db.load();
		List<FormEvent> peopleLoaded = new ArrayList<FormEvent>();
		for (Person p : people) {
			FormEvent e = convertPersonToFormEvent(p);
			peopleLoaded.add(e);
		}
		return peopleLoaded;
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

	public void addPerson(FormEvent e) throws IOException {

		person = ConvertFormEventToPerson(e);
		db.addPerson(person);
	}

	private FormEvent convertPersonToFormEvent(Person p) {
		String name = p.getName();
		String family = p.getFamily();
		Role role = p.getRole();
		String age = p.getAge();
		String city = p.getCity();
		String favSport = p.getFavoriteSport();
		Gender gender = p.getGender();
		String salary = p.getSalary();
		Boolean isEmp = p.getIsEmp();
		FormEvent e = new FormEvent(name, family, gender, age, role, city,
				favSport, isEmp, salary);
		return e;
	}

	private Person ConvertFormEventToPerson(FormEvent e) {

		int ID = e.getID();
		String name = e.getName();
		String family = e.getFamily();
		Role role = e.getRole();
		String age = e.getAge();
		String city = e.getCity();
		String favSport = e.getFavoriteSport();
		Gender gender = e.getGender();
		String salary = e.getSalary();
		Boolean isEmp = e.getIsEmp();
		Person person = new Person(ID, name, family, gender, age, role, city,
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

	public Boolean authenticate(String userName, String passString) {
		Boolean success = false;
		try {
			success = db.authenticate(userName, passString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (success)
			return true;
		else
			return false;
	}

}
