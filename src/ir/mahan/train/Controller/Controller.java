package ir.mahan.train.Controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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

	public Controller() {
		db = new DataBase();
	}

	public void addPerson(FormEvent e) throws IOException {
		
		person = ConvertFormEventToPerson(e);
		db.addPerson(person);
	}
	public void deletePerson(int row) throws Exception {
		try {
			db.deletePerson(row);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/// ---------------------- DataBase -----------------------
	public void saveToDB() {
		try {
			db.setDB();
		} catch (Exception e) {
			System.out.println("No connection found");
		}
	}

	public List<FormEvent> loadFromDB() throws Exception {
		List<Person> people = db.loadFromDB();
		List<FormEvent> peopleLoaded = new ArrayList<FormEvent>();
		for (Person p : people) {
			FormEvent e = convertPersonToFormEvent(p);
			peopleLoaded.add(e);
		}
		return peopleLoaded;
	}

	/// ---------------------- File --------------------------
	
	public void SaveToFile(File file) throws IOException {
		
		db.saveToFile(file);
	}

	public List<FormEvent> loadFromFile(File file) throws IOException {

		List<Person> people = db.loadFromFile(file);
		List<FormEvent> formEvents = new ArrayList<>();
		for (Person person : people) {
			formEvents.add(convertPersonToFormEvent(person));
		}
		return formEvents;
	}

	/// ---------------------- Convert -----------------------
	
	private FormEvent convertPersonToFormEvent(Person p) {
		int ID = p.getID();
		String name = p.getName();
		String family = p.getFamily();
		Role role = p.getRole();
		String age = p.getAge();
		String city = p.getCity();
		String favSport = p.getFavoriteSport();
		Gender gender = p.getGender();
		String salary = p.getSalary();
		Boolean isEmp = p.getIsEmp();
		FormEvent e = new FormEvent(ID, name, family, gender, age, role, city,
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
	/// ---------------------- authenticate -----------------------
	
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
