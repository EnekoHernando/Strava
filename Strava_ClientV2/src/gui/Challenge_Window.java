package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllers.ChallengeController;
import controllers.TrainingSessionController;
import data.dto.ChallengeDTO;
import data.dto.SportDTO;

	
//POSSIBLY WILL BE UPGRADED
public class Challenge_Window extends JFrame{
	private static final long serialVersionUID = 1L;
	private static SimpleDateFormat sdf2 = new SimpleDateFormat( "dd/MM/yyyy" );
	private boolean accepting = true; //If true the user is accepting challenges, if false is completing
	private JPanel buttons;
	private JButton logOut;
	private JButton cChallenge; //Completed challenges
	private JButton aChallenge; //Accepted challenge
	private JButton allChallenges; //Community challenges
	private JRadioButton checkBox;
	private JTable tableC;
	private DefaultTableModel dataModel;
	private JTextField nameU;
	private /*JTextField*/JButton sesion;
	private ChallengeController controller;
	
	private JButton createChallenges;

	//FIELD FOR THE CREATING OF THE CHALLENGE
	boolean creating = false;
	private JLabel nameL;
	private JTextField nameT;
	private JLabel initialDL;
	private JTextField initialDT;
	private JLabel endDL;
	private JTextField endDT;
	private JLabel targetDL;
	private JTextField targetDT;
	private JLabel targetTL;
	private JTextField targetTT;
	private JLabel sportL;
	private JComboBox<SportDTO> sportT;
	private JPanel create;

	private JPanel panel;
	
