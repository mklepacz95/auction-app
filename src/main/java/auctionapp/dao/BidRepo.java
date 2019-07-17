package auctionapp.dao;

import auctionapp.dao.entity.Bid;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BidRepo extends CrudRepository<Bid, Long> {
    List<Bid> findAllBidsByAuctionId(Long id);
    Optional<Bid> findTopByAuctionIdOrderByAmountDesc(Long id);
    Optional<Bid> findTopByAuctionIdAndUserIdOrderByAmountDesc(Long id, Integer userId);

    List<Bid> findAllBidsByAuctionIdAndUserId(Long autcionId, Integer userId);
}
