package ir.asta.training.exercise2.features.orders.manager;

import ir.asta.training.exercise2.features.items.manager.ItemDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDTO {
    private Long id;
    private Date purchaseTime;
    private List<ItemDTO> itemDTOs =new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public List<ItemDTO> getItemDTOs() {
        return itemDTOs;
    }

    public void setItemDTOs(List<ItemDTO> itemDTOs) {
        this.itemDTOs = itemDTOs;
    }
}
