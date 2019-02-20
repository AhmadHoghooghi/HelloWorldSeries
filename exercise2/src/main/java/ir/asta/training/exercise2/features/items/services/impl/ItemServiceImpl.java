package ir.asta.training.exercise2.features.items.services.impl;

import ir.asta.training.exercise2.entities.ItemEntity;
import ir.asta.training.exercise2.features.items.manager.ItemDTO;
import ir.asta.training.exercise2.features.items.manager.ItemManager;
import ir.asta.training.exercise2.features.items.services.ItemService;
import ir.asta.training.exercise2.utils.UtilMethods;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named("itemService")
public class ItemServiceImpl implements ItemService {
    @Inject
    private ItemManager itemManager;

    @Override
    public List<ItemDTO> search(HttpServletRequest httpServletRequest) throws ParseException {
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        Map<String, String> searchCriteriaMap = new HashMap<>();
        parameterMap.keySet().forEach(
                key ->
                        searchCriteriaMap.put(key, UtilMethods.extractParamFromMap(key, parameterMap))
        );
        return itemManager.search(searchCriteriaMap);
    }

    @Override
    public List<ItemDTO> totalSaleByCategory(String category) {
        return itemManager.totalSaleByCategory(category);
    }
}
