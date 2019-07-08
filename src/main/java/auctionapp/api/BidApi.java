package auctionapp.api;

import auctionapp.dao.entity.Bid;
import auctionapp.exception.SmallerBid;
import auctionapp.manager.BidManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bidAPI")
public class BidApi {
    private BidManager bidManager;

    @Autowired
    public BidApi(BidManager bidManager) {
        this.bidManager = bidManager;
    }

    @GetMapping("/bids")
    public Iterable<Bid> getAllBids() {
        return bidManager.getAllBids();
    }

    @PostMapping("/bid")
    public Bid saveBid(@RequestBody Bid bid) throws SmallerBid {
        return bidManager.saveBid(bid);
    }

    @GetMapping("/bid/auction/{id}")
    public List<Bid> findAllBidsByAuctionId(@PathVariable Long id) {
        return bidManager.findAllBidsByAuctionId(id);
    }

    @GetMapping("/maxBid/auction/{id}")
    public Optional<Bid> findMaxBidByAucitonId(@PathVariable Long id) {
        return bidManager.findMaxByAuctionId(id);
    }

}
