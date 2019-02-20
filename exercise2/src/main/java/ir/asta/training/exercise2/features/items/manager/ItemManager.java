package ir.asta.training.exercise2.features.items.manager;

import ir.asta.training.exercise2.entities.ItemEntity;
import ir.asta.training.exercise2.features.items.dao.ItemDao;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named("itemManager")
public class ItemManager {
    @Inject
    private ItemDao itemDao;

    @Inject
    private ItemMapper itemMapper;
    public List<ItemDTO> search(Map<String, String> searchCriteriaMap) throws ParseException {
        return itemDao.search(searchCriteriaMap).stream()
                .map(itemMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ItemDTO> totalSaleByCategory(String category) {
        return itemDao
                .totalSaleByCategory(category)
                .stream()
                .map(item-> itemMapper.toDTO(item))
                .collect(Collectors.toList());
    }
}
