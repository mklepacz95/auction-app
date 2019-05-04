package auctionapp.dao;

import auctionapp.dao.entity.Item;
import auctionapp.enums.ItemCategoryEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends CrudRepository<Item, Integer> {
    List<Item> findByCategory(ItemCategoryEnum itemCategory);
}
