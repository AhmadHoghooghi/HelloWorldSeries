package ir.asta.training.exercise2.features.orders.manager;

import ir.asta.training.exercise2.entities.OrderEntity;
import ir.asta.training.exercise2.features.orders.dao.OrderDao;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named("orderManager")
public class OrderManager {

    @Inject
    private OrderDao orderDao;

    @Inject
    private OrderMapper orderMapper;


    @Transactional
    public void save(OrderEntity Order) {

        orderDao.save(Order);
    }

    @Transactional
    public void save(OrderDTO orderDTO) {
        OrderEntity orderEntity = orderMapper.toEntity(orderDTO);
        orderEntity.getItems().forEach(item->item.setShoppingOrder(orderEntity));
        save(orderEntity);
        orderDTO.setId(orderEntity.getId());
    }

    @Transactional(readOnly = true)
    public OrderEntity find(Long id) {
        return orderDao.find(id);
    }

    @Transactional(readOnly = true)
    public OrderDTO findDTO(Long id) {
        return orderMapper.toDTO(find(id));
    }

    @Transactional(readOnly = true)
    public void update(OrderDTO orderDTO) {
        update(orderMapper.toEntity(orderDTO));
    }

    @Transactional(readOnly = true)
    public List<OrderEntity> list() {
        return orderDao.list();
    }

    @Transactional(readOnly = true)
    public List<OrderDTO> listDTO() {
        return list().stream().map(orderMapper::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public void update(OrderEntity Order) {
        orderDao.update(Order);
    }

    @Transactional
    public void delete(Long id) {
        orderDao.delete(id);
    }

    @Transactional(readOnly = true)
    public List<OrderEntity> search() {
        return orderDao.search();
        //todo implement search method in CategoryManager.
    }

}
