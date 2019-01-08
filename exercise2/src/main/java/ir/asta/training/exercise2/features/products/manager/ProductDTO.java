package ir.asta.training.exercise2.features.products.manager;

import ir.asta.training.exercise2.entities.CategoryEntity;
import ir.asta.training.exercise2.entities.ProductEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDTO {
    private Long id;
    private String code;
    List<Long> categoriesIdList=new ArrayList<>();

    public ProductDTO() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Long> getCategoriesIdList() {
        return categoriesIdList;
    }

    public void setCategoriesIdList(List<Long> categoriesIdList) {
        this.categoriesIdList = categoriesIdList;
    }
}
