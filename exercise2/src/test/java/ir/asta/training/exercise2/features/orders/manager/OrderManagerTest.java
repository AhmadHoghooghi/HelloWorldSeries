package ir.asta.training.exercise2.features.orders.manager;

import ir.asta.training.exercise2.config.TestConfig;
import ir.asta.training.exercise2.entities.CategoryEntity;
import ir.asta.training.exercise2.entities.ItemEntity;
import ir.asta.training.exercise2.entities.OrderEntity;
import ir.asta.training.exercise2.entities.ProductEntity;
import ir.asta.training.exercise2.features.categories.manager.CategoryManager;
import ir.asta.training.exercise2.features.products.manager.ProductManager;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
// @ActiveProfiles("test")
public class OrderManagerTest {

    @Inject
    private CategoryManager categoryManager;

    @Inject
    private ProductManager productManager;

    @Inject
    private OrderManager orderManager;

    private CategoryEntity category;
    private ProductEntity product;
    private OrderEntity order1;
    private OrderEntity order2;
    private ItemEntity item1;
    private ItemEntity item2;


    @Before
    public void setup(){
        category = new CategoryEntity();
        category.setSubject("category");

        product = new ProductEntity();
        product.setCode("product");

        category.addProduct(product);
        product.addCategory(category);



        order1 = new OrderEntity();
        order1.setPurchaseTime(new Date());
        order2 = new OrderEntity();
        order2.setPurchaseTime(new Date());

        item1 = new ItemEntity();
        item1.setQuantity(1);
        item2 = new ItemEntity();
        item2.setQuantity(2);
    }

    @Test
    // @Ignore
    public void save() {
        categoryManager.save(category);
        productManager.save(product);
        //item1
        item1.setProduct(product);
        item1.setShoppingOrder(order1);
        order1.addItem(item1);

        //save
        orderManager.save(order1);
        Long orderId = order1.getId();
        assertThat(orderId, is(notNullValue()));

        OrderEntity dbOrder = orderManager.find(orderId);
        assertThat(dbOrder.getItems().size(),is(equalTo(1)));
    }

    @Test
    // @Ignore
    public void find() {
        categoryManager.save(category);
        productManager.save(product);
        //item1
        item1.setProduct(product);
        item1.setShoppingOrder(order1);
        order1.addItem(item1);

        //save
        orderManager.save(order1);
        Long orderId = order1.getId();
        assertThat(orderId, is(notNullValue()));

        OrderEntity dbOrder = orderManager.find(orderId);
        assertThat(dbOrder.getItems().size(),is(equalTo(1)));
    }

    @Test
    // @Ignore
    public void list() {
        categoryManager.save(category);
        productManager.save(product);
        item1.setProduct(product);
        item1.setShoppingOrder(order1);

        item2.setProduct(product);
        item2.setShoppingOrder(order2);

        order1.addItem(item1);
        order2.addItem(item2);

        orderManager.save(order1);
        orderManager.save(order2);

        List<OrderEntity> list = orderManager.list();
        assertThat(list.size(), is(equalTo(2)));
    }

    @Test
    // @Ignore
    public void update() {
        categoryManager.save(category);
        productManager.save(product);
        item1.setProduct(product);
        item1.setShoppingOrder(order1);
        order1.addItem(item1);
        orderManager.save(order1);
        //edits:
        //edit1: change purchase time
        Date newDate = new Date();
        order1.setPurchaseTime(newDate);
        //edit2: addition of one other
        item2.setProduct(product);
        item2.setShoppingOrder(order1);
        order1.addItem(item2);
        orderManager.update(order1);
        //
        OrderEntity dbOrder = orderManager.find(order1.getId());
        int itemsNum = dbOrder.getItems().size();
        assertThat(itemsNum, is(equalTo(2)));
        assertThat(dbOrder.getPurchaseTime(), is(equalTo(newDate)));
    }

    @Test
    // @Ignore
    public void delete() {
        categoryManager.save(category);
        productManager.save(product);
        item1.setProduct(product);
        item1.setShoppingOrder(order1);
        order1.addItem(item1);
        orderManager.save(order1);
        OrderEntity dbOrder = orderManager.find(order1.getId());
        orderManager.delete(dbOrder.getId());
        List<OrderEntity> list = orderManager.list();
        assertThat(list.size(), is(equalTo(0)));
    }
}