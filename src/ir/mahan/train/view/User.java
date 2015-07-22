package ir.mahan.train.view;
public class User {
	String name;
	String family;
	Gender gender;
	String city;
	Category role;
	String age;
	String favoriteSport;

	public User(String name, String family, Gender gender, String city,
			Category role, String age, String favoriteSport) {
		super();
		this.name = name;
		this.family = family;
		this.gender = gender;
		this.city = city;
		this.role = role;
		this.age = age;
		this.favoriteSport = favoriteSport;
	}

}
