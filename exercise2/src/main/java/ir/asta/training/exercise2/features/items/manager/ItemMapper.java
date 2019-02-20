package ir.asta.training.exercise2.features.items.manager;

import ir.asta.training.exercise2.entities.ItemEntity;
import ir.asta.training.exercise2.features.products.manager.ProductManager;

import javax.inject.Inject;
import javax.inject.Named;

@Named("itemMapper")
public class ItemMapper {
    // @Inject
    // private OrderManager orderManager;


    @Inject
    private ProductManager productManager;

    public ItemEntity toEntity(ItemDTO dto){
        ItemEntity entity = new ItemEntity();

        entity.setId(dto.getId());
        entity.setQuantity(dto.getQuantity());
        // entity.setShoppingOrder(orderManager.find(dto.getOrderId()));
        entity.setProduct(productManager.find(dto.getProductId()));
        return entity;
    }

    public ItemDTO toDTO(ItemEntity entity){
        ItemDTO dto = new ItemDTO();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        // dto.setOrderId(entity.getShoppingOrder().getId());
        dto.setProductId(entity.getProduct().getId());
        return dto;
    }
}
