package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eider
 */

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailMethods
{
    public void SendMailNewAccount(String accUsername, String accFullName, String accEmail)
    {
        final String username = "trackandfieldcompetitions@gmail.com";
        final String password = "TAFCompetitions";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
        
        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("trackandfieldcompetitions@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("trackandfieldcompetitions@gmail.com"));
            message.setSubject("New Account: " + accUsername);
            message.setText("Information of the new account:"
                    + "\n\nUsername: " + accUsername
                    + "\nFull Name: " + accFullName
                    + "\nEmail Account: " + accEmail);

            Transport.send(message);
        }
        catch (MessagingException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    public void SendMailAccountRecovery(String[] accRecInfo)
    {
        final String username = "trackandfieldcompetitions@gmail.com";
        final String password = "TAFCompetitions";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
        
        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("trackandfieldcompetitions@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(accRecInfo[3]));
            message.setSubject("Password Recovery - Track And Field Competitions");
            message.setText("Hello " + accRecInfo[2] + ","
                    + "\n\nThis is the information of your account: "
                    + "\nUsername: " + accRecInfo[0]
                    + "\nPassword: " + accRecInfo[1]
                    + "\n\nIf you didn't want to recover your password or didn't request this, just ignore and delete this message. "
                    + "To keep your account secure, please don't forward this email to anyone."
                    + "\n\nBest regards,"
                    + "\nTrack And Field Competitions Team.");

            Transport.send(message);
        }
        catch (MessagingException e)
        {
                throw new RuntimeException(e);
        }
    }
}
