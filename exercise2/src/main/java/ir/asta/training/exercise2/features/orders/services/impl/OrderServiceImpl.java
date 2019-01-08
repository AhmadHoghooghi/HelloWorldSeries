package ir.asta.training.exercise2.features.orders.services.impl;

import ir.asta.training.exercise2.entities.OrderEntity;
import ir.asta.training.exercise2.features.orders.manager.OrderDTO;
import ir.asta.training.exercise2.features.orders.manager.OrderManager;
import ir.asta.training.exercise2.features.orders.services.OrderService;
import ir.asta.wise.core.datamanagement.ActionResult;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named("orderService")
public class OrderServiceImpl implements OrderService {

    @Inject
    private OrderManager orderManager;

    @Override
    public ActionResult<Long> saveOrUpdate(OrderDTO entity) {
        ActionResult<Long> response;
        if (entity.getId() == null) {
            orderManager.save(entity);
            response = new ActionResult<>(true, "سفارش جدید با موفقیت ذخیره شد.", entity.getId());
        } else {
            orderManager.update(entity);
            response = new ActionResult<>("سفارش با موفقیت ویرایش شد.", entity.getId());
        }
        return response;
    }

    @Override
    public OrderDTO load(Long id) {
        return orderManager.findDTO(id);
    }

    @Override
    public List<OrderDTO> list() {
        return  orderManager.listDTO();
    }

    @Override
    public ActionResult<Long> delete(Long id) {
        orderManager.delete(id);
        return new ActionResult<>("موضوع با موفقیت حذف شد.",id);
    }

    @Override
    public List<OrderEntity> search(HttpServletRequest httpServletRequest) {
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        Map<String, String> searchCriteriaMap = new HashMap<>();
        parameterMap.keySet().forEach(key -> {
            searchCriteriaMap.put(key, extractParamFromMap(key, parameterMap));
        });

        throw new RuntimeException("not implemented yet");
        //return orderManager.search(searchCriteriaMap);
        // return null;
    }
    private String extractParamFromMap(String name, Map<String, String[]> parameterMap) {
        return parameterMap.get(name) == null ? null : parameterMap.get(name)[0];
    }
}
