package ir.mahan.train.model;

import java.io.Serializable;

public class User implements Serializable{
	String name;
	String family;
	Gender gender;
	String city;
	Role role;
	String age;
	String favoriteSport;
	String salary;
	Boolean isEmp;

	public User(String name, String family, Role role, String age,
			Gender gender, String city, String favSport, Boolean isEmp,
			String salary) {
		super();
		this.name = name;
		this.family = family;
		this.gender = gender;
		this.city = city;
		this.role = role;
		this.age = age;
		this.favoriteSport = favSport;
		this.isEmp = isEmp;
		this.salary = salary;
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
