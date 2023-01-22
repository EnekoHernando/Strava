package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserWIndow extends JFrame{
	private static final long serialVersionUID = 1L;
	private static SimpleDateFormat sdf2 = new SimpleDateFormat( "dd/MM/yyyy" );
	private JLabel email;
	private JLabel bD;
	private JLabel weight;
	private JLabel height;
	private JLabel maxH;
	private JLabel heartRest;
	private JButton back;
	private JPanel info;
	private JPanel buttons;
	
	public UserWIndow(Challenge_Window cw) {
		info = new JPanel();
		buttons = new JPanel();
		email = new JLabel("Email: "+ cw.getController().getUser().getEmail());
		bD = new JLabel("Birth Date: "+sdf2.format(cw.getController().getUser().getBirthdate()));
		weight = new JLabel("Weight: "+cw.getController().getUser().getWeight() + "kg");
		height = new JLabel("Height: "+cw.getController().getUser().getHeight() + "cm");
		maxH = new JLabel("Max Heart Rate: "+cw.getController().getUser().getMaxHeartRate());
		heartRest = new JLabel("Heart Rate At Rest: " + cw.getController().getUser().getHeartRateAtRest());
		back = new JButton("Return");
		info.setLayout(new GridLayout(7,0));
		info.add(email);
		info.add(bD);
		info.add(weight);
		info.add(height);
		info.add(maxH);
		info.add(heartRest);
		buttons.add(back, BorderLayout.CENTER);
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cw.setVisible(true);
				dispose();
			}
		});
		getContentPane().add(info, BorderLayout.CENTER);
		getContentPane().add(buttons, BorderLayout.NORTH);
		setVisible(true);
		setSize(400,400);
	}
}
