package auctionapp.api;

import auctionapp.dao.entity.Bid;
import auctionapp.exception.SmallerBid;
import auctionapp.manager.BidManager;
import auctionapp.manager.JwtManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bidAPI")
public class BidApi {
    private BidManager bidManager;
    private JwtManager jwtManager;

    @Autowired
    public BidApi(BidManager bidManager) {
        this.bidManager = bidManager;
        this.jwtManager = new JwtManager();
    }

    @GetMapping("/bids")
    public Iterable<Bid> getAllBids() {
        return bidManager.getAllBids();
    }

    @PostMapping("/bid")
    public Bid saveBid(@RequestBody Bid bid, @RequestHeader("Authorization") String jwt) throws SmallerBid {
        String login = jwtManager.getLoginFromJwt(jwt);
        return bidManager.saveBid(bid,login);
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
