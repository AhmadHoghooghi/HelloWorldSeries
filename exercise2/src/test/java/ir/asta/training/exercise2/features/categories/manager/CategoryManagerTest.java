package ir.asta.training.exercise2.features.categories.manager;



import ir.asta.training.exercise2.config.TestConfig;
import ir.asta.training.exercise2.entities.CategoryEntity;
import ir.asta.training.exercise2.entities.ProductEntity;
import ir.asta.training.exercise2.features.products.manager.ProductManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
// @ActiveProfiles("test")
public class CategoryManagerTest {

    @Inject
    private CategoryManager categoryManager;

    @Inject
    private ProductManager productManager;

    @Test
    public void save() {
        CategoryEntity category = new CategoryEntity();
        category.setSubject("test Category");
        categoryManager.save(category);
        assertThat(category.getId(),is(notNullValue()));
    }

    @Test
    public void find() {
        CategoryEntity category = new CategoryEntity();
        String subject = "test Category";
        category.setSubject(subject);
        categoryManager.save(category);
        Long id = category.getId();
        CategoryEntity dbEntity = categoryManager.find(id);
        assertThat(dbEntity.getSubject(), is(equalTo(subject)));
    }

    @Test
    public void list() {
        CategoryEntity category1 = new CategoryEntity();
        category1.setSubject("category1");
        categoryManager.save(category1);

        CategoryEntity category2 = new CategoryEntity();
        category2.setSubject("category1");
        categoryManager.save(category2);

        List<CategoryEntity> list = categoryManager.list();
        assertThat(list.size(), is(equalTo(2)));

    }

    @Test
    public void updateBasicFeatures() {
        CategoryEntity category = new CategoryEntity();
        category.setSubject("subject");
        categoryManager.save(category);
        String editedSubject = "subject-edited";
        category.setSubject(editedSubject);
        categoryManager.update(category);
        CategoryEntity dbCategory = categoryManager.find(category.getId());
        assertThat(dbCategory.getSubject(), is(equalTo(editedSubject)));
    }

    @Test
    public void delete() {
        CategoryEntity category = new CategoryEntity();
        category.setSubject("subject");
        categoryManager.save(category);
        categoryManager.delete(category.getId());
        List<CategoryEntity> list = categoryManager.list();
        assertThat(list.size(), is(equalTo(0)));
    }
}