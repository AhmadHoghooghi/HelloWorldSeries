package caveatemptor.features.items.web;

import caveatemptor.entities.Category;
import caveatemptor.entities.Item;
import caveatemptor.features.categories.services.CategoryService;
import caveatemptor.features.items.repositories.ItemRepository;
import caveatemptor.features.items.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/item", method = RequestMethod.POST)
    public String addItem(@RequestParam("item-name") String itemName,
                          @RequestParam("auction-end") String auctionEndString,
                          @RequestParam("user-name") String userName,
                          @RequestParam("category-name") String categoryName,
                          @RequestParam("initial-price") String initialPriceString
    ) throws Exception {
        LocalDateTime auctionEnd = LocalDateTime.parse(auctionEndString);
        BigDecimal initialPrice = BigDecimal.valueOf(Double.parseDouble(initialPriceString));

        Item item = itemService.createAndPersist(itemName, auctionEnd, userName, categoryName, initialPrice);
        return "redirect:/navigate-categories/" + categoryName;
    }

    @RequestMapping(value = "/item-update-bidding/{itemId}", method = RequestMethod.POST)
    public String updateItemBidding(@PathVariable("itemId") Long id,
                                    @RequestParam("category-name") String categoryName) throws Exception {
        Item item = itemService.findItemById(id);
        itemService.changeBiddableTo(item, !item.isBiddable());
        return "redirect:/navigate-categories/" + categoryName;
    }

    @RequestMapping(value = "/item-update-add-category/{itemId}", method = RequestMethod.POST)
    public String updateItemAddCategory(@PathVariable("itemId") Long id,
                                        @RequestParam("name-of-category-to-add") String categoryToAddName,
                                        @RequestParam("category-name") String categoryName) throws Exception {
        itemService.addNewCategoryToItem(categoryToAddName, id);
        return "redirect:/navigate-categories/" + categoryName;
    }

}
