package auctionapp.gmail;

import com.google.api.client.util.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Properties;

@Service
public class GmailSender {

    private GmailCred gmailCred = new GmailCred();
    private static String bodyTestAuction = "<html>\n" +
            "\t<h2>Witaj @@user</h2>\n" +
            "\t<hr><hr>\n" +
            "\t<p>Wystawiłeś aukcję na portalu TimAuctionApp o numerze @@auctionId<p><hr>\n" +
            "\t<p>Na aukcji znajduje się przedmiot o nazwie @@itemName</p>\n" +
            "\t<p>Dziękujemy za skorzystanie z naszego portalu</p>\n" +
            "\t<p>Pozdrawiamy,<hr>Team TimAuctionApp</p>\n" +
            "</html>";

    public static MimeMessage createEmail(String to,
                                          String from,
                                          String subject
//                                          ,String bodyText
                                          )
            throws MessagingException {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        MimeMessage email = new MimeMessage(session);

        email.setFrom(new InternetAddress(from));
        email.addRecipient(javax.mail.Message.RecipientType.TO,
                new InternetAddress(to));
        email.setSubject(subject);
        //email.setText(bodyText);
        return email;
    }


    public static Message createMessageWithEmail(MimeMessage emailContent)
            throws MessagingException, IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        emailContent.writeTo(buffer);
        byte[] bytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(bytes);
        Message message = new Message();
        message.setRaw(encodedEmail);
        return message;
    }

    public static Message sendMessage(Gmail service,
                                      String userId,
                                      MimeMessage emailContent)
            throws MessagingException, IOException {
        Message message = createMessageWithEmail(emailContent);
        message = service.users().messages().send(userId, message).execute();

        System.out.println("Message id: " + message.getId());
        System.out.println(message.toPrettyString());
        return message;
    }

    public Message send(String to, String user, Long auctionId, String itemName ) throws GeneralSecurityException, MessagingException, IOException {

        Gmail service = gmailCred.getGmailSerivce();
        String bodyContent = bodyTestAuction.replace("@@user",user).replace("@@auctionId",auctionId.toString()).replace("@@itemName",itemName);

        MimeMessage emailContext = createEmail(to
                ,"timauctionapp@gmail.com"
                ,"Wystawiono aukcję w portalu TimAuctionApp"
              );
        emailContext.setContent(bodyContent,"text/html; charset=utf-8");
        return sendMessage(service,"timauctionapp@gmail.com",emailContext);
    }
}
