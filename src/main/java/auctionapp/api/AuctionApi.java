package auctionapp.api;

import auctionapp.dao.entity.Auction;
import auctionapp.exception.AuctionException;
import auctionapp.manager.AuctionManager;
import auctionapp.manager.JwtManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

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
    public Auction saveAuction(@RequestBody Auction auction, @RequestHeader("Authorization") String jwt) throws GeneralSecurityException, IOException, MessagingException, AuctionException {
        return auctionManager.saveAution(auction, jwtManager.getLoginFromJwt(jwt));
    }

    @GetMapping("/userAuction")
    public List<Auction> getAllAuctionByLogin(@RequestHeader("Authorization") String jwt) {
        String login = jwtManager.getLoginFromJwt(jwt);
        return auctionManager.getAllAuctionsByLogin(login);
    }

    @DeleteMapping("/auction/{id}")
    public String deleteAuction(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws AuctionException {
        return auctionManager.deleteAuction(jwtManager.getLoginFromJwt(jwt),id);
    }
}
