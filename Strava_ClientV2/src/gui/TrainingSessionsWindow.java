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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllers.TrainingSessionController;
import data.dto.ChallengeDTO;
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
	private JButton back;
	private JPanel buttons;
	private TrainingSessionRender cellRenderer = new TrainingSessionRender();
	
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
	private JComboBox<ChallengeDTO> challengeT;
	private JLabel challengeL;
	private JPanel creation;
	
	
	public TrainingSessionsWindow(TrainingSessionController tsc, Challenge_Window cw, LogIn_Window lw) {
		
		this.controller = tsc;
		
		dataT=new JTable();
		dataT.setDefaultRenderer(Object.class, cellRenderer);
		createT = new JButton("Create Session");
		back = new JButton("Return");
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
		challengeL = new JLabel("Challenge: ");
		challengeT = new JComboBox<>();
		
		if(cw.getController().getAcceptedChallenges(cw.getController().getUser()).keySet().size() > 0)
			for(ChallengeDTO c: cw.getController().getAcceptedChallenges(cw.getController().getUser()).keySet()) 
				challengeT.addItem(c);
		else {
			JOptionPane.showMessageDialog(null, "There are not any challenge in progress, please accept a new one.", "Error", JOptionPane.ERROR_MESSAGE);
			dispose();
		}
		
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
		creation.add(challengeL);
		
		creation.setLayout(new GridLayout(7,2));
		fillModel();
		
		challengeT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(challengeT.getSelectedItem()!=null) {
					cellRenderer.setChallenge((ChallengeDTO) challengeT.getSelectedItem());
					repaint();
				}
			}
		});
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Challenge_Window(cw.getController(), lw);
				dispose();
			}
		});
		
		createT.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!creating) {
					creating = true;
					createT.setText("Confirm Session");
					getContentPane().removeAll();
					buttons.removeAll();
					buttons.add(back);
					buttons.add(createT);
					creation.add(challengeT);
					getContentPane().add(name,BorderLayout.NORTH);
					getContentPane().add(creation, BorderLayout.CENTER);
					getContentPane().add(buttons, BorderLayout.SOUTH);
					nameST.setText("");
					distanceT.setText("0");
					startDateT.setText(sdf2.format(new Date( System.currentTimeMillis()))+"");
					finishDateT.setText(sdf2.format(new Date( System.currentTimeMillis()+24*3600000L))+"");
					durationT.setText("0");
				}else{
					createT.setText("Create Session");
					creating = false;
					try {
						if(challengeT.getSelectedItem() != null) {
							controller.createTrainingSession(controller.getUser(), nameST.getText(), ((ChallengeDTO) challengeT.getSelectedItem()).getSport(), Integer.parseInt(distanceT.getText())
								, sdf2.parse(startDateT.getText()), sdf2.parse(finishDateT.getText()), Integer.parseInt(durationT.getText()), (ChallengeDTO) challengeT.getSelectedItem());
						}else new JOptionPane("No challenge has been selected", JOptionPane.ERROR_MESSAGE);
					}catch (Exception e1) {
						
					}
					getContentPane().removeAll();
					buttons.removeAll();
					buttons.add(back);
					buttons.add(createT);
					buttons.add(challengeT);
					getContentPane().add(name, BorderLayout.NORTH);
					getContentPane().add(new JScrollPane(dataT), BorderLayout.CENTER);
					getContentPane().add(buttons, BorderLayout.SOUTH);
					repaint();
					fillModel();
					dataT.setModel(dataModel);
				}
				repaint();
			}
		});
		
		dataT.setModel(dataModel);
		name = new JLabel(tsc.getUser().getEmail());
		buttons.add(back);
		buttons.add(createT);
		buttons.add(challengeT);
		getContentPane().add(name,BorderLayout.NORTH);
		getContentPane().add(new JScrollPane(dataT), BorderLayout.CENTER);
		getContentPane().add(buttons, BorderLayout.SOUTH);
		setVisible(true);
		setSize(800,800);
	}
	public void fillModel() {
		headers = new Vector<String>( Arrays.asList( "Title", "sport", "Start Date-End Date", "Distance-Time", "Challenge") );
		dataModel = new DefaultTableModel(  
			new Vector<Vector<Object>>(),  
			headers  
		);
		for (TrainingSessionDTO c : controller.getSessions(controller.getUser())) {
			dataModel.addRow( new Object[] {c.getTitle(), c.getSport(), sdf2.format(c.getStartDate())+ " - " + sdf2.format(c.getFinishDate()), c.getDistance() + " km - "+c.getDuration()+" min", c.getChallenges()} );
		}
	}
}
