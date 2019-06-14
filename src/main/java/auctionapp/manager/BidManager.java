package auctionapp.manager;

import auctionapp.dao.BidRepo;
import auctionapp.dao.entity.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidManager {

    private BidRepo bidRepo;

    @Autowired
    public BidManager(BidRepo bidRepo) {
        this.bidRepo = bidRepo;
    }

    public Iterable<Bid> getAllBids() {
        return bidRepo.findAll();
    }

    public Bid saveBid(Bid bid) {
        return bidRepo.save(bid);
    }

    public List<Bid> findAllBidsByAuctionId(Long id) {
        return  bidRepo.findAllBidsByAuctionId(id);
    }
}
