package auctionapp.dao.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "auction")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_price")
    private Double startPrice;

    @Column(name = "min_price")
    private Double minPrice;

    @Column(name = "min_bid")
    private Double minBid;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    //the number of days of the auction
    @Column(name = "how_long")
    private Integer howLong;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    @ManyToOne
    private User user;

    public Auction() {
    }

    public Auction(Double startPrice, Double minPrice, Double minBid, LocalDateTime startDate, Integer howLong, Item item, User user) {
        this.startPrice = startPrice;
        this.minPrice = minPrice;
        this.minBid = minBid;
        this.startDate = startDate;
        this.howLong = howLong;
        this.item = item;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMinBid() {
        return minBid;
    }

    public void setMinBid(Double minBid) {
        this.minBid = minBid;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Integer getHowLong() {
        return howLong;
    }

    public void setHowLong(Integer howLong) {
        this.howLong = howLong;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
