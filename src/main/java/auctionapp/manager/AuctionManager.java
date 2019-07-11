package auctionapp.manager;

import auctionapp.dao.AuctionRepo;
import auctionapp.dao.entity.Auction;
import auctionapp.dao.entity.Item;
import auctionapp.dao.entity.User;
import auctionapp.exception.AuctionException;
import auctionapp.gmail.GmailSender;
import com.google.api.services.gmail.GmailScopes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;

@Service
public class AuctionManager {

    private AuctionRepo auctionRepo;
    private PersonManager personManager;
    private GmailSender gmailSender;
    private UserManager userManager;
    private ItemManager itemManager;

    @Autowired
    public AuctionManager(AuctionRepo auctionRepo, PersonManager personManager, ItemManager itemManager, UserManager userManager) {
        this.auctionRepo = auctionRepo;
        this.personManager = personManager;
        this.itemManager = itemManager;
        this.gmailSender = new GmailSender();
        this.userManager = userManager;
    }

    public Iterable<Auction> getAllAuctions() {
        return auctionRepo.findAll();
    }

    public List<Auction> getAllAuctionsByLogin(String login) {
        return auctionRepo.getAllByUserLogin(login);
    }

    public Auction saveAution(Auction auction, String login) throws AuctionException {
        Item item = auction.getItem();
        Integer userId = userManager.findUserByUsername(login).get(0).getId();
        User user = new User();
        user.setId(userId);
        auction.setUser(user);
        if(item.getId() == null) itemManager.save(item);
        Auction saveAuction = auctionRepo.save(auction);
        if(auction.equals(null)) throw new AuctionException("Auction is not saved");
        return saveAuction;
    }

    public Optional<Auction> getAuctionById(Long id) {
        return auctionRepo.findById(id);
    }


    public String deleteAuction(String login, Long autionId) throws AuctionException {
        Optional<Auction> auction = auctionRepo.findById(autionId);
        if(auction.isPresent()) {
            if(auction.get().getUser().getLogin().equals(login)) {
                auctionRepo.delete(auction.get());
                return "The auction has been successfully removed";
            } else throw new AuctionException("User is not the owner of the auction");
        } else throw new AuctionException("Auction not found");
    }
}
