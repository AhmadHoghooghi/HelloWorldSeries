package ir.asta.training.exercise2.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "WISE_PRODUCT")
public class ProductEntity {

    private Long id;
    private String code;
    List<CategoryEntity> categories=new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToMany(mappedBy = "products")
    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }

    public void addCategory(CategoryEntity category){
        categories.add(category);
    }

    public void removeCategory(CategoryEntity category){
        categories.remove(category);
    }
}
