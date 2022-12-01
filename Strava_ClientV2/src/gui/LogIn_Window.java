package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.ChallengeController;
import controllers.LoginController;
import data.dto.UserDTO;


public class LogIn_Window extends JFrame{
	private static final long serialVersionUID = 1L;
	private static SimpleDateFormat sdf2 = new SimpleDateFormat( "dd/MM/yyyy" );
	
	//▓▓ BOOLEANS ▓▓
	boolean register = false;
	boolean login = false;
	
	//▓▓ LABELS AND TEXFIELDS ▓▓
	private JLabel logo;
	private LoginController controller;
	private JLabel mailL;
	private JTextField mailT;
	private JLabel passwordL;
	private JTextField passwordT;
	private JLabel dateBL;
	private JTextField dateBT;
	private JLabel weightL;
	private JTextField weightT;
	private JLabel heightL;
	private JTextField heightT;
	private JLabel maxHRL;
	private JTextField maxHRT;
	private JLabel heartRARL;
	private JTextField heartRART;
	
	//▓▓ BUTTONS ▓▓
	private JButton loginB;
	private JButton logInG;
	private JButton logInF;
	private JButton signInB;
	private JButton signInG;
	private JButton signInF;
	private JButton returnB;
	
	//▓▓ PANELS ▓▓
	private JPanel buttons;
	private JPanel info;
	private JPanel i1;
	private JPanel i2;
	
	//▓▓ Logos ▓▓
	ImageIcon imglogo;
	ImageIcon imgGoogle;
	ImageIcon imgFace;
	
