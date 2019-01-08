package ir.asta.training.exercise2.features.orders.manager;

import ir.asta.training.exercise2.entities.OrderEntity;
import ir.asta.training.exercise2.features.items.manager.ItemMapper;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.stream.Collectors;

@Named("orderMapper")
public class OrderMapper {
    @Inject
    private ItemMapper itemMapper;

    public OrderEntity toEntity(OrderDTO dto) {
        OrderEntity entity = new OrderEntity();

        entity.setId(dto.getId());
        entity.setPurchaseTime(dto.getPurchaseTime());
        entity.setItems(
                dto.getItemDTOs()
                        .stream()
                        .map(itemMapper::toEntity)
                        .collect(Collectors.toList())
        );
        return entity;
    }

    public OrderDTO toDTO(OrderEntity entity) {
        OrderDTO dto = new OrderDTO();
        dto.setId(entity.getId());
        dto.setPurchaseTime(entity.getPurchaseTime());
        dto.setItemDTOs(entity.getItems()
                .stream()
                .map(itemMapper::toDTO)
                .collect(Collectors.toList())
        );
        return dto;
    }
}
