package caveatemptor.features.items.repositories;

import caveatemptor.configs.RootConfig;
import caveatemptor.entities.Category;
import caveatemptor.entities.Item;
import caveatemptor.entities.User;
import caveatemptor.features.categories.repositories.CategoryRepository;
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

import javax.management.PersistentMBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional
public class ItemRepositoryImplTest {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private ItemRepository itemRepository;

    private Item item;

    @Before
    public void setup() {
        String name = "table";
        LocalDateTime auctionEnd = LocalDateTime.now().plusWeeks(1L);
        User user = new User("ahmadhoghooghi", "ahmad", "hoghooghi", Collections.emptyList());
        Category category = new Category("facilities");

        BigDecimal initialPrice = new BigDecimal(100);
        item = new Item(name, auctionEnd, user, category, initialPrice);

        em.persist(user);
        em.persist(category);
    }

    @Test
    // @Ignore
    public void persistItemHappyPath() {
        itemRepository.persist(item);
        Long itemId = item.getId();
        assertThat(itemId, is(notNullValue()));
    }

    @Test
    public void findItemByIdHappyPath() {
        itemRepository.persist(item);
        Long id = item.getId();
        Item item = itemRepository.find(id);
        assertThat(item, is(notNullValue()));
    }



    @Test
    // @Ignore
    public void removeItemHappyPath() {
        itemRepository.persist(item);
        Long id = item.getId();

        itemRepository.remove(item);
        Item dbItem = itemRepository.find(id);
        assertThat(dbItem, is(nullValue()));

    }

    @Test
    @Ignore
    public void addImageToItemHappyPath() {
        fail("not implemented yet");
    }

    @Test
    @Ignore
    public void removeImageOfItemHappyPath() {
        fail("not implemented yet");
    }


}