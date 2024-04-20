package utils;


import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;

public class SendEmail {

    Properties emailProperties;
    Session mailSession;
    MimeMessage emailMessage;
    final String fromEmail;
    final String[] toEmails;
    final String subject;
    final String body;

    private static final Dotenv dotenv = Dotenv.load();
    final String username = dotenv.get("SMTP2GO_USERNAME");
    final String password = dotenv.get("SMTP2GO_PASSWORD");

    public SendEmail(String fromEmail,String[] toEmails, String subject, String body) {
        this.fromEmail = fromEmail;
        this.toEmails = toEmails;
        this.subject = subject;
        this.body = body;
    }


    public void sendEmail(String message) {
        try {
            emailProperties = System.getProperties();
            emailProperties.put("mail.smtp.port", dotenv.get("SMTP_PORT"));
            emailProperties.put("mail.smtp.auth", "true");
            emailProperties.put("mail.smtp.starttls.enable", "true");
            emailProperties.put("mail.smtp.host", dotenv.get("SMTP2GO_HOST"));
            mailSession = Session.getInstance(emailProperties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            emailMessage = new MimeMessage(mailSession);
            Multipart mp = new MimeMultipart("alternative");
            BodyPart textMessage = new MimeBodyPart();
            textMessage.setText(message);
            BodyPart htmlMessage = new MimeBodyPart();
            htmlMessage.setContent(body, "text/html");
            mp.addBodyPart(textMessage);
            mp.addBodyPart(htmlMessage);
            emailMessage.setFrom(new InternetAddress(fromEmail));
            for (String toEmail : toEmails) {
                emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            }
            emailMessage.setSubject(subject);
            emailMessage.setContent(mp);
            Transport.send(emailMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
