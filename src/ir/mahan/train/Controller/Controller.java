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
	private DataBase db;
	private Person person;

	public Controller() {
		db = new DataBase();
	}

	public void addPerson(FormEvent e) {

		person = ConvertFormEventToPerson(e);
		db.addPerson(person);
	}

	public void deletePerson(int row) throws SQLException {
		db.deletePerson(row);
	}

	public void updatePerson(FormEvent e) throws SQLException {
		Person p = ConvertFormEventToPerson(e);
		db.updatePerson(p);
	}

	// / ---------------------- DataBase -----------------------

	public void connect() throws ClassNotFoundException, SQLException,
			IOException {
		db.connect();
	}

	public void disconnect() throws SQLException {
		db.disConnect();
	}

	public void saveToDB() throws SQLException {
		db.setDB();
	}

	public List<FormEvent> loadFromDB() throws SQLException {
		List<Person> people = db.loadFromDB();
		List<FormEvent> peopleLoaded = new ArrayList<FormEvent>();
		for (Person p : people) {
			FormEvent e = convertPersonToFormEvent(p);
			peopleLoaded.add(e);
		}
		return peopleLoaded;
	}

	// / ---------------------- File --------------------------

	public void SaveToFile(File file) throws IOException {

		db.saveToFile(file);
	}

	public List<FormEvent> loadFromFile(File file)
			throws ClassNotFoundException, IOException {

		List<Person> people = db.loadFromFile(file);
		List<FormEvent> formEvents = new ArrayList<>();
		for (Person person : people) {
			formEvents.add(convertPersonToFormEvent(person));
		}
		return formEvents;
	}

	// / ---------------------- Convert -----------------------

	FormEvent convertPersonToFormEvent(Person p) {
		FormEvent e = new FormEvent(p.getID(), p.getName(), p.getFamily(),
				p.getGender(), p.getAge(), p.getRole(), p.getCity(),
				p.getFavoriteSport(), p.getIsEmp(), p.getSalary());
		return e;
	}

	public Person ConvertFormEventToPerson(FormEvent e) {
		Person person = new Person(e.getID(), e.getName(), e.getFamily(),
				e.getGender(), e.getAge(), e.getRole(), e.getCity(),
				e.getFavoriteSport(), e.getIsEmp(), e.getSalary());
		return person;

	}

	// / ---------------------- authenticate -----------------------

	public Boolean authenticate(String userName, String passString)
			throws SQLException {
		Boolean success = false;
		success = db.authenticate(userName, passString);
		if (success)
			return true;
		else
			return false;
	}

	public void checkEditedCells(List<FormEvent> formEvents) {
		for (int i = 0; i < formEvents.size(); i++) {
			Person p = ConvertFormEventToPerson(formEvents.get(i));
			db.getPeopleList().set(i, p);
		}
	}

}
