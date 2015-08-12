package ir.mahan.train.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBase {

	private List<Person> people;
	Connection con;
	Statement statement = null;

	public DataBase() {
		people = new ArrayList<>();
	}

	public List<Person> getPeopleList() {
		return people;
	}

	public void addPerson(Person p) {
		people.add(p);
	}

	@SuppressWarnings("unused")
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

	public List<Person> loadFromFile(File file) throws IOException {

		FileInputStream fileStream = new FileInputStream(file);
		ObjectInputStream os = new ObjectInputStream(fileStream);
		try {
			Person[] persons = (Person[]) os.readObject();
			people.clear();
			people.addAll(Arrays.asList(persons));

		} catch (ClassNotFoundException e) {
			System.out.println("File Not Found");
		}
		people.toArray(new Person[people.size()]);
		os.close();
		return people;

	}

	public void save() throws Exception {

		List<Person> people = getPeopleList();
		connect();

		for (Person p : people) {

			int bit;
			if (p.getIsEmp())
				bit = 1;
			else {
				bit = 0;
			}

			String query = "INSERT INTO G1.Person Values (" + p.getID() + ",'"
					+ p.getName() + "','" + p.getFamily() + "','"
					+ p.getGender() + "','" + p.getAge() + "','" + p.getRole()
					+ "','" + p.getCity() + "','" + p.getFavoriteSport() + "',"
					+ bit + ",'" + p.getSalary() + "')";

			statement = con.createStatement();
			statement.executeUpdate(query);

		}
		disConnect();
	}

	public List<Person> load() throws Exception {

		connect();
		ArrayList<Person> persons = new ArrayList<Person>();
		String query = "SELECT * FROM G1.Person ";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		while (rs.next()) {

			int ID = rs.getInt("ID");
			String name = rs.getString("FirstName");
			String family = rs.getString("LastName");
			String age = rs.getString("Age");
			String city = rs.getString("City");
			String gender = rs.getString("Gender");
			String role = rs.getString("Category");
			String favSport = rs.getString("Sport");
			Boolean isEmp = rs.getBoolean("IsEmployee");
			String salary = rs.getString("Salary");

			Role roleE;
			Gender genderE;

			if (gender == "Female")
				genderE = Gender.Female;
			else
				genderE = Gender.Male;

			if (role == "staff")
				roleE = Role.staff;
			else if (role == "student")
				roleE = Role.student;
			else
				roleE = Role.teacher;

			Person p = new Person(ID, name, family, roleE, city, genderE, age,
					favSport, isEmp, salary);
			persons.add(p);
		}
		disConnect();
		return persons;

	}

	public boolean authenticate(String userName, String password)
			throws Exception {
		connect();
		String query = "select username from [User] where username=? and password=?";
		PreparedStatement checkstm = con.prepareStatement(query);
		checkstm.setString(1, userName);
		checkstm.setString(2, password);
		ResultSet rs = checkstm.executeQuery();

		if (rs.next()) {
			disConnect();
			return true;
		} else {
			disConnect();
			return false;
		}

	}

	public void connect() throws Exception {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		} catch (ClassNotFoundException e) {
			throw new Exception("driver Not Found");
		}

		String connectionURL = "jdbc:sqlserver://swsql.mahanair.aero;user=sa;password=123;database=javaTraining";
		con = DriverManager.getConnection(connectionURL);
	}

	public void disConnect() throws Exception {
		if (con != null) {

			try {
				con.close();

			} catch (SQLException e) {
				throw new Exception("Could Not Disconnect...");
			}
		}
	}

}
