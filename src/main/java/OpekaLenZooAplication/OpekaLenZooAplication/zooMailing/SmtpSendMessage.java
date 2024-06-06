package OpekaLenZooAplication.OpekaLenZooAplication.zooMailing;

import OpekaLenZooAplication.OpekaLenZooAplication.Constants;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.List;
import java.util.Properties;


public class SmtpSendMessage {
    private final String username = Constants.login;
    private final Session session;

    public SmtpSendMessage() {
        this.session = getSession();
    }

    private Session getSession() {
        final String password = Constants.password;
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.mail.ru");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtps.ssl.checkserveridentity", true);
        prop.put("mail.smtps.ssl.trust", "*");
        prop.put("mail.smtp.ssl.enable", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        return session;
    }

    public void send(String to, String subject, String text, List<File> filePath) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(to)
        );
        message.setSubject(subject);
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(text);
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        for (File file : filePath) {
            addAttachment(multipart, file);
        }
        message.setContent(multipart);
        Transport.send(message);
    }
    public void send(String to, String subject, File htmlFile) throws MessagingException, IOException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(to)
        );
        message.setSubject(subject);

        Multipart multipart = new MimeMultipart();
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(readFile(htmlFile), "text/html; charset=utf-8");
        multipart.addBodyPart(messageBodyPart);
        messageBodyPart = new MimeBodyPart();
        DataSource fds = new FileDataSource(
                "src/main/resources/Html/icon.jpg");
        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID", "<image>");
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);

        Transport.send(message);
    }
    private String readFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder stringBuilder = new StringBuilder();
        while (reader.ready()){
            stringBuilder.append(reader.readLine());
        }
        return stringBuilder.toString();
    }

    private void addAttachment(Multipart multipart, File file) throws MessagingException {
        BodyPart messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(file);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(file.getName());
        multipart.addBodyPart(messageBodyPart);
    }
}
