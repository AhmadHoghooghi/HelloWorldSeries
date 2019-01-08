package ir.asta.training.exercise2.features.categories.manager;

import java.util.ArrayList;
import java.util.List;

public class CategoryDTO {
    private Long id;
    private String subject;
    private List<Long> productsIdList = new ArrayList<>();

    public CategoryDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Long> getProductsIdList() {
        return productsIdList;
    }

    public void setProductsIdList(List<Long> productsIdList) {
        this.productsIdList = productsIdList;
    }
}
