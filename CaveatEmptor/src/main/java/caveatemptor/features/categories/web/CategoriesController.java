package caveatemptor.features.categories.web;

import caveatemptor.entities.Category;
import caveatemptor.entities.Item;
import caveatemptor.features.categories.services.CategoryService;
import caveatemptor.features.items.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Controller
public class CategoriesController {
    private final ItemService itemService;
    private CategoryService categoriesService;

    @Autowired
    public CategoriesController(CategoryService categoriesService,ItemService itemService) {
        this.categoriesService = categoriesService;
        this.itemService = itemService;
    }

    @RequestMapping(value = {"/navigate-categories"}, method = RequestMethod.GET)
    public String categories(Model model) {
        model.addAttribute("parent", null);
        model.addAttribute("categories", categoriesService.getRootCategories());
        List<Item> allItems = itemService.findAllItemsInCategoryHierarchyWithRoot(null);
        allItems.sort(Comparator.comparing(Item::getName));
        Set<Item> distinctItems = new LinkedHashSet<>(allItems);
        model.addAttribute("items", distinctItems);
        return "categories";
    }

    @RequestMapping(value = {"/navigate-categories/{parentCategoryName:.+}"}, method = RequestMethod.GET)
    public String subCategories(@PathVariable("parentCategoryName") String parentCategoryName, Model model) {
        Category parentCategory = categoriesService.findCategoryByName(parentCategoryName);
        model.addAttribute("parent", parentCategory);
        model.addAttribute("categories", categoriesService.getChildrenOfCategoryWithName(parentCategoryName));

        List<Item> allItems = itemService.findAllItemsInCategoryHierarchyWithRootName(parentCategoryName);
        allItems.sort(Comparator.comparing(Item::getName));
        Set<Item> distinctItems = new LinkedHashSet<>(allItems);
        model.addAttribute("items", distinctItems);
        return "categories";
    }


    @RequestMapping(value = "/add-category", method = RequestMethod.POST)
    public String addCategory(@RequestParam("parent-category-name") String parentCategoryName,
                              @RequestParam("new-category-name") String newCategoryName) {
        if (parentCategoryName == null || "".equals(parentCategoryName)) {
            Category newCategory = categoriesService.create(newCategoryName);
            categoriesService.persist(newCategory);
            return "redirect:/navigate-categories";
        } else {

            Category category = categoriesService.create(newCategoryName, parentCategoryName);
            categoriesService.persist(category);


            return "redirect:/navigate-categories/" + parentCategoryName;
        }
    }

    @RequestMapping(value = "/delete-category/{deleteCategoryName:.+}", method = RequestMethod.POST)
    public String deleteCategory(@PathVariable("deleteCategoryName") String deleteCategoryName,
                                 @RequestParam("parent-category-name") String parentCategoryName) throws Exception {
        categoriesService.deleteCategoryByName(deleteCategoryName);
        if (parentCategoryName == null || "".equals(parentCategoryName)) {
            return "redirect:/navigate-categories/";
        } else {
            return "redirect:/navigate-categories/" + parentCategoryName;
        }
    }

    // :.+ solves problem with old category names containing "."
    @RequestMapping(value = "/edit-category/{oldCategoryName:.+}", method = RequestMethod.POST)
    public String editCategory(@PathVariable("oldCategoryName") String oldName,
                                 @RequestParam("new-category-name") String newName,
                                 @RequestParam("parent-category-name") String parentCategoryName) {
        categoriesService.editCategoryName(oldName, newName);
        if (parentCategoryName == null || "".equals(parentCategoryName)) {
            return "redirect:/navigate-categories/";
        } else {
            return "redirect:/navigate-categories/" + parentCategoryName;
        }
    }
}
