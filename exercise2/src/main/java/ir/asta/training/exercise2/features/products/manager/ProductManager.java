package ir.asta.training.exercise2.features.products.manager;

import ir.asta.training.exercise2.entities.ProductEntity;
import ir.asta.training.exercise2.features.categories.manager.CategoryManager;
import ir.asta.training.exercise2.features.products.dao.ProductDao;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named("productManager")
public class ProductManager {

    @Inject
    private ProductDao productDao;

    @Inject
    private ProductMapper productMapper;

    @Inject
    CategoryManager categoryManager;

    @Transactional
    public void save(ProductDTO productDTO) {
        ProductEntity productEntity = productMapper.toEntity(productDTO);
        save(productEntity);
        productDTO.setId(productEntity.getId());
    }

    @Transactional
    public void save(ProductEntity product) {
        productDao.save(product);
        product.getCategories().forEach(category -> {
            category.addProduct(product);
            categoryManager.update(category);
        });
    }

    @Transactional(readOnly = true)
    public ProductEntity find(Long id) {
        return productDao.find(id);

    }

    @Transactional(readOnly = true)
    public ProductDTO findDTO(Long id) {
        return productMapper.toDTO(find(id));
    }

    @Transactional(readOnly = true)
    public List<ProductEntity> list() {
        return productDao.list();
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> listDTO() {
        return list().
                stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void update(ProductEntity product) {
        productDao.update(product);
    }

    @Transactional
    public void update(ProductDTO productDTO) {
        ProductEntity productEntity = productMapper.toEntity(productDTO);
        update(productEntity);
    }

    @Transactional
    public void delete(Long id) {
        productDao.delete(id);
    }

    @Transactional(readOnly = true)
    public List<ProductEntity> search() {
        return productDao.search();
        //todo implement search method in CategoryManager.
    }


}
