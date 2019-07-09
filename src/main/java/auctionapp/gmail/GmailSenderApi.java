package auctionapp.gmail;

import com.google.api.services.gmail.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("/email")
public class GmailSenderApi {

    private GmailSender gmailSender;

    @Autowired
    public GmailSenderApi(GmailSender gmailSender) {
        this.gmailSender = gmailSender;
    }

    @GetMapping("/test")
    public String test() {
        return "dzialam";
    }

    @PostMapping("/wyslij")
    public Message wyslij() throws GeneralSecurityException, IOException, MessagingException {
        return gmailSender.wyslijMaila();
    }

}
