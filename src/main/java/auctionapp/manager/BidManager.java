package auctionapp.manager;

import auctionapp.dao.BidRepo;
import auctionapp.dao.entity.Auction;
import auctionapp.dao.entity.Bid;
import auctionapp.exception.SmallerBid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidManager {

    private BidRepo bidRepo;
    private AuctionManager auctionManager;

    @Autowired
    public BidManager(BidRepo bidRepo, AuctionManager auctionManager) {
        this.bidRepo = bidRepo;
        this.auctionManager = auctionManager;
    }

    public Iterable<Bid> getAllBids() {
        return bidRepo.findAll();
    }

    public Bid saveBid(Bid bid) throws SmallerBid {
        Optional<Auction> auction = auctionManager.getAuctionById(bid.getAuction().getId());
        if(auction.isPresent()) {
            Optional<Bid> maxBid = findMaxByAuctionId(auction.get().getId());
            if(!maxBid.isPresent()) return bidRepo.save(bid);
            else {
                if(maxBid.get().getAmount() < bid.getAmount()){
                    if(bid.getAmount() - maxBid.get().getAmount() >= auction.get().getMinBid()) return bidRepo.save(bid);
                    else throw new SmallerBid("Bid have to small amount. Minium amount must be: " + (auction.get().getMinBid()+maxBid.get().getAmount()) );
                }
                else throw new SmallerBid("Amount in bid is smaller than max bid of auction");
            }
        }
        else throw new NullPointerException("Auction not found");
        //return bidRepo.save(bid);
    }

    public List<Bid> findAllBidsByAuctionId(Long id) {
        return  bidRepo.findAllBidsByAuctionId(id);
    }

    public Optional<Bid> findMaxByAuctionId(Long id) { return bidRepo.findTopByAuctionIdOrderByAmountDesc(id);}
}
