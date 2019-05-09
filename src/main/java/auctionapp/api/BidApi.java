package auctionapp.api;

import auctionapp.dao.entity.Bid;
import auctionapp.manager.BidManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Bid saveBid(@RequestBody Bid bid) {
        return bidManager.saveBid(bid);
    }

}
