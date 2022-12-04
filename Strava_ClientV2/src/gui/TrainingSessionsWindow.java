package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllers.TrainingSessionController;
import data.dto.TrainingSessionDTO;
import data.dto.SportDTO;

public class TrainingSessionsWindow extends JFrame{
	private static SimpleDateFormat sdf2 = new SimpleDateFormat( "dd/MM/yyyy" );
	private static final long serialVersionUID = 1L;
	private TrainingSessionController controller;
	private JTable dataT;
	private DefaultTableModel dataModel;
	private JLabel name;
	private Vector<String> headers=null;
	private JButton createT;
	private JPanel buttons;
	
	//▓▓▓  CREATION OF THE SESSION  ▓▓▓
	private JLabel nameSL;
	private JTextField nameST;
	private JLabel distanceL;
	private JTextField distanceT;
	private JLabel startDateL;
	private JTextField startDateT;
	private JLabel finishDateL;
	private JTextField finishDateT;
	private JLabel durationL;
	private JTextField durationT;
	public TrainingSessionsWindow(TrainingSessionController tsc) {
		this.controller = tsc;
		dataT=new JTable();
		createT = new JButton("Create Session");
		buttons = new JPanel();
		nameSL = new JLabel("Name of the session: ");
		nameST = new JTextField();
		
		headers = new Vector<String>( Arrays.asList( "Ttile", "sport", "Start Date-End Date", "Distance-Time") );
		dataModel = new DefaultTableModel(  
			new Vector<Vector<Object>>(),  
			headers  
		);
		
		for (TrainingSessionDTO c : controller.getSessions(controller.getUser())) {
			System.out.println("Traininn session ::::::" + c);
			dataModel.addRow( new Object[] {c.getTitle(), c.getSport(), sdf2.format(c.getStartDate())+ " - " + sdf2.format(c.getFinishDate()), c.getDistance() + " km - "+c.getDuration()+" min"} );
		}
		
		dataT.setModel(dataModel);
		name = new JLabel(tsc.getUser().getEmail());
		buttons.add(createT);
		getContentPane().add(name,BorderLayout.NORTH);
		getContentPane().add(dataT, BorderLayout.CENTER);
		getContentPane().add(buttons, BorderLayout.SOUTH);
		setVisible(true);
		setSize(500,500);
	}
}
