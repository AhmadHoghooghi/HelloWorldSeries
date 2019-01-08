package ir.asta.training.exercise2.features.categories.manager;

import ir.asta.training.exercise2.entities.CategoryEntity;
import ir.asta.training.exercise2.features.products.manager.ProductManager;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.stream.Collectors;
@Named("categoryMapper")
public class CategoryMapper {

    @Inject
    ProductManager productManager;

    public CategoryEntity toEntity(CategoryDTO dto) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(dto.getId());
        categoryEntity.setSubject(dto.getSubject());
        categoryEntity.setProducts(
                dto.getProductsIdList()
                        .stream()
                        .map(id -> productManager.find(id))
                        .collect(Collectors.toList())
        );
        return categoryEntity;
    }

    public CategoryDTO toDto(CategoryEntity entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setSubject(entity.getSubject());
        dto.setProductsIdList(
                entity.getProducts()
                .stream()
                .map(product -> product.getId())
                .collect(Collectors.toList()));
        return dto;
    }


}
