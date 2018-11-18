package caveatemptor.features.categories.repositories;

import caveatemptor.configs.RootConfig;
import caveatemptor.entities.Category;
import caveatemptor.utils.RepoUtil;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.fail;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private RepoUtil repoUtil;

    @Before
    public void setUpDB()  {
        populateDBWithSomeCategories();

    }

    @Test
    public void getRootCategoriesHappyPath() {
        List<Category> categories = categoryRepo.getRootCategories();
        assertThat(categories.size(), is(equalTo(3)));
    }

    @Test
    public void getUnmodifiableChildrenOfCategoryWithIdHappyPath() {
        List<Category> children = categoryRepo.getChildrenOfCategoryWithName("one");
        assertThat(children.size(), is(equalTo(3)));
    }

    @Test
    public void getChildrenNumberOfCategoryWithNameHappyPath() {
        Long count = categoryRepo.getChildrenNumberOfCategoryWithName("one");
        assertThat(count, is(equalTo(3L)));
    }

    @Test
    public void getCategoryByNameHappyPath() {
        Category category = categoryRepo.findByName("1.2");
        assertThat(category, is(notNullValue()));
    }

    @Test
    public void deleteCategoryByNameHappyPath() throws Exception {
        categoryRepo.deleteCategoryByName("1.3");
        Long childrenNum = categoryRepo.getChildrenNumberOfCategoryWithName("one");
        assertThat(childrenNum, is(equalTo(2L)));
    }

    @Test
    public void addRootCategoryHappyPath() {
        categoryRepo.persist("four", null);
        List<Category> rootCategories = categoryRepo.getRootCategories();
        assertThat(rootCategories.size(), equalTo(4));
    }

    @Test
    public void addSubCategoryToHappyPath() {
        Category two = categoryRepo.fullyInitializedFindByName("one");
        categoryRepo.persist("1.4", two);
        Long num = categoryRepo.getChildrenNumberOfCategoryWithName("one");
        assertThat(num, equalTo(4L));
    }

    @Test
    public void addSubCategoryToLeafCategoryHappyPath() {
        Category two = categoryRepo.fullyInitializedFindByName("two");
        assertThat(two, is(notNullValue()));
        categoryRepo.persist("2.1", two);
        Long num = categoryRepo.getChildrenNumberOfCategoryWithName("two");
        assertThat(num, equalTo(1L));
    }


    @Test(expected = DataIntegrityViolationException.class)
    public void testAddCategoryWithDuplicateNameThrowsException() {
        categoryRepo.persist("one", null);
    }

    @Test
    public void deleteOrphanCategoryDeleteHappyPath() throws Exception {
        Category three = categoryRepo.findByName("three");
        categoryRepo.deleteCategoryByName("three");
        List<Category> rootCategories = categoryRepo.getRootCategories();
        assertThat(rootCategories,not(hasItem(three)) );
        assertThat(rootCategories.size(), is(equalTo(2)));
    }

    @Test(expected = Exception.class)
    public void deleteCategoryThrowsExceptionIfHasSubCategory() throws Exception {
        categoryRepo.deleteCategoryByName("one");
    }

    @Test()
    @Ignore
    public void deleteCategoryThrowsExceptionIfHasLinkedItem() {
        fail("not implemented yet");
    }

    @Test
    public void editCategoryNameForLeafCategoriesHappyPath() {
        categoryRepo.editCategoryName("1.1", "one.one");
        Category newSubCategory = categoryRepo.findByName("one.one");
        List<Category> children = categoryRepo.getChildrenOfCategoryWithName("one");
        assertThat(children, hasItem(newSubCategory));
    }

    @Test
    public void editCategoryNameForParentCategoryHappyPath() {
        categoryRepo.editCategoryName("one", "1");
        Category category = categoryRepo.findByName("1");
        List<Category> rootCategories = categoryRepo.getRootCategories();
        assertThat(rootCategories, hasItem(category));
        Long num = categoryRepo.getChildrenNumberOfCategoryWithName("1");
        assertThat(num, is(equalTo(3L)) );
    }

    private void populateDBWithSomeCategories(){
        categoryRepo.persist("one", null);
        categoryRepo.persist("two", null);
        categoryRepo.persist("three", null);
        Category one = categoryRepo.fullyInitializedFindByName("one");
        categoryRepo.persist("1.1", one);
        categoryRepo.persist("1.2", one);
        categoryRepo.persist("1.3", one);

    }
}