package ir.mahan.train.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigGenerator {
//	public void generateCongigFile() {
//		Properties prop = new Properties();
//		FileOutputStream output = null;
//
//		try {
//			//"jdbc:sqlserver://swsql.mahanair.aero;user=sa;password=123;database=javaTraining";
//			output = new FileOutputStream("config.properties");
//			prop.setProperty("ServerAddress", "swsql.mahanair.aero");
//			prop.setProperty("database", "javaTraining");
//			prop.setProperty("dbuser", "sa");
//			prop.setProperty("dbpassword", "123");
//
//			// save properties to project root folder
//			prop.store(output, null);
//
//		} catch (IOException io) {
//			io.printStackTrace();
//		} finally {
//			if (output != null) {
//				try {
//					output.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			
//		}
//
//	}

	public String ReadPropertiesFile() throws IOException {
		File file = new File("test.properties");
		FileInputStream fileInput = new FileInputStream(file);
		Properties properties = new Properties();
		properties.load(fileInput);
		fileInput.close();
		return null;
	}
}
