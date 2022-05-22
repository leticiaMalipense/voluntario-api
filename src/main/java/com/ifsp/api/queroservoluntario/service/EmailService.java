package com.ifsp.api.queroservoluntario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Locale;
import java.util.Properties;

@Service
public class EmailService {

    public static final String QUEROSERVOLUNTARIOPROJETO_GMAIL_COM = "colocaremail";
    @Autowired
    private UserService userService;

    @Transactional
    public void sendEmailRecovery(String emailToSend) {
        String newPassword = userService.recoveryPassword(emailToSend);
        sendEmail(emailToSend, newPassword);
    }

    private void sendEmail(String emailToSend, String newPassword) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(QUEROSERVOLUNTARIOPROJETO_GMAIL_COM, "senha");
                    }});
        session.setDebug(true);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(QUEROSERVOLUNTARIOPROJETO_GMAIL_COM));

            Address[] toUser = InternetAddress.parse(emailToSend.toLowerCase().trim());

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Recuperação de senha");
            message.setText("Utilize essa nova senha " + newPassword + " para acessar sua conta no aplicativo Quero ser Volutário. Recomendamos que após login faça a redifinição de senha para uma nova.");
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
