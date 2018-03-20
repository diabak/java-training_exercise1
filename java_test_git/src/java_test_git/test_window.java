package java_test_git;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class test_window extends JFrame implements ActionListener{
	public void button () {
		
	}
	JPanel p_main = new JPanel();
	JButton b_accept = new JButton("Accept!");
	JTextArea t_text_place = new JTextArea("test...",2,1);
	JFormattedTextField n_field = new JFormattedTextField();
	String s_text_area = null;
	
	public test_window() {
		// -- Main Windows setting
		setSize(300,200);
		setTitle("My first window app");
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		// -- TEXT AREA
		t_text_place.setLineWrap(true);
		t_text_place.setWrapStyleWord(true);
		p_main.add(t_text_place);
		
		// -- Number Field
		n_field.setColumns(5);
		//n_field.setFormatterFactory(tf);
		//n_field.addPropertyChangeListener("value", this);
		p_main.add(n_field);
		
		// -- ACCEPT Button
		b_accept.setBounds(100,50,100,20);
		p_main.add(b_accept);
		b_accept.addActionListener(this);
		
		add(p_main);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == b_accept) {
			System.out.println("TEST0");
			s_text_area = t_text_place.getText();
			System.out.println("TEST1" + s_text_area);
		}	
	}
	///private void counting_function(int value) {
		// TODO Auto-generated method stub
		//System.out.println("TESTing");
	//}
}
