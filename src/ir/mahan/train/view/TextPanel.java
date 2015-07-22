package ir.mahan.train.view;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class TextPanel extends JPanel {
	
	JTextArea textArea;
	
	public TextPanel(){
		this.setLayout (new BoxLayout(this, BoxLayout.X_AXIS));  
		textArea = new JTextArea();
		setLayout(new BorderLayout());
		textArea.setLineWrap(true);
		add(new JScrollPane(textArea));
		this.add(textArea);
	    textArea.setEditable(false);
	    this.setBorder(BorderFactory.createTitledBorder("Text Panel"));
	}
	
	public void setText(String str) {
		this.textArea.append(str + "\n");
	}

}
