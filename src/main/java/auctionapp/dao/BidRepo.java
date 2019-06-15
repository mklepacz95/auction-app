package auctionapp.dao;

import auctionapp.dao.entity.Bid;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepo extends CrudRepository<Bid, Long> {
    List<Bid> findAllBidsByAuctionId(Long id);
}
