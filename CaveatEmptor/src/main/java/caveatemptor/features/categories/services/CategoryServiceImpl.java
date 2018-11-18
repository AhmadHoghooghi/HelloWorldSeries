package caveatemptor.features.categories.services;

import caveatemptor.entities.Category;
import caveatemptor.entities.Item;
import caveatemptor.features.categories.repositories.CategoryRepository;
import caveatemptor.features.items.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemService itemService;


    @Override
    public List<Category> getRootCategories() {
        return categoryRepository.getRootCategories();
    }

    @Override
    public void deleteCategoryByName(String name) throws Exception {
        boolean categoryHasNotChild = categoryRepository.getChildrenNumberOfCategoryWithName(name).equals(0L);
        int itemNum = itemService.countAllItemsInCategoryHierarchyWithRoot(categoryRepository.findByName(name));
        boolean categoryHasNotLinkedItems = itemNum == 0;
        if (categoryHasNotChild && categoryHasNotLinkedItems) {
            categoryRepository.deleteCategoryByName(name);
        } else {
            throw new Exception("Category Can Not Be Deleted, remove sub items and linked items before delete");
        }
    }


    @Override
    public void persist(Category category) {
        categoryRepository.persist(category);
    }

    @Override
    public void update(Category category) {
        categoryRepository.update(category);
    }

    @Override
    public Category create(String name) {
        return new Category(name);
    }

    @Override
    public Category create(String name, Category paretn) {
        return new Category(name, paretn);
    }

    @Override
    public Category create(String name, String parentName) {
        Category parentCategory = categoryRepository.fullyInitializedFindByName(parentName);
        if (parentCategory == null) {
            throw new RuntimeException("wrong parent category name");
        }
        return new Category(name, parentCategory);
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getChildrenOfCategoryWithName(String name) {
        return categoryRepository.getChildrenOfCategoryWithName(name);
    }

    @Override
    public void editCategoryName(String oldName, String newName) {
        categoryRepository.editCategoryName(oldName, newName);
    }

    @Override
    public List<Item> findItemsInCategory(Category category) {
        return new ArrayList<>(category.getItems());
    }
}
