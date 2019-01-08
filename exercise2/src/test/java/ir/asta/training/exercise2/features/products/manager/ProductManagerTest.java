package ir.asta.training.exercise2.features.products.manager;

import ir.asta.training.exercise2.config.TestConfig;
import ir.asta.training.exercise2.entities.CategoryEntity;
import ir.asta.training.exercise2.entities.ProductEntity;
import ir.asta.training.exercise2.features.categories.manager.CategoryManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
// @ActiveProfiles("test")
public class ProductManagerTest {
    @Inject
    private ProductManager productManager;
    @Inject
    private CategoryManager categoryManager;

    @Test
    // @Ignore
    public void saveProductWithoutCategories() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCode("111");
        productManager.save(productEntity);
        assertThat(productEntity.getId(), is(notNullValue()));
    }

    @Test
    public void saveProductWithCategoriesHappyPath() {
        // categoryEntity.
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setSubject("category1");
        categoryManager.save(categoryEntity);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setCode("product1");
        productEntity.setCategories(Arrays.asList(categoryEntity));

        // categoryEntity.setProducts(Arrays.asList(productEntity));

        productManager.save(productEntity);

        assertThat("on [product with category] save, product itself should save successfully.",
                productEntity.getId(), is(notNullValue()));
        ProductEntity dbProduct = productManager.find(productEntity.getId());
        assertThat("on [product with category] save, categories should save successfully",
                dbProduct.getCategories().size(),is(equalTo(1)));

    }

    @Test
    public void saveProductDTOHappyPath(){
        //save category
        CategoryEntity category = new CategoryEntity();
        category.setSubject("subject");
        categoryManager.save(category);
        Long categoryId = category.getId();

        //
        ProductDTO productDTO = new ProductDTO();
        productDTO.setCode("1");
        productDTO.setCategoriesIdList(Arrays.asList(categoryId));
        productManager.save(productDTO);

        assertThat(productDTO.getId(), is(notNullValue()));

        //read from database and check
        ProductEntity dbProduct = productManager.find(productDTO.getId());
        assertThat("on [productDTO with categories save] categories should save successfully"
                ,dbProduct.getCategories().size(), is(equalTo(1)));
    }


    @Test
    // @Ignore
    public void find() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCode("product1");
        productManager.save(productEntity);
        ProductEntity dbEntity = productManager.find(productEntity.getId());
        assertThat(dbEntity.getId(), is(notNullValue()));
    }

    @Test
    // @Ignore
    public void list() {
        ProductEntity product1 = new ProductEntity();
        product1.setCode("1");
        productManager.save(product1);

        ProductEntity product2 = new ProductEntity();
        product2.setCode("2");
        productManager.save(product2);

        List<ProductEntity> list = productManager.list();
        assertThat(list.size(), is(equalTo(2)));

    }

    @Test
    // @Ignore
    public void updateProductBasicFeatures() {
        ProductEntity product = new ProductEntity();
        product.setCode("1");
        productManager.save(product);
        String editedCode = "2";
        product.setCode(editedCode);
        productManager.update(product);
        ProductEntity dbProduct = productManager.find(product.getId());
        assertThat(dbProduct.getCode(), is(equalTo(editedCode)));
    }


    @Test
    // @Ignore
    public void delete() {
        ProductEntity product = new ProductEntity();
        product.setCode("1");
        productManager.save(product);
        productManager.delete(product.getId());
        List<ProductEntity> list = productManager.list();
        assertThat(list.size(), is(equalTo(0)));
    }
}