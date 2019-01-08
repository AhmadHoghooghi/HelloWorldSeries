package ir.asta.training.exercise2.features.categories.services.impl;

import ir.asta.training.exercise2.entities.CategoryEntity;
import ir.asta.training.exercise2.features.categories.manager.CategoryDTO;
import ir.asta.training.exercise2.features.categories.manager.CategoryManager;
import ir.asta.training.exercise2.features.categories.services.CategoryService;
import ir.asta.training.exercise2.utils.dtos.CategoryProductRel;
import ir.asta.wise.core.datamanagement.ActionResult;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Named("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Inject
    private CategoryManager categoryManager;
    @Override
    public ActionResult<Long> saveOrUpdate(CategoryDTO entity) {
        ActionResult<Long> response;
        if (entity.getId() == null) {
            categoryManager.save(entity);
            response = new ActionResult<>(true, "موضوع جدید با موفقیت ذخیره شد.", entity.getId());
        } else {
            categoryManager.update(entity);
            response = new ActionResult<>("موضوع با موفقیت ویرایش شد.", entity.getId());
        }
        return response;
    }

    @Override
    public CategoryDTO load(Long id) {
        return categoryManager.findDTO(id);
    }

    @Override
    public List<CategoryDTO> list() {
        return  categoryManager.listDTO();
    }

    @Override
    public ActionResult<Long> delete(Long id) {
        categoryManager.delete(id);
        return new ActionResult<>("موضوع با موفقیت حذف شد.",id);
    }

    @Override
    public List<CategoryEntity> search(HttpServletRequest httpServletRequest) {
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        Map<String, String> searchCriteriaMap = new HashMap<>();
        parameterMap.keySet().forEach(key -> {
            searchCriteriaMap.put(key, extractParamFromMap(key, parameterMap));
        });

        throw new RuntimeException("not implemented yet");
        //return categoryManager.search(searchCriteriaMap);
        // return null;
    }

    @Override
    public ActionResult<CategoryProductRel> setCategoryProductRel(CategoryProductRel rel) {
        categoryManager.setCategoryProductRel(rel);
        return new ActionResult<>("رابطه با موفقیت ذخیره شده.", rel);
    }

    private String extractParamFromMap(String name, Map<String, String[]> parameterMap) {
        return parameterMap.get(name) == null ? null : parameterMap.get(name)[0];
    }
}
