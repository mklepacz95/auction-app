package auctionapp.api;

import auctionapp.dao.entity.Auction;
import auctionapp.manager.AuctionManager;
import auctionapp.manager.JwtManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("/auctionAPI")
public class AuctionApi {

    private AuctionManager auctionManager;
    private JwtManager jwtManager;

    @Autowired
    public AuctionApi(AuctionManager auctionManager) {
        this.auctionManager = auctionManager;
        this.jwtManager = new JwtManager();
    }

    @GetMapping("/auctions")
    public Iterable<Auction> getAllAuction(@RequestHeader("Authorization") String jwt) {
        return  auctionManager.getAllAuctions();
    }

    @PostMapping("/auction")
    public Auction saveAuction(@RequestBody Auction auction) throws GeneralSecurityException, IOException, MessagingException {
        return auctionManager.saveAution(auction);
    }

    @GetMapping("/userAuction")
    public Iterable<Auction> getAllAuctionByLogin(@RequestHeader("Authorization") String jwt) {
        String login = jwtManager.getLoginFromJwt(jwt);
        return auctionManager.getAllAuctionsByLogin(login);
    }
}
