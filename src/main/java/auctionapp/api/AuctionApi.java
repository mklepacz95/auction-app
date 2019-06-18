package auctionapp.api;

import auctionapp.dao.entity.Auction;
import auctionapp.manager.AuctionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auctionAPI")
public class AuctionApi {

    private AuctionManager auctionManager;

    @Autowired
    public AuctionApi(AuctionManager auctionManager) {
        this.auctionManager = auctionManager;
    }

    @GetMapping("/auctions")
    public Iterable<Auction> getAllAuction() {
        return  auctionManager.getAllAuctions();
    }

    @PostMapping("/auction")
    public Auction saveAuction(@RequestBody Auction auction) {
        return auctionManager.saveAution(auction);
    }


}
