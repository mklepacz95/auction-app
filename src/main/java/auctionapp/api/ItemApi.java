package auctionapp.api;

import auctionapp.dao.entity.Item;
import auctionapp.enums.ItemCategoryEnum;
import auctionapp.manager.ItemManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/itemAPI")
public class ItemApi {

    private ItemManager itemManager;

    @Autowired
    public ItemApi(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @GetMapping("/items")
    public Iterable<Item> getAll() {
        return itemManager.findAll();
    }

    @GetMapping("/item/{id}")
    public Optional<Item> getById(@PathVariable Integer id) {
        return itemManager.findById(id);
    }

    @PostMapping("/item")
    public String addItem(@RequestBody Item item) {
        return itemManager.save(item);
    }

    @GetMapping("/{itemCategory}")
    public List<Item> getItemByCategory(@PathVariable ItemCategoryEnum itemCategory) {
        return itemManager.findByCategory(itemCategory);
    }

}
