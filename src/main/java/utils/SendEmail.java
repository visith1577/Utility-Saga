package utils;


import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class SendEmail {

    Properties emailProperties;
    Session mailSession;
    MimeMessage emailMessage;
    final String fromEmail;
    final String password;
    final String[] toEmails;
    final String subject;
    final String body;

    private static final Dotenv dotenv = Dotenv.load();

    public SendEmail(String fromEmail, String password,String[] toEmails, String subject, String body) {
        this.fromEmail = fromEmail;
        this.password = password;
        this.toEmails = toEmails;
        this.subject = subject;
        this.body = body;
    }

    public void setMailServerProperties() {
        emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", dotenv.get("SMTP_PORT"));
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
    }

    public void createEmailMessage()
            throws AddressException, MessagingException {
        mailSession = Session.getDefaultInstance(emailProperties, null);
        emailMessage = new MimeMessage(mailSession);
        for (String toEmail : toEmails) {
            emailMessage.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(toEmail));
        }
        emailMessage.setSubject(this.subject);
        emailMessage.setText(this.body);
    }

    public void sendEmail() throws AddressException, MessagingException {
        Transport transport = mailSession.getTransport("smtp");
        transport.connect(
                dotenv.get("SMTP_HOST"),
                this.fromEmail,
                this.password
        );
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
    }
}
