package ir.mahan.train.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBase {
	private List<Person> people;

	public DataBase() {
		people = new ArrayList<>();
		
	}

	private void addPerson(Person p) {
		people.add(p);
	}

	private void deletePerson(int index) {
		people.remove(index);
	}

	public void saveToFile(File file) throws IOException {

		FileOutputStream fileStream = new FileOutputStream(file);
		ObjectOutputStream os = new ObjectOutputStream(fileStream);
		Person[] persons = people.toArray(new Person[people.size()]);
		os.writeObject(persons);
		os.close();

	}

	public void loadFromFile(File file) throws IOException {
		// TODO Auto-generated method stub

		FileInputStream fileStream = new FileInputStream(file);
		ObjectInputStream os = new ObjectInputStream(fileStream);
		try {
			Person[] persons = (Person[]) os.readObject();
			people.clear();
			people.addAll(Arrays.asList(persons));

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		people.toArray(new Person[people.size()]);
		os.close();

	}
}
