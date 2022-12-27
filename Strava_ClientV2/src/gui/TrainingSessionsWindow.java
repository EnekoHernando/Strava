package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllers.TrainingSessionController;
import data.dto.SportDTO;
import data.dto.TrainingSessionDTO;

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
	private boolean creating = false;
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
	private JComboBox<SportDTO> sportT;
	private JPanel creation;
	
	
	public TrainingSessionsWindow(TrainingSessionController tsc) {
		
		this.controller = tsc;
		dataT=new JTable();
		createT = new JButton("Create Session");
		buttons = new JPanel();
		nameSL = new JLabel("Name of the session: ");
		nameST = new JTextField();
		distanceL = new JLabel("Distance: ");
		distanceT = new JTextField("0");
		startDateL = new JLabel("Start Date: ");
		startDateT = new JTextField(sdf2.format(new Date( System.currentTimeMillis())));
		finishDateL = new JLabel("Finish Date: ");
		finishDateT = new JTextField(sdf2.format(new Date( System.currentTimeMillis()+24*3600000L)));
		durationL = new JLabel("Duration: ");
		durationT = new JTextField("0");
		creation = new JPanel();
		sportT = new JComboBox<>();
		sportT.addItem(SportDTO.RUNNING);
		sportT.addItem(SportDTO.CYCLING);
		
		creation.add(nameSL);
		creation.add(nameST);
		creation.add(distanceL);
		creation.add(distanceT);
		creation.add(startDateL);
		creation.add(startDateT);
		creation.add(finishDateL);
		creation.add(finishDateT);
		creation.add(durationL);
		creation.add(durationT);
		creation.setLayout(new GridLayout(5,2));
		headers = new Vector<String>( Arrays.asList( "Ttile", "sport", "Start Date-End Date", "Distance-Time") );
		dataModel = new DefaultTableModel(  
			new Vector<Vector<Object>>(),  
			headers  
		);
		
		for (TrainingSessionDTO c : controller.getSessions(controller.getUser())) {
			System.out.println("Traininn session ::::::" + c);
			dataModel.addRow( new Object[] {c.getTitle(), c.getSport(), sdf2.format(c.getStartDate())+ " - " + sdf2.format(c.getFinishDate()), c.getDistance() + " km - "+c.getDuration()+" min"} );
		}
		createT.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!creating) {
					creating = true;
					createT.setText("Confirm Session");
					getContentPane().removeAll();
					getContentPane().add(name,BorderLayout.NORTH);
					getContentPane().add(creation, BorderLayout.CENTER);
					getContentPane().add(buttons, BorderLayout.SOUTH);
				}else{
					createT.setText("Create Training");
					creating = false;
					try {
						SportDTO spdto = (SportDTO) sportT.getSelectedItem();
						if(spdto == null) {
							spdto = SportDTO.RUNNING;
						}
						controller.createTrainingSession(controller.getUser(), nameST.getText(), spdto, Integer.parseInt(distanceT.getText())
								, sdf2.parse(startDateT.getText()), sdf2.parse(finishDateT.getText()), Integer.parseInt(durationT.getText()));
					}catch (Exception e1) {
					}
					nameST.setText("");
					distanceT.setText("0");
					startDateT.setText(sdf2.format(new Date( System.currentTimeMillis())));
					finishDateT.setText(sdf2.format(new Date( System.currentTimeMillis()+24*3600000L)));
					durationT.setText("0");
					getContentPane().removeAll();
					getContentPane().add(name, BorderLayout.NORTH);
					getContentPane().add(dataT, BorderLayout.CENTER);
					getContentPane().add(buttons, BorderLayout.SOUTH);
					repaint();
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
				}
				
			}
		});
		
		dataT.setModel(dataModel);
		name = new JLabel(tsc.getUser().getEmail());
		buttons.add(createT);
		getContentPane().add(name,BorderLayout.NORTH);
		getContentPane().add(new JScrollPane(dataT), BorderLayout.CENTER);
		getContentPane().add(buttons, BorderLayout.SOUTH);
		setVisible(true);
		setSize(800,800);
	}
}
