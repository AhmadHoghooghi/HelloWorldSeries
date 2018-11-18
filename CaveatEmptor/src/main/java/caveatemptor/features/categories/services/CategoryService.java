package caveatemptor.features.categories.services;

import caveatemptor.entities.Category;
import caveatemptor.entities.Item;

import java.util.List;

public interface CategoryService {
    Category create(String name);
    Category create(String name, Category parent);
    Category create(String name, String parentName);

    void persist(Category category);

    void update(Category category);
    List<Category> getRootCategories();

    void deleteCategoryByName(String name) throws Exception;

    Category findCategoryByName(String name);

    List<Category> getChildrenOfCategoryWithName(String name);

    void editCategoryName(String oldName, String newName);

    List<Item> findItemsInCategory(Category category);
}