	public LogIn_Window(LoginController controller) {
		this.controller = controller;
		@SuppressWarnings("unused")
		LogIn_Window instance = this;
		imglogo = new ImageIcon();
		imgGoogle = new ImageIcon();
		imgFace = new ImageIcon();
		loginB = new JButton("Log in");
		returnB = new JButton("Return");
		try {
			imglogo = new ImageIcon(ImageIO.read(new File("img/Logo_Strava.png")));
			imgGoogle = new ImageIcon(ImageIO.read(new File("img/Google.png")).getScaledInstance(loginB.getPreferredSize().width, loginB.getPreferredSize().height, BufferedImage.SCALE_SMOOTH));
			imgFace = new ImageIcon(ImageIO.read(new File("img/Facebook.png")).getScaledInstance(loginB.getPreferredSize().width, loginB.getPreferredSize().height, BufferedImage.SCALE_SMOOTH));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		logo = new JLabel(imglogo);
		logInG = new JButton(imgGoogle);
		logInF = new JButton(imgFace);
		signInG = new JButton(imgGoogle);
		signInF = new JButton(imgFace);
		signInB = new JButton("Sign in");
		mailL = new JLabel("Mail: ");
		mailT = new JTextField("example@gmail.com",50);
		passwordL = new JLabel("Password: ");
		passwordT = new JTextField("",25);
		dateBL = new JLabel("Birth Date: ");
		dateBT = new JTextField(sdf2.format(new Date( System.currentTimeMillis()-24*3600000L )));
		weightL = new JLabel("Weight: ");
		weightT = new JTextField("50",10);
		heightL = new JLabel("Height: ");
		heightT = new JTextField("160",10);
		maxHRL = new JLabel("Max Heart Rate: ");
		maxHRT = new JTextField("120",10);
		heartRARL = new JLabel("Heart rate at rest: ");
		heartRART = new JTextField("80",10);
		buttons = new JPanel();
		info = new JPanel();
		i1 =new JPanel();
		i2 = new JPanel();
		//LISTENERS
		loginB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!login) {
					getContentPane().removeAll();
					info = new JPanel();
					login = true;
					i1.add(mailL);
					i1.add(passwordL);
					i1.setLayout(new GridLayout(2,1));
					i2.add(mailT);
					i2.add(passwordT);
					i2.setLayout(new GridLayout(2,1));
					info.add(i1);
					info.add(i2);
					buttons.removeAll();
					buttons.add(loginB);
					buttons.add(logInG);
					buttons.add(logInF);
					buttons.add(returnB);
					getContentPane().add(logo, BorderLayout.NORTH);
					getContentPane().add(info, BorderLayout.CENTER);
					getContentPane().add(buttons, BorderLayout.SOUTH);
					setSize(750, 300);
				}else {
					UserDTO user = login("Normal");
					if(user!=null) {
						ChallengeController cc = new ChallengeController(LogIn_Window.this.controller.getService());
						cc.setUser(user);
						new Challenge_Window(cc, instance);
						setVisible(false);
					}else {
						JOptionPane.showMessageDialog(null, "You must register", "Error Loggin in", JLabel.ERROR);
					}
				}
			}
		});
		logInG.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UserDTO user = login("Google");
				if(user!=null) {
					ChallengeController cc = new ChallengeController(LogIn_Window.this.controller.getService());
					cc.setUser(user);
					new Challenge_Window(cc, instance);
					setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "You must register", "Error Loggin in", JLabel.ERROR);
				}
			}
		});
		logInF.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UserDTO user = login("FaceBook");
				if(user!=null) {
					ChallengeController cc = new ChallengeController(LogIn_Window.this.controller.getService());
					cc.setUser(user);
					new Challenge_Window(cc, instance);
					setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "You must register", "Error Loggin in", JLabel.ERROR);
				}
			}
		});
		signInB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				info.removeAll();
				if(!register) {
					register = true;
					signInB.setText("Sign Up");
					buttons.removeAll();
					buttons.add(signInB);
					buttons.add(signInG);
					buttons.add(signInF);
					buttons.add(returnB);
					getContentPane().removeAll();
					JPanel i1 = new JPanel();
					JPanel i2 = new JPanel();
					JPanel i3 = new JPanel();
					JPanel i4= new JPanel();
					JPanel i5 = new JPanel();
					JPanel i6 = new JPanel();
					JPanel i7 = new JPanel();
					i1.setLayout(new GridLayout(2,1));
					i1.add(mailL);
					i1.add(mailT);
					i2.setLayout(new GridLayout(2,1));
					i2.add(passwordL);
					i2.add(passwordT);
					i3.setLayout(new GridLayout(2,1));
					i3.add(dateBL);
					i3.add(dateBT);
					i4.setLayout(new GridLayout(2,1));
					i4.add(weightL);
					i4.add(weightT);
					i5.setLayout(new GridLayout(2,1));
					i5.add(heightL);
					i5.add(heightT);
					i6.setLayout(new GridLayout(2,1));
					i6.add(maxHRL);
					i6.add(maxHRT);
					i7.setLayout(new GridLayout(2,1));
					i7.add(heartRARL);
					i7.add(heartRART);
					info.add(i1);
					info.add(i2);
					info.add(i3);
					info.add(i4);
					info.add(i5);
					info.add(i6);
					info.add(i7);
					info.setLayout(new GridLayout(7,1));
					getContentPane().add(logo,BorderLayout.NORTH);
					getContentPane().add(info);
					getContentPane().add(buttons, BorderLayout.SOUTH);
					setSize(800,800);
				}else {
					UserDTO user = register("Normal");
					if(user!=null) {
						ChallengeController cc = new ChallengeController(LogIn_Window.this.controller.getService());
						cc.setUser(user);
						new Challenge_Window(cc, instance);
						setVisible(false);
					}else {
						JOptionPane.showMessageDialog(null, "That user already exist, Log in", "Error registering", JLabel.ERROR);
					}
				}
			}
		});
		signInG.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UserDTO user = register("Google");
				if(user!=null) {
					ChallengeController cc = new ChallengeController(LogIn_Window.this.controller.getService());
					cc.setUser(user);
					new Challenge_Window(cc, instance);
					setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "You must register", "Error Loggin in", JLabel.ERROR);
				}
			}
		});
		signInF.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UserDTO user = register("FaceBook");
				if(user!=null) {
					ChallengeController cc = new ChallengeController(LogIn_Window.this.controller.getService());
					cc.setUser(user);
					new Challenge_Window(cc, instance);
					setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "You must register", "Error Loggin in", JLabel.ERROR);
				}
			}
		});
		returnB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					login = false;
					register = false;
					getContentPane().removeAll();
					buttons.removeAll();
					buttons.add(loginB);
					buttons.add(signInB);
					getContentPane().add(logo, BorderLayout.CENTER);
					getContentPane().add(buttons, BorderLayout.SOUTH);
					setSize(750,400);
					setVisible(true);
			}
		});
		buttons.add(loginB);
		buttons.add(signInB);
		getContentPane().add(logo, BorderLayout.CENTER);
		getContentPane().add(buttons, BorderLayout.SOUTH);
		setSize(750,400);
		setVisible(true);
	}
	public UserDTO login(String type) {		
		System.out.println(" - Login into the server: '" + this.mailT.getText() + "' - '" + this.passwordT.getText() + "' ...");
		UserDTO result = this.controller.login(mailT.getText(), this.passwordT.getText(), type);
		System.out.println("\t* Login result: " + result);
		System.out.println("\t* Token: " + this.controller.getToken());
		return result;
	}
	public UserDTO register(String type) {
		System.out.println(" - registering into the server: '" + this.mailT.getText() + "' - '" + this.passwordT.getText() + "' ...");
		Date date = new Date(System.currentTimeMillis()-24*3600000L);
		try {
			date = sdf2.parse(dateBT.getText());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		UserDTO result = this.controller.register(type, mailT.getText(), this.passwordT.getText(), date.getTime()
				, Float.parseFloat(weightT.getText()), Integer.parseInt(heightT.getText()), Integer.parseInt(maxHRT.getText()), Integer.parseInt(heartRART.getText()));
		System.out.println("\t* Login result: " + result);
		System.out.println("\t* Token: " + this.controller.getToken());
		return result;
	}
}
