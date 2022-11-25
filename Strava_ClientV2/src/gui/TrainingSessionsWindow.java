package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
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
	public TrainingSessionsWindow(TrainingSessionController tsc) {
		this.controller = tsc;
		dataT=new JTable();
		tsc.createTrainingSession(tsc.getUser(), "Sesion1", SportDTO.RUNNING, 10, new Date(System.currentTimeMillis()), new Date( System.currentTimeMillis()+24*3600000L), 2);
		headers = new Vector<String>( Arrays.asList( "Ttile", "sport", "Start Date-End Date", "Distance-Time") );
		dataModel = new DefaultTableModel(  
			new Vector<Vector<Object>>(),  
			headers  
		);
		for (TrainingSessionDTO c : controller.getSessions(controller.getUser())) {
			dataModel.addRow( new Object[] {c.getTitle(), c.getSport(), sdf2.format(c.getStartDate())+ " - " + sdf2.format(c.getFinishDate()), c.getDistance() + " km - "+c.getDuration()+" min"} );
		}
		dataT.setModel(dataModel);
		name = new JLabel();
		getContentPane().setLayout(new GridLayout(2,0));
		getContentPane().add(name,BorderLayout.NORTH);
		getContentPane().add(dataT, BorderLayout.CENTER);
		setVisible(true);
		setSize(500,500);
	}
}
