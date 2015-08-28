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
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new LoginFrame(new Controller());
			}
		});
	}
}
