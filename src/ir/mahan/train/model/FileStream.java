//package ir.mahan.train.model;
//
//import ir.mahan.train.view.FormEvent;
//import ir.mahan.train.view.userFileFilter;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//
//import javax.swing.JFileChooser;
//
//public class FileStream implements Ifile {
//	private JFileChooser fileChooser;
//	public FileStream() {
//
//		fileChooser = new JFileChooser();
//		fileChooser.setAcceptAllFileFilterUsed(false);
//		fileChooser.addChoosableFileFilter(new userFileFilter());
//
//	}
//
//	public JFileChooser getFileChooser() {
//		return fileChooser;
//	}
//
//	public void setFileChooser(JFileChooser fileChooser) {
//		this.fileChooser = fileChooser;
//	}
////
////	@Override
////	public void writeToFile(FormEvent user) throws IOException {
////		try {
////			File selectedFile = fileChooser.getSelectedFile();
////			FileOutputStream fileStream = new FileOutputStream(selectedFile);
////			ObjectOutputStream os = new ObjectOutputStream(fileStream);
////			os.writeObject(user);
////			os.close();
////
////		} catch (Exception ex) {
////			ex.printStackTrace();
////		}
////	}
//
////	@Override
////	public FormEvent readFromFile() throws Exception {
////		// TODO Auto-generated method stub
////
////		File selectedFile = fileChooser.getSelectedFile();
////		FileInputStream fileStream = new FileInputStream(selectedFile);
////		ObjectInputStream os = new ObjectInputStream(fileStream);
////		FormEvent user = (FormEvent) os.readObject();
////		return user;
////		
////	}
//
//}
