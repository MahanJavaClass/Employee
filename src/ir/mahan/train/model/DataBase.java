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
	private Connection con;
	private Statement statement = null;

	public DataBase() {
		people = new ArrayList<>();
	}

	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String connectionURL = "jdbc:sqlserver://swsql.mahanair.aero;user=sa;password=123;database=javaTraining";
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

		FileInputStream fileStream = new FileInputStream(file);
		ObjectInputStream os = new ObjectInputStream(fileStream);
		Person[] persons = (Person[]) os.readObject();
		people.clear();
		people.addAll(Arrays.asList(persons));
		people.toArray(new Person[people.size()]);
		os.close();
		return people;
	}

	public void saveToFile(File file) throws IOException {
		FileOutputStream fileStream = new FileOutputStream(file);
		ObjectOutputStream os = new ObjectOutputStream(fileStream);
		Person[] persons = people.toArray(new Person[people.size()]);
		os.writeObject(persons);
		os.close();

	}

	// /---------------------- DataBase -------------------------------
	// /Right Click
	public void deletePerson(int index) throws SQLException {
		int id;
		id = people.get(index).getID();
		people.remove(index);
		String removeQuery = "delete from G1.person where id=?";
		PreparedStatement preparedStatement = con.prepareStatement(removeQuery);
		preparedStatement.setInt(1, id);
		preparedStatement.executeUpdate();
		;
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

			if (gender.equals("Female"))
				genderE = Gender.Female;
			else
				genderE = Gender.Male;

			if (role.equals("staff"))
				roleE = Role.staff;
			else if (role.equals("student"))
				roleE = Role.student;
			else
				roleE = Role.teacher;

			Person p = new Person(ID, name, family, genderE, age, roleE, city,
					favSport, isEmp, salary);
			persons.add(p);
		}
		people = persons;
		return people;

	}

	public boolean authenticate(String userName, String password)
			throws SQLException {

		String query = "select username from [User] where username=? and password=?";
		PreparedStatement checkstm = con.prepareStatement(query);
		checkstm.setString(1, userName);
		checkstm.setString(2, password);
		ResultSet rs = checkstm.executeQuery();
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}

}
