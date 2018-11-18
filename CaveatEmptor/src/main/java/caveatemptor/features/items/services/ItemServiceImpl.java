package caveatemptor.features.items.services;

import caveatemptor.entities.Category;
import caveatemptor.entities.Image;
import caveatemptor.entities.Item;
import caveatemptor.features.categories.services.CategoryService;
import caveatemptor.features.items.repositories.ItemRepository;
import caveatemptor.features.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public Item create(String name, LocalDateTime auctionEnd, String userName, String categoryName, BigDecimal initialPrice) throws Exception {
        Category category = categoryService.findCategoryByName(categoryName);
        Item item = new Item(name, auctionEnd, userService.findByUserName(userName), category, initialPrice);
        category.addItem(item);
        return item;
    }

    @Override
    public Item createAndPersist(String name, LocalDateTime auctionEnd, String userName, String categoryName, BigDecimal initialPrice) throws Exception {
        Item item = create(name, auctionEnd, userName, categoryName, initialPrice);
        persist(item);
        return item;
    }

    @Override
    public void persist(Item item) {
        itemRepository.persist(item);
        item.getCategories().stream().forEach(category -> categoryService.update(category));
    }

    @Override
    public void update(Item item) {
        itemRepository.update(item);
    }

    @Override
    public Item findItemById(Long id) {
        return itemRepository.find(id);
    }

    @Override
    public void changeBiddableTo(Item item, boolean biddable) throws Exception {
        item.setBiddable(biddable);
        itemRepository.update(item);
    }

    @Override
    public void markAsSold(Item item) {
        item.setAsSold();
        itemRepository.update(item);
    }

    @Override
    public void updateItemAuctionEndTo(Item item, LocalDateTime newAuctionEnd) throws Exception {
        item.setAuctionEnd(newAuctionEnd);
        itemRepository.update(item);
    }

    @Override
    public void addImage(Item item, Image image) {
        item.addImage(image);
        itemRepository.update(item);
    }

    @Override
    public void removeImage(Item item, Image image) {
        item.removeImage(image);
        itemRepository.update(item);
    }

    @Override
    public void addNewCategoryToItem(Category category, Item item) throws Exception {
        item.addCategory(category);
        category.addItem(item);

        categoryService.update(category);
        update(item);
    }

    @Override
    public void addNewCategoryToItem(String categoryName, Long itemId) throws Exception {
        Category category = categoryService.findCategoryByName(categoryName);
        Item item = findItemById(itemId);
        addNewCategoryToItem(category, item);
    }

    @Override
    public void removeCategoryFromItem(Item item, Category category) throws Exception {
        item.removeCategory(category);
        category.removeItem(item);

        categoryService.update(category);
        update(item);
    }

    @Override
    public List<Item> findItemsInCategory(Category category) {
        if (category == null) {
            return new ArrayList<>();
        }
        return categoryService.findItemsInCategory(category);
    }

    @Override
    public List<Item> findAllItemsInCategoryHierarchyWithRoot(Category upperCategory) {
        List<Item> resultList = new ArrayList<>();
        resultList.addAll(findItemsInCategory(upperCategory));

        List<Category> children;
        if (upperCategory == null) {
            children = categoryService.getRootCategories();
        } else {
            children = upperCategory.getUnModifiableChildren();
        }

        for (Category category : children) {
            if (category.getUnModifiableChildren().size() == 0) {
                resultList.addAll(findItemsInCategory(category));
            } else {
                resultList.addAll(findAllItemsInCategoryHierarchyWithRoot(category));
            }
        }
        //to initialize categories collection of item
        resultList.forEach(item->item.getCategories().size());
        return resultList;
    }

    @Override
    public List<Item> findAllItemsInCategoryHierarchyWithRootName(String categoryName) {
        Category category = categoryService.findCategoryByName(categoryName);
        return findAllItemsInCategoryHierarchyWithRoot(category);
    }

    public int countItemsInCategory(Category category) {
        if (category == null) {
            return 0;
        }
        return category.getItems().size();
    }

    @Override
    public int countAllItemsInCategoryHierarchyWithRoot(Category upperCategory) {
        int counter = 0;
        counter += countItemsInCategory(upperCategory);

        List<Category> children;
        if (upperCategory == null) {
            children = categoryService.getRootCategories();
        } else {
            children = upperCategory.getUnModifiableChildren();
        }

        for (Category category : children) {
            if (category.getUnModifiableChildren().size() == 0) {
                counter += countItemsInCategory(category);
            } else {
                counter += countAllItemsInCategoryHierarchyWithRoot(category);
            }
        }
        return counter;
    }

}