	public Challenge_Window(ChallengeController cC, LogIn_Window lw) {
		//FIELD INSTANCES FOR THE CREATING OF THE CHALLENGE
		nameL=new JLabel("Name: ");
		nameT = new JTextField("",150);
		initialDL = new JLabel("Initial date: ");
		initialDT = new JTextField(sdf2.format(new Date( System.currentTimeMillis())),20);
		endDL = new JLabel("Endin date: ");
		endDT = new JTextField(sdf2.format(new Date( System.currentTimeMillis()+24*3600000L)),20);
		targetDL = new JLabel("Target Distance (km): ");
		targetDT = new JTextField("0",10);
		targetTL = new JLabel("Target Time (minutes): ");
		targetTT = new JTextField("0",10);
		sportL = new JLabel("Sport: ");
		sportT = new JComboBox<>();
		sportT.addItem(SportDTO.RUNNING);
		sportT.addItem(SportDTO.CYCLING);
		create = new JPanel();
		panel = new JPanel();
		create.setLayout(new GridLayout(6,0));
		create.add(nameL);
		create.add(nameT);
		create.add(initialDL);
		create.add(initialDT);
		create.add(endDL);
		create.add(endDT);
		create.add(targetDL);
		create.add(targetDT);
		create.add(targetTL);
		create.add(targetTT);
		create.add(sportL);
		create.add(sportT);
		//FIELD INSTANCE FOR THE REST OF THE WINDOW
		checkBox = new JRadioButton();
		this.controller = cC;
		tableC = new JTable();
		nameU = new JTextField(this.controller.getUser().getEmail());
		buttons = new JPanel();
		sesion = new JButton("Trainnig sessions");
		sesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TrainingSessionController tsc = new TrainingSessionController(controller.getService());
				tsc.setUser(controller.getUser());			
			}
		});
		logOut = new JButton("Log out.");
		logOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				logout();
				lw.setVisible(true);
				setVisible(false);
			}
		});
		cChallenge = new JButton("Completed Challenges");
		cChallenge.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				modelTable("completed");
				tableC.setModel(dataModel);
				tableC.getColumnModel().getColumn(0).setMinWidth(200);
				tableC.getColumnModel().getColumn(0).setMaxWidth(200);
				tableC.getColumnModel().getColumn(1).setMinWidth(100);
				tableC.getColumnModel().getColumn(1).setMaxWidth(100);
				tableC.getColumnModel().getColumn(2).setMinWidth(150);
				tableC.getColumnModel().getColumn(2).setMaxWidth(150);
				tableC.getColumnModel().getColumn(3).setMinWidth(100);
				tableC.getColumnModel().getColumn(3).setMaxWidth(100);
				
			}
		});
		aChallenge = new JButton("Accepted Challenges");
		aChallenge.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				accepting=false;
				modelTable("Accepted");
				tableC.setModel(dataModel);
				tableC.getColumnModel().getColumn(0).setMinWidth(200);
				tableC.getColumnModel().getColumn(0).setMaxWidth(200);
				tableC.getColumnModel().getColumn(1).setMinWidth(100);
				tableC.getColumnModel().getColumn(1).setMaxWidth(100);
				tableC.getColumnModel().getColumn(2).setMinWidth(150);
				tableC.getColumnModel().getColumn(2).setMaxWidth(150);
				tableC.getColumnModel().getColumn(3).setMinWidth(100);
				tableC.getColumnModel().getColumn(3).setMaxWidth(100);
			}
		});
		allChallenges= new JButton("Community Challenges");
		allChallenges.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				accepting=true;
				modelTable("all");
				tableC.setModel(dataModel);
				tableC.getColumnModel().getColumn(0).setMinWidth(200);
				tableC.getColumnModel().getColumn(0).setMaxWidth(200);
				tableC.getColumnModel().getColumn(1).setMinWidth(100);
				tableC.getColumnModel().getColumn(1).setMaxWidth(100);
				tableC.getColumnModel().getColumn(2).setMinWidth(150);
				tableC.getColumnModel().getColumn(2).setMaxWidth(150);
				tableC.getColumnModel().getColumn(3).setMinWidth(100);
				tableC.getColumnModel().getColumn(3).setMaxWidth(100);
			}
		});
		tableC.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.isAltDown()) {
					if(accepting) {
						int row = tableC.rowAtPoint(e.getPoint());
						if (row>=0) {
							controller.acceptChallenge(row);
						}
					}else {
						int row = tableC.rowAtPoint(e.getPoint());
						if(row>=0) {
							controller.completeChallenge(row);
						}
					}
				}
			}
		});
		createChallenges = new JButton("Create challenge");
		createChallenges.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!creating) {
					creating = true;
					createChallenges.setText("Confirm challenge");
					getContentPane().removeAll();
					getContentPane().add(create, BorderLayout.CENTER);
					getContentPane().add(buttons, BorderLayout.SOUTH);
					repaint();
				}else {
					createChallenges.setText("Create challenge");
					creating = false;
					try {
						controller.createChallenge(nameT.getText(), sdf2.parse(initialDT.getText()), sdf2.parse(endDT.getText()),
								Float.parseFloat(targetDT.getText()), Integer.parseInt(targetTT.getText()), (SportDTO) sportT.getSelectedItem());
					} catch (Exception e2) {
					}
					nameT.setText("");
					initialDT.setText(sdf2.format(new Date( System.currentTimeMillis())));
					endDT.setText(sdf2.format(new Date( System.currentTimeMillis()+24*3600000L)));
					targetDT.setText("0");
					targetTT.setText("0");
					modelTable("all");
					tableC.setModel(dataModel);
					tableC.getColumnModel().getColumn(0).setMinWidth(200);
					tableC.getColumnModel().getColumn(0).setMaxWidth(200);
					tableC.getColumnModel().getColumn(1).setMinWidth(100);
					tableC.getColumnModel().getColumn(1).setMaxWidth(100);
					tableC.getColumnModel().getColumn(2).setMinWidth(150);
					tableC.getColumnModel().getColumn(2).setMaxWidth(150);
					tableC.getColumnModel().getColumn(3).setMinWidth(100);
					tableC.getColumnModel().getColumn(3).setMaxWidth(100);
					getContentPane().removeAll();
					allChallenges.setEnabled(true);
					aChallenge.setEnabled(true);
					cChallenge.setEnabled(true);
					nameU.setText(controller.getUser().getEmail());
					panel.removeAll();
					panel.add(nameU);
					panel.add(sesion);
					getContentPane().add(panel, BorderLayout.NORTH);
					getContentPane().add( new JScrollPane(tableC), BorderLayout.CENTER );
					getContentPane().add(buttons, BorderLayout.SOUTH);
					repaint();
				}
			}
		});
		modelTable("all");
		tableC.setModel(dataModel);
		tableC.getColumnModel().getColumn(0).setMinWidth(200);
		tableC.getColumnModel().getColumn(0).setMaxWidth(200);
		tableC.getColumnModel().getColumn(1).setMinWidth(100);
		tableC.getColumnModel().getColumn(1).setMaxWidth(100);
		tableC.getColumnModel().getColumn(2).setMinWidth(150);
		tableC.getColumnModel().getColumn(2).setMaxWidth(150);
		tableC.getColumnModel().getColumn(3).setMinWidth(100);
		tableC.getColumnModel().getColumn(3).setMaxWidth(100);
		buttons.add(logOut);
		buttons.add(allChallenges);
		buttons.add(aChallenge);
		buttons.add(cChallenge);
		buttons.add(createChallenges);
		panel.setLayout(new GridLayout(1,2));
		nameU.setText(this.controller.getUser().getEmail());
		panel.add(nameU);
		panel.add(sesion);
		getContentPane().add(panel, BorderLayout.NORTH);
		getContentPane().add( new JScrollPane(tableC), BorderLayout.CENTER );
		getContentPane().add(buttons, BorderLayout.SOUTH);
		setVisible(true);
		setSize(800,800);
	}

	public void logout() {
		System.out.println(" - Logout from the server...");		
		this.controller.logout(this.controller.getUser());
		System.out.println("\t* Logout success!");
	}
	public void modelTable(String type) {
		Vector<String> headers=null;
		switch (type) {
		case "completed":
			headers = new Vector<String>( Arrays.asList( "Name", "sport", "Start Date-End Date", "Distance-Time") );
			dataModel = new DefaultTableModel(  
				new Vector<Vector<Object>>(),  
				headers  
			);
			for (ChallengeDTO c : controller.getCompletedChallenges(controller.getUser())) {
				dataModel.addRow( new Object[] {c.getName(), c.getSport().name(), sdf2.format(c.getStartDate())+ " - " + sdf2.format(c.getEndDate()), c.getTargetDistance() + " km - "+c.getTargetTime()+" min"} );
			}
			tableC.setModel(dataModel);
			break;
		case "all":
			headers = new Vector<String>( Arrays.asList( "Name", "sport", "Start Date-End Date", "Distance-Time", "Accepted"));
			dataModel = new DefaultTableModel(  
				new Vector<Vector<Object>>(),  
				headers  
			);
			for (ChallengeDTO c : controller.recoverAllChallenges()) {
				checkBox.setSelected(false);
				for(ChallengeDTO cdto:controller.getAcceptedChallenges(controller.getUser())) {
					if(c.equals(cdto)) {
						checkBox.setSelected(true);
						break;
					}
				}
				dataModel.addRow( new Object[] {c.getName(), c.getSport().name(), sdf2.format(c.getStartDate())+ " - " + sdf2.format(c.getEndDate()), c.getTargetDistance() + " km - "+c.getTargetTime()+" min", checkBox.isSelected()} );
			}
			tableC.setModel(dataModel);
			break;
		default:
			headers = new Vector<String>( Arrays.asList( "Name", "sport", "Start Date-End Date", "Distance-Time", "Completed") );
			dataModel = new DefaultTableModel(  
				new Vector<Vector<Object>>(),  
				headers  
			);
			for (ChallengeDTO c : controller.getAcceptedChallenges(controller.getUser())) {
				checkBox.setSelected(false);
				for(ChallengeDTO cdto:controller.getCompletedChallenges(controller.getUser())) {
					if(c.equals(cdto)) {
						checkBox.setSelected(true);
						break;
					}
				}
				dataModel.addRow( new Object[] {c.getName(), c.getSport().name(), sdf2.format(c.getStartDate())+ " - " + sdf2.format(c.getEndDate()), c.getTargetDistance() + " km - "+c.getTargetTime()+" min", checkBox.isSelected()} );
			}
			tableC.setModel(dataModel);
			break;
		}
	}
}
