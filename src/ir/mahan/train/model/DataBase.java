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

	public List<Person> getPeopleList() {
		return people;
	}

	public void addPerson(Person p) {
		people.add(p);
	}

	// /---------------------- File -----------------------------------
	public List<Person> loadFromFile(File file){
		try{
		FileInputStream fileStream = new FileInputStream(file);
		ObjectInputStream os = new ObjectInputStream(fileStream);
		Person[] persons = (Person[]) os.readObject();
		people.clear();
		people.addAll(Arrays.asList(persons));
		people.toArray(new Person[people.size()]);
		os.close();
		
		}
		catch(IOException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public void deletePerson(int index) throws Exception {
		connect();
		int id;
		id = people.get(index).getID();
		people.remove(index);
		String removeQuery = "delete from G1.person where id=?";
		PreparedStatement preparedStatement = con.prepareStatement(removeQuery);
		preparedStatement.setInt(1, id);
		preparedStatement.executeUpdate();
		disConnect();
	}

	// /Right Click
	public void updatePerson(Person newPerson) throws Exception {
		connect();
		updateDB(newPerson);
		disConnect();
	}

	public void setDB() throws Exception {
		connect();
		for (Person p : people) {
			Boolean isExist = isPersonExist(p.getID());
			if (isExist) {
				updateDB(p);

			} else {
				saveDB(p);
			}
		}
		disConnect();
	}

	public void saveDB(Person p) {
		String query;
		query = "INSERT INTO G1.Person Values (" + p.getID() + ",'"
				+ p.getName() + "','" + p.getFamily() + "','" + p.getGender()
				+ "','" + p.getAge() + "','" + p.getRole() + "','"
				+ p.getCity() + "','" + p.getFavoriteSport() + "',"
				+ isEmployee(p) + ",'" + p.getSalary() + "')";

		try {
			statement = con.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateDB(Person p) throws Exception {

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

	private Boolean isPersonExist(int id) throws Exception {
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

	public List<Person> loadFromDB() throws Exception {
		connect();
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
		disConnect();
		people = persons;
		return people;

	}

	public boolean authenticate(String userName, String password)
			throws Exception {
		connect();
		try {
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
		} catch (Exception e) {
			disConnect();
			throw e;
		}
	}

}
