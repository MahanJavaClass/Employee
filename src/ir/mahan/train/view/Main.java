package ir.mahan.train.view;

import ir.mahan.train.Controller.Controller;
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
