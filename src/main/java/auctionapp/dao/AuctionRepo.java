package auctionapp.dao;

import auctionapp.dao.entity.Auction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuctionRepo extends CrudRepository<Auction, Long> {
    List<Auction> getAllByUserLogin(String login);
    Optional<Auction> findById(Long id);
}
