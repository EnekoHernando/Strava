package gateways;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import data.domain.Challenge;

public class MailSender {
	private final String from = "stravatw4@gmail.com";
	private final String password = "hynppkcuxwntyxhk";
//	private final String from = "deusto.sd@gmail.com";
//	private final String password = "yzbipwiferkviomi";
	
	private final String host = "smtp.gmail.com";
	private final String port = "587";
	private String subject = "You have created a new Challenge called ";
	private String to;
	
	private Properties props;

	public MailSender(String receiverEmail) {
		to = receiverEmail;
		props = new Properties();
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.debug", "false");
	}

	public String sendMessage(Challenge challenge) {
		try {
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			session.setDebug(true);
			MimeMessage msg = new MimeMessage(session);
			msg.setText(challenge.longToString().trim());
			msg.setSubject(subject + "'"+ challenge.getName()+"' :");
			msg.setFrom(new InternetAddress(from));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			Transport.send(msg);
			System.out.println("Message sent to: " + to);
		} catch (Exception ex) {
			System.err.println(" $ Error sending message: " + ex);
		}
		return "OK";
	}

	private class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(from, password);
		}
	}
}