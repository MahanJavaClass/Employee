package ir.mahan.train.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigGenerator {

		String result = "";
		InputStream inputStream;
	 
		public String getPropValues() throws IOException {
	 
			try {
				Properties prop = new Properties();
				String propFileName = "config.properties";
	 
				inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
	 
				if (inputStream != null) {
					prop.load(inputStream);
				} else {
					throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
				}
				String dbuser = prop.getProperty("dbuser");
				String dbpassword = prop.getProperty("dbpassword");
				String database = prop.getProperty("database");
				String ServerAddress = prop.getProperty("ServerAddress");
	 
				result = "jdbc:sqlserver://" + ServerAddress + ";user=" + dbuser + ";password=" +dbpassword+";database="+database ;
			} catch (Exception e) {
				System.out.println("Exception: " + e);
			} finally {
				inputStream.close();
			}
			return result;
		}
	
}
