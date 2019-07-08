package auctionapp.manager;

import auctionapp.dao.AuctionRepo;
import auctionapp.dao.entity.Auction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuctionManager {

    private AuctionRepo auctionRepo;

    @Autowired
    public AuctionManager(AuctionRepo auctionRepo) {
        this.auctionRepo = auctionRepo;
    }

    public Iterable<Auction> getAllAuctions() {
        return auctionRepo.findAll();
    }

    public Iterable<Auction> getAllAuctionsByLogin(String login) {
        return auctionRepo.getAllByUserLogin(login);
    }

    public Auction saveAution(Auction auction) {
        return auctionRepo.save(auction);
    }

}
