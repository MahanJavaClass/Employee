package ir.mahan.train.view;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import ir.mahan.train.Controller.Controller;
import ir.mahan.train.model.PasswordHash;

import javax.swing.SwingUtilities;

/**
 * @author sedigheh.arabameri 
 * @author zohre.moradi
 *
 */
public class Main {

	public static void main(String[] args) {
		try {
			String result ="1000:5a639c508fa3258149470cfc9eb4f4f3d7da09b2d8fc1aa5:081b56d72cc72326fb977f2dd62ff3b6326b9f804a97f01b";
			System.err.println(PasswordHash.validatePassword("1234",result));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				new LoginFrame(new Controller());
//			}
//		});
	}
}
