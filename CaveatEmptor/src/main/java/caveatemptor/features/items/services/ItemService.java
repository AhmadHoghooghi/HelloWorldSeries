package caveatemptor.features.items.services;

import caveatemptor.entities.Category;
import caveatemptor.entities.Image;
import caveatemptor.entities.Item;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ItemService {
    Item create(String name, LocalDateTime auctionEnd, String userName, String categoryName, BigDecimal initialPrice) throws Exception;
    Item createAndPersist(String name, LocalDateTime auctionEnd, String userName, String categoryName, BigDecimal initialPrice) throws Exception;
    void persist(Item item);
    void update(Item item);
    Item findItemById(Long id);
    void changeBiddableTo(Item item, boolean biddable) throws Exception;
    void markAsSold(Item item);
    void updateItemAuctionEndTo(Item item, LocalDateTime newAuctionEnd) throws Exception;
    void addImage(Item item, Image image);
    void removeImage(Item item, Image image);
    void addNewCategoryToItem(Category category, Item item) throws Exception;
    void addNewCategoryToItem(String categoryName, Long itemId) throws Exception;
    void removeCategoryFromItem(Item item, Category category) throws Exception;
    List<Item> findItemsInCategory(Category category);
    List<Item> findAllItemsInCategoryHierarchyWithRoot(Category category);
    List<Item> findAllItemsInCategoryHierarchyWithRootName(String categoryName);
    int countItemsInCategory(Category category);
    int countAllItemsInCategoryHierarchyWithRoot(Category category);
}
