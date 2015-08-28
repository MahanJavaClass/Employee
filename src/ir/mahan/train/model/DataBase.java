package ir.mahan.train.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	private Connection con;
	private Statement statement = null;
	ConfigGenerator configGenerator;

	public DataBase() {
		people = new ArrayList<>();
	}

	public void connect() throws ClassNotFoundException, SQLException,
			IOException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String connectionURL = new ConfigGenerator().getPropValues();
		con = DriverManager.getConnection(connectionURL);
	}

	public void disConnect() throws SQLException {
		if (con != null)
			con.close();
	}

	public List<Person> getPeopleList() {
		return people;
	}

	public void addPerson(Person p) {
		people.add(p);
	}

	// /---------------------- File -----------------------------------
	public List<Person> loadFromFile(File file) throws IOException,
			ClassNotFoundException {
		ObjectInputStream os = new ObjectInputStream(new FileInputStream(file));
		people.clear();
		people.addAll(Arrays.asList((Person[]) os.readObject()));
		people.toArray(new Person[people.size()]);
		os.close();
		return people;
	}

	public void saveToFile(File file) throws IOException {
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(
				file));
		Person[] persons = people.toArray(new Person[people.size()]);
		os.writeObject(persons);
		os.close();

	}

	// /---------------------- DataBase -------------------------------
	// /Right Click
	public void deletePerson(int index) throws SQLException {
		String removeQuery = "delete from G1.person where id=?";
		PreparedStatement preparedStatement = con.prepareStatement(removeQuery);
		preparedStatement.setInt(1, people.get(index).getID());
		preparedStatement.executeUpdate();
		people.remove(index);
	}

	// /Right Click
	public void updatePerson(Person newPerson) throws SQLException {
		updateDB(newPerson);
	}

	public void setDB() throws SQLException {
		for (Person p : people) {
			Boolean isExist = isPersonExist(p.getID());
			if (isExist) {
				updateDB(p);

			} else {
				saveDB(p);
			}
		}
	}

	public void saveDB(Person p) throws SQLException {
		String query;
		query = "INSERT INTO G1.Person Values (" + p.getID() + ",'"
				+ p.getName() + "','" + p.getFamily() + "','" + p.getGender()
				+ "','" + p.getAge() + "','" + p.getRole() + "','"
				+ p.getCity() + "','" + p.getFavoriteSport() + "',"
				+ isEmployee(p) + ",'" + p.getSalary() + "')";

		statement = con.createStatement();
		statement.executeUpdate(query);

	}

	public void updateDB(Person p) throws SQLException {

		String query;
		query = "UPDATE G1.Person set FirstName='" + p.getName()
				+ "',LastName='" + p.getFamily() + "',Gender='" + p.getGender()
				+ "',Age='" + p.getAge() + "',Category='" + p.getRole()
				+ "',City='" + p.getCity() + "',Sport='" + p.getFavoriteSport()
				+ "',IsEmployee=" + isEmployee(p) + ",Salary='" + p.getSalary()
				+ "' WHERE ID=" + p.getID();
		statement = con.createStatement();
		statement.executeUpdate(query);
	}

	private Boolean isPersonExist(int id) throws SQLException {
		String query = "select * from G1.person where ID=" + id;
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		if (rs.next())
			return true;
		else
			return false;
	}

	public int isEmployee(Person p) {
		if (p.getIsEmp())
			return 1;
		else {
			return 0;
		}
	}

	public List<Person> loadFromDB() throws SQLException {
		ArrayList<Person> persons = new ArrayList<Person>();
		String query = "SELECT * FROM G1.Person ORDER BY ID ";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		while (rs.next()) {
			persons.add(readColumnsOfPersonTable(rs));
		}
		people = persons;
		return people;

	}

	public Person readColumnsOfPersonTable(ResultSet rs) throws SQLException {
		Role roleE;
		Gender genderE;
		genderE = recognizePersonGender(rs);
		roleE = recognizePersonRole(rs);
		Person p = new Person(rs.getInt("ID"), rs.getString("FirstName"),
				rs.getString("LastName"), genderE, rs.getString("Age"), roleE,
				rs.getString("City"), rs.getString("Sport"),
				rs.getBoolean("IsEmployee"), rs.getString("Salary"));
		return p;
	}

	public Role recognizePersonRole(ResultSet rs) throws SQLException {

		if (rs.getString("Category").equals("staff"))
			return Role.staff;
		else if (rs.getString("Category").equals("student"))
			return Role.student;
		else
			return Role.teacher;
	}

	public Gender recognizePersonGender(ResultSet rs) throws SQLException {
		if (rs.getString("Gender").equals("Female"))
			return Gender.Female;
		else
			return Gender.Male;
	}

	public String authenticate(String userName)
			throws SQLException {
		String correctHash = null;
		String query = "select * from [User] where username= '"+userName+"'";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
		 correctHash = rs.getString("password");
		}
		if (correctHash != null) {
			return correctHash;
		} else {
			throw new SQLException("User or password incorrect");
		}
	}

}
