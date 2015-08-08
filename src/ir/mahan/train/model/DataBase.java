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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBase {

	private List<Person> people;
	Connection con;
	private int port;
	private String user;
	private String pass;

	public DataBase() {
		people = new ArrayList<>();
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
		return people;

	}

	public List<Person> getPeopleList() {
		return people;
	}

	public void connect() throws Exception {
		if (con != null)
			return;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		} catch (ClassNotFoundException e) {
			throw new Exception("driver Not Found");
		}

		String connectionURL = "jdbc:sqlserver://swsql.mahanair.aero;user=sa;password=123;database=c005_s01_n";
		con = DriverManager.getConnection(connectionURL);
		System.out.println("connected");
	}

	public void disConnect() throws Exception {
		if (con != null)
			return;
		try {
			con.close();
			System.out.println("Disconnected");

		} catch (SQLException e) {
			throw new Exception("Could Not Disconnect...");
		}
	}

	public void save() throws SQLException {
		String SQLCheckCommand = "select count(*) as count from person where id=?";
		PreparedStatement checkstm = con.prepareStatement(SQLCheckCommand);
		checkstm.setInt(1, 5);
		ResultSet checkResult = checkstm.executeQuery();
		checkResult.next();
		int count = checkResult.getInt(1);
		System.out.println(count);
	}

	public static void main(String[] args) {
		DataBase db = new DataBase();
		try {
			db.connect();
			db.save();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
