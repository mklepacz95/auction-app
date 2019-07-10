package auctionapp.manager;

import auctionapp.dao.AuctionRepo;
import auctionapp.dao.entity.Auction;
import auctionapp.dao.entity.Item;
import auctionapp.gmail.GmailSender;
import com.google.api.services.gmail.GmailScopes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;

@Service
public class AuctionManager {

    private AuctionRepo auctionRepo;
    private PersonManager personManager;
    private GmailSender gmailSender;
    private ItemManager itemManager;

    @Autowired
    public AuctionManager(AuctionRepo auctionRepo, PersonManager personManager, ItemManager itemManager) {
        this.auctionRepo = auctionRepo;
        this.personManager = personManager;
        this.itemManager = itemManager;
        this.gmailSender = new GmailSender();
    }

    public Iterable<Auction> getAllAuctions() {
        return auctionRepo.findAll();
    }

    public Iterable<Auction> getAllAuctionsByLogin(String login) {
        return auctionRepo.getAllByUserLogin(login);
    }

    public Auction saveAution(Auction auction) throws GeneralSecurityException, IOException, MessagingException {
        Item item = auction.getItem();
        if(item.getId() == null) itemManager.save(item);
        Auction saveAuction = auctionRepo.save(auction);
        if(auction.equals(null)) throw new NullPointerException("Auction is not saved");
        else {
            String email = personManager.getEmailFromPesronByUserId(saveAuction.getUser().getId());
            String personName = personManager.getNameFromPesronByUserId(saveAuction.getUser().getId());
            String itemName = itemManager.find(saveAuction.getItem().getId()).getName();
            gmailSender.send(email,personName,auction.getId(),itemName);
        }
        return saveAuction;
    }

    public Optional<Auction> getAuctionById(Long id) {
        return auctionRepo.findById(id);
    }

}
