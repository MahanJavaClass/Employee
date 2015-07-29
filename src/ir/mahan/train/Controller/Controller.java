package ir.mahan.train.Controller;

import ir.mahan.train.model.DataBase;
import ir.mahan.train.model.Gender;
import ir.mahan.train.model.Person;
import ir.mahan.train.model.Role;
import ir.mahan.train.view.FormEvent;

public class Controller {
	DataBase db;
	public Controller(){
		
	}
public void addPerson(FormEvent user){
	Person person=ConvertFormEventToPerson(user);
}
private Person ConvertFormEventToPerson(FormEvent user) {
	
	String name = user.getName();
	String family = user.getFamily();
	Role role = user.getRole();
	String age = user.getAge();
	String city = user.getCity();
	String favSport = user.getFavoriteSport();
	Gender gender = user.getGender();
	String salary = user.getSalary();
	Person person=new Person(name, family, gender, city, role, age, favSport, salary);
	return person;
}
}
