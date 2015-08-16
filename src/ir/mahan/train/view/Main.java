package ir.mahan.train.view;
import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new LoginFrame();

			}
		});
	}
}
