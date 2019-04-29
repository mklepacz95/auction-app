package auctionapp.dao;

import auctionapp.dao.entity.Item;
import auctionapp.enums.ItemCategoryEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepo extends CrudRepository<Item, Integer> {
    List<Item> findByCategory(ItemCategoryEnum itemCategory);
}
