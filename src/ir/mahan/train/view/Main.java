package ir.mahan.train.view;

import ir.mahan.train.Controller.Controller;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LoginFrame loginFrame = new LoginFrame(new Controller());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
