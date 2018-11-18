package caveatemptor.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Bid {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR_NAME)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDate createdDate;

    @ManyToOne(optional = false)
    private User bidder;

    @ManyToOne(optional = false)
    private Item item;

    protected Bid() {
    }

    public Bid(User bidder, Item item, BigDecimal amount, LocalDate createdDate) throws Exception {
        this.amount = amount;
        this.createdDate = createdDate;
        this.bidder = bidder;
        if (!item.isBiddable()) {
            throw new Exception("Item is not biddable");
        } else {
            this.item = item;
        }

    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public User getBidder() {
        return bidder;
    }

    public Item getItem() {
        return item;
    }
}
