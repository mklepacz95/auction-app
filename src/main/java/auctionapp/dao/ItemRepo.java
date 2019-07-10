package auctionapp.dao;

import auctionapp.dao.entity.Item;
import auctionapp.enums.ItemCategoryEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepo extends CrudRepository<Item, Integer> {
    List<Item> findByCategory(ItemCategoryEnum itemCategory);
    Item findItemById(Integer id);
}
