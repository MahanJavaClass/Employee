package ir.mahan.train.model;

import java.io.Serializable;

public class Person implements Serializable{

	private static final long serialVersionUID = 1L;
	int ID;
	String name;
	String family;
	Gender gender;
	String city;
	Role role;
	String age;
	String favoriteSport;
	String salary;
	Boolean isEmp;

	public int getID(){
		return ID;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person(int ID , String name, String family, Role role, String city,
			Gender gender, String age, String favoriteSport,  Boolean isEmp ,String salary
			) {
		super();
		this.ID = ID;
		this.name = name;
		this.family = family;
		this.gender = gender;
		this.city = city;
		this.role = role;
		this.age = age;
		this.favoriteSport = favoriteSport;
		this.salary = salary;
		this.isEmp = isEmp;
	
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
