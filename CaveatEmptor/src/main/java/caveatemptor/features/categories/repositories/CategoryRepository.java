package caveatemptor.features.categories.repositories;

import caveatemptor.entities.Category;

import java.util.List;

public interface CategoryRepository {
    void persist(String name, Category parent);
    void persist(Category category);
    void update(Category category);

    List<Category> getRootCategories();

    Category findByName(String name);
    Category fullyInitializedFindByName(String name);

    List<Category> getChildrenOfCategoryWithName(String name);

    Long getChildrenNumberOfCategoryWithName(String name);

    void deleteCategoryByName(String name) throws Exception;

    void editCategoryName(String oldName, String newName);
}
