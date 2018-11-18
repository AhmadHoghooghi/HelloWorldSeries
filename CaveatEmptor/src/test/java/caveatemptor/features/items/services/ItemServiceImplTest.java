package caveatemptor.features.items.services;

import caveatemptor.configs.RootConfig;
import caveatemptor.entities.Category;
import caveatemptor.entities.Item;
import caveatemptor.entities.User;
import caveatemptor.features.categories.services.CategoryService;
import caveatemptor.features.items.repositories.ItemRepository;
import caveatemptor.features.users.services.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TreeMap;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional
public class ItemServiceImplTest {
    Category a, b, ax, ay, bx, by, axi;
    Item A, B, AX, AY, BX, BY, AXI1,AXI2;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @Before
    public void setup() throws Exception {
        String name = "table";
        LocalDateTime auctionEnd = LocalDateTime.now().plusWeeks(1L);
        User user = userService.create("ahmadhoghooghi", "ahmad", "hoghooghi");
        userService.persist(user);
        BigDecimal initialPrice = new BigDecimal(100);

        //categories
        a = categoryService.create("a");
        categoryService.persist(a);

        b = categoryService.create("b");
        categoryService.persist(b);

        ax = categoryService.create("ax", a);
        categoryService.persist(ax);

        ay = categoryService.create("ay", a);
        categoryService.persist(ay);

        bx = categoryService.create("bx");
        categoryService.persist(bx);

        by = categoryService.create("by");
        categoryService.persist(by);

        axi = categoryService.create("axi", ax);
        categoryService.persist(axi);

        //Items
        A = itemService.create("A", auctionEnd, user.getUserName(), a.getName(), initialPrice);
        itemService.persist(A);

        B = itemService.create("B", auctionEnd, user.getUserName(), b.getName(), initialPrice);
        itemService.persist(B);

        AX = itemService.create("AX", auctionEnd, user.getUserName(), ax.getName(), initialPrice);
        itemService.persist(AX);

        AY = itemService.create("AY", auctionEnd, user.getUserName(), ay.getName(), initialPrice);
        itemService.persist(AY);

        BX = itemService.create("BX", auctionEnd, user.getUserName(), bx.getName(), initialPrice);
        itemService.persist(BX);

        BY = itemService.create("BY", auctionEnd, user.getUserName(), by.getName(), initialPrice);
        itemService.persist(BY);

        AXI1 = itemService.create("AXI1", auctionEnd, user.getUserName(), axi.getName(), initialPrice);
        itemService.persist(AXI1);

        AXI2 = itemService.create("AXI2", auctionEnd, user.getUserName(), axi.getName(), initialPrice);
        itemService.persist(AXI2);

        // System.out.println("setup is done");
    }



    @Test
    // @Ignore
    public void countItemsInCategory(){
        int itemNum = itemService.countItemsInCategory(axi);
        assertThat(itemNum, is(equalTo(2)));

        Category DBaxi = categoryService.findCategoryByName(axi.getName());
        int DBitemNum = itemService.countItemsInCategory(DBaxi);
        assertThat(DBitemNum, is(equalTo(2)));
    }

    @Test
    // @Ignore
    public void countAllItemsInCategoryHierarchyWithRoot(){
        int num = itemService.countAllItemsInCategoryHierarchyWithRoot(a);
        assertThat(num, is(equalTo(5)));
    }

    @Test
    public void findItemsInCategory(){
        List<Item> itemsInCategory = itemService.findItemsInCategory(axi);
        assertThat(itemsInCategory.size(), is(equalTo(2)));
    }

    @Test
    public void findAllItemsInCategoryHierarchyWithRoot(){
        List<Item> aItems = itemService.findAllItemsInCategoryHierarchyWithRoot(a);
        assertThat(aItems.size(), is(equalTo(5)));
    }

