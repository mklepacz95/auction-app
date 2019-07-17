package auctionapp.manager;

import auctionapp.dao.BidRepo;
import auctionapp.dao.entity.Auction;
import auctionapp.dao.entity.Bid;
import auctionapp.dao.entity.User;
import auctionapp.exception.SmallerBid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidManager {

    private BidRepo bidRepo;
    private AuctionManager auctionManager;
    private UserManager userManager;

    @Autowired
    public BidManager(BidRepo bidRepo, AuctionManager auctionManager, UserManager userManager) {
        this.bidRepo = bidRepo;
        this.auctionManager = auctionManager;
        this.userManager = userManager;
    }

    public Iterable<Bid> getAllBids() {
        return bidRepo.findAll();
    }

    public Bid saveBid(Bid bid, String login) throws SmallerBid {
        User u = new User();
        Integer userId = userManager.findUserByUsername(login).get(0).getId();
        u.setId(userId);
        bid.setUser(u);
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

    public List<Bid> findAllBidsByAuctionIdAndUserId(Long id, String login) {
        Integer userId = userManager.findUserByUsername(login).get(0).getId();
        return  bidRepo.findAllBidsByAuctionIdAndUserId(id,userId);
    }

    public Optional<Bid> findMaxByAuctionId(Long id) { return bidRepo.findTopByAuctionIdOrderByAmountDesc(id);}


    public Optional<Bid> findTopByAuctionIdAndUserIdOrderByAmountDesc(Long id, String login) {
        Integer userId = userManager.findUserByUsername(login).get(0).getId();
        return bidRepo.findTopByAuctionIdAndUserIdOrderByAmountDesc(id, userId);
    }
}
