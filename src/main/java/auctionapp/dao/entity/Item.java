package auctionapp.dao.entity;

import auctionapp.enums.ItemCategoryEnum;

import javax.persistence.*;

@Entity
@Table(name ="item")
public class Item {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private ItemCategoryEnum category;

    public Item(String name, ItemCategoryEnum category) {
        this.name = name;
        this.category = category;
    }

    public Item() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(ItemCategoryEnum category) {
        this.category = category;
    }
}
