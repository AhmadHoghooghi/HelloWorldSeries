package ir.asta.training.exercise2.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "WISE_ORDER")
public class OrderEntity {

    private Long id;
    private Date purchaseTime;
    private List<ItemEntity> items = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "shoppingOrder", orphanRemoval = true, cascade = {CascadeType.ALL})
    @OnDelete(action = OnDeleteAction.CASCADE)
    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }
    @Temporal(TemporalType.TIMESTAMP)
    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public void addItem(ItemEntity item) {
        items.add(item);
    }
    public void removeItem(ItemEntity item){
        items.remove(item);
    }
}
