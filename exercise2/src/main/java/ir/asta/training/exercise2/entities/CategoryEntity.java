package ir.asta.training.exercise2.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "WISE_CATEGORY")
public class CategoryEntity {

    private Long id;
    private String subject;
    private List<ProductEntity> products = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToMany
    @JoinTable(
            name = "WISE_CATEGORY_PRODUCT",
            joinColumns = {@JoinColumn(name = "CATEGORY")},
            inverseJoinColumns = {@JoinColumn(name="PRODUCT")}
    )
    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public void addProduct(ProductEntity product){
        products.add(product);
    }

    public void removeProduct(ProductEntity product){
        products.remove(product);
    }
}
