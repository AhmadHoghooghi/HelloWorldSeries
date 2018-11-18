package caveatemptor.features.categories.web;

import caveatemptor.configs.RootConfig;
import caveatemptor.configs.WebConfig;
import caveatemptor.entities.Category;
import caveatemptor.features.categories.services.CategoryService;
import caveatemptor.features.categories.services.CategoryServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, RootConfig.class})
@WebAppConfiguration
@ActiveProfiles("test")
public class CategoriesControllerTest {

    @Test
    public void testNavigateRootCategories() throws Exception {
        CategoryService mockService = mock(CategoryServiceImpl.class);

        when(mockService.getRootCategories()).thenReturn(new ArrayList<>());
        CategoriesController categoriesController = new CategoriesController(mockService,null);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(categoriesController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/navigate-categories"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("categories"))
                .andExpect(MockMvcResultMatchers.view().name("categories"));
    }

    @Test
    public void testNavigateSubCategories() throws Exception {
        Category one = new Category("one");
        Category child = new Category("1.1", one);

        CategoryService mockService = mock(CategoryServiceImpl.class);
        when(mockService.getChildrenOfCategoryWithName("one")).thenReturn(Arrays.asList(child));
        when(mockService.findCategoryByName("one")).thenReturn(one);

        CategoriesController categoriesController = new CategoriesController(mockService,null);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(categoriesController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/navigate-categories/one"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("parent"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("categories"))
                .andExpect(MockMvcResultMatchers.view().name("categories"));
    }

    @Test
    public void addRootCategory() throws Exception {
        CategoryService mockService = mock(CategoryService.class);
        CategoriesController categoriesController = new CategoriesController(mockService,null);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(categoriesController).build();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/add-category")
                        .param("parent-category-name", "")
                        .param("new-category-name", "newCategory")
        )
                .andExpect(redirectedUrl("/navigate-categories"));
    }

    @Test
    public void addSubCategory() throws Exception {
        CategoryService mockService = mock(CategoryServiceImpl.class);
        CategoriesController categoriesController = new CategoriesController(mockService,null);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(categoriesController).build();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/add-category/")
                        .param("new-category-name", "newCategory")
                        .param("parent-category-name", "parentCategory")
        )
                .andExpect(redirectedUrl("/navigate-categories/parentCategory"));
    }
}