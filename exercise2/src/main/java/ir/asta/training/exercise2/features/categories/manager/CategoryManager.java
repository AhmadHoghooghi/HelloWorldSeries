package ir.asta.training.exercise2.features.categories.manager;

import ir.asta.training.exercise2.entities.CategoryEntity;
import ir.asta.training.exercise2.entities.ProductEntity;
import ir.asta.training.exercise2.features.categories.dao.CategoryDao;
import ir.asta.training.exercise2.features.products.manager.ProductManager;
import ir.asta.training.exercise2.utils.dtos.CategoryProductRel;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;


@Named("categoryManager")
public class CategoryManager {
    @Inject
    private CategoryDao categoryDao;

    @Inject
    private CategoryMapper categoryMapper;

    @Inject
    private ProductManager productManager;

    @Transactional
    public void save(CategoryEntity category) {
        categoryDao.save(category);
    }

    @Transactional
    public void save(CategoryDTO categoryDTO){
        CategoryEntity categoryEntity = categoryMapper.toEntity(categoryDTO);
        save(categoryEntity);
        categoryDTO.setId(categoryEntity.getId());
    }

    @Transactional(readOnly = true)
    public CategoryEntity find(Long id) {
        return categoryDao.find(id);
    }

    @Transactional(readOnly = true)
    public CategoryDTO findDTO(Long id){
        return categoryMapper.toDto(find(id));
    }

    @Transactional(readOnly = true)
    public List<CategoryEntity> list() {
        return categoryDao.list();
    }

    @Transactional(readOnly = true)
    public List<CategoryDTO> listDTO() {
        return categoryDao.list()
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void update(CategoryEntity category) {
        categoryDao.update(category);
    }

    public void update(CategoryDTO categoryDTO){
        update(categoryMapper.toEntity(categoryDTO));
    }

    @Transactional
    public void delete(Long id) {
        categoryDao.delete(id);
    }

    @Transactional(readOnly = true)
    public List<CategoryEntity> search() {
        return categoryDao.search();
        //todo implement search method in CategoryManager.
    }

    public void setCategoryProductRel(CategoryProductRel rel) {
        CategoryEntity category = find(rel.getCategoryId());
        ProductEntity product = productManager.find(rel.getProductId());

        if(!category.getProducts().contains(product)){
            category.addProduct(product);
        }

        if(!product.getCategories().contains(category)){
            product.addCategory(category);
        }

        update(category);
        productManager.update(product);
    }
}
