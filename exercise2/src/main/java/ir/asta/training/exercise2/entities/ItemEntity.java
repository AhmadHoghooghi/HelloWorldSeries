package ir.asta.training.exercise2.entities;

import javax.persistence.*;


@Entity(name = "WISE_ITEM")
public class ItemEntity {

    private Long id;
    private int quantity;
    private ProductEntity product;
    private OrderEntity shoppingOrder;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "product",nullable = false,foreignKey = @ForeignKey(name = "PRODUCT_FK"))
    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    @Basic
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @ManyToOne
    @JoinColumn(name = "shoppingOrder",nullable = false)
    public OrderEntity getShoppingOrder() {
        return shoppingOrder;
    }

    public void setShoppingOrder(OrderEntity order) {
        this.shoppingOrder = order;
    }

}