    @Test(expected = Exception.class)
    // @Ignore
    public void addingNewCategoryWhenItsParentOfOneCurrentlySetCategoryFails() throws Exception {
        itemService.addNewCategoryToItem(a, AXI1);
    }

    @Test(expected = Exception.class)
    // @Ignore
    public void removeCategoryFailsWhenItsTheOnlyCategoryOfItem() throws Exception {
        itemService.removeCategoryFromItem(AXI1, axi);
    }

    @Test
    //@Ignore
    public void addNewCategoryHappyPath() throws Exception {
        Category newCategory = new Category("newCategory");
        categoryService.persist(newCategory);
        assertThat(newCategory.getId(), is(notNullValue()));

        itemService.addNewCategoryToItem(newCategory, A);

        Category dbNewCategory = categoryService.findCategoryByName("newCategory");

        assertThat(dbNewCategory.getItems().size(), is(equalTo(1)));
        assertThat(A.getCategories(), hasItem(dbNewCategory));
    }

    @Test(expected = Exception.class)
    //@Ignore
    public void updateItemAuctionEndToPastTimeFails() throws Exception {
        itemService.updateItemAuctionEndTo(A, LocalDateTime.now().minusDays(2L));
    }

    @Test
    //@Ignore
    public void updateItemAuctionEndHappyPath() throws Exception {
        itemService.updateItemAuctionEndTo(A, LocalDateTime.now().plusDays(3L));
        //
        Long id = A.getId();
        assertThat(id, is(notNullValue()));
        Item dbItem = itemService.findItemById(id);
        assertThat(dbItem, is(notNullValue()));
        assertThat(dbItem.getAuctionEnd().isAfter(LocalDateTime.now().plusDays(2L)) ,is(true) );
        assertThat(dbItem.getAuctionEnd().isBefore(LocalDateTime.now().plusDays(4L)) ,is(true) );
    }
    @Test
    // @Ignore
    public void updateItemNotBiddableHappyPath() throws Exception {
        itemService.changeBiddableTo(A, false);
        Long Aid = A.getId();
        Item dbA = itemService.findItemById(Aid);
        assertThat(dbA.isBiddable(), is(false));
    }

    @Test
    // @Ignore
    public void updateItemAsSoldHappyPath() {
        itemService.markAsSold(A);
        Long Aid = A.getId();
        Item dbA = itemService.findItemById(Aid);
        assertThat(dbA.isSold(), is(true));
    }

    @Test(expected = Exception.class)
    //@Ignore
    public void updateItemAsBiddableAfterItIsSoldFails() throws Exception {
        itemService.markAsSold(A);
        Long Aid = A.getId();
        itemService.changeBiddableTo(A, true);
    }


    @Test(expected = Exception.class)
    public void updateItemAsBiddableAfterAuctionEndFails() throws Exception {
        itemService.updateItemAuctionEndTo(A,LocalDateTime.now());
        Thread.sleep(200);
        itemService.changeBiddableTo(A, true);
    }

    @Test
    //@Ignore
    public void findItemsInCategoryWorksFineForNull(){
        List<Item> itemsInNullCategory = itemService.findItemsInCategory(null);
        assertThat(itemsInNullCategory.size(), is(equalTo(0)));
    }

    @Test
    // @Ignore
    public void countItemsInCategoryWorksFineForNullCategory(){
        int num = itemService.countItemsInCategory(null);
        assertThat(num, is(equalTo(0)));
    }

    @Test
    //@Ignore
    public void findAllItemsInCategoryHierarchyWithRootWorksFineForNullCategory(){
        List<Item> items = itemService.findAllItemsInCategoryHierarchyWithRoot(null);
        assertThat(items.size(), is(equalTo(8)));
    }


    @Test
    public void countAllItemsInCategoryHierarchyWithRootWorksFineForNullCategory(){
        int num = itemService.countAllItemsInCategoryHierarchyWithRoot(null);
        assertThat(num, is(equalTo(8)));
    }

}