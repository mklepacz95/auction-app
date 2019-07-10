package auctionapp.manager;

import auctionapp.dao.ItemRepo;
import auctionapp.dao.entity.Item;
import auctionapp.enums.ItemCategoryEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemManager {

    private ItemRepo itemRepo;

    @Autowired
    public ItemManager(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    public String save(Item item) {
        if(itemRepo.save(item) != null) return "Item saved correct/successful";
        else return "Item do not saved";
    }

    public Iterable<Item> findAll() {
        return itemRepo.findAll();
    }

    public Optional<Item> findById(Integer id) {
        return itemRepo.findById(id);
    }

    public List<Item> findByCategory(ItemCategoryEnum itemCategory) {
        return itemRepo.findByCategory(itemCategory);
    }

    public Item find(Integer id) {
        return itemRepo.findItemById(id);
    }

}
