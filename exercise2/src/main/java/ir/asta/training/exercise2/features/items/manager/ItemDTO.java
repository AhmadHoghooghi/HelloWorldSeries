package ir.asta.training.exercise2.features.items.manager;

public class ItemDTO {
    private Long id;
    private int quantity;
    private Long productId;
    // private Long orderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    // public Long getOrderId() {
    //     return orderId;
    // }
    //
    // public void setOrderId(Long orderId) {
    //     this.orderId = orderId;
    // }
}
