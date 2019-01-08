package ir.asta.training.exercise2.features.products.manager;

import ir.asta.training.exercise2.entities.CategoryEntity;
import ir.asta.training.exercise2.entities.ProductEntity;
import ir.asta.training.exercise2.features.categories.manager.CategoryManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.stream.Collectors;

@Named("productMapper")
public class ProductMapper {
    @Inject
    private CategoryManager categoryManager;

    public ProductEntity toEntity(ProductDTO dto) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(dto.getId());
        productEntity.setCode(dto.getCode());
        productEntity.setCategories(
                dto.getCategoriesIdList()
                        .stream()
                        .map(id -> categoryManager.find(id))
                        .collect(Collectors.toList())
        );
        return productEntity;
    }

    public ProductDTO toDTO(ProductEntity productEntity) {
        ProductDTO dto = new ProductDTO();
        dto.setId(productEntity.getId());
        dto.setCode(productEntity.getCode());
        dto.setCategoriesIdList(productEntity.getCategories()
                .stream()
                .map(CategoryEntity::getId)
                .collect(Collectors.toList())
        );
        return dto;
    }
}
