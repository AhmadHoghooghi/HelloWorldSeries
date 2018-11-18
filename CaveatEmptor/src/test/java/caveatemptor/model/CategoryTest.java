package caveatemptor.model;

import caveatemptor.entities.Category;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class CategoryTest {

    private static Category root1;
    private static Category root2;

    @Before
    public void setup() {
        root1 =null;
        root1 = new Category("root1");

        root2=null;
        root2 = new Category("root2");

    }

    @Test
    public void isRootWorksForRootCategory() {
        assertThat(root1.isRoot(), is(equalTo(true)));
    }

    @Test
    public void isRootWorksForNonRootCategory() {
        Category nonRoot = new Category("nonRoot", root1);
        assertThat(nonRoot.isRoot(), is(equalTo(false)));
    }

    @Test
    public void rowChildCategoryIsAccessibleFromParent(){
        Category child = new Category("child", root1);
        assertThat(root1.getUnModifiableChildren(), contains(child));
    }

    @Test
    public void deleteWorksProperly(){
        Category categoryToDelete = new Category("child", root1);
        categoryToDelete.delete();
        assertThat(root1.getUnModifiableChildren(), not(contains(categoryToDelete)));
    }

    @Test
    public void equalsWorksCorrectly(){
        assertThat(root1.equals(root2), is(true));
    }




}