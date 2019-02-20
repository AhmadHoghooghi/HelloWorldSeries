package ir.asta.training.exercise2.utils.dtos;

public class CategoryProductRel {
    private Long categoryId;
    private Long ProductId;

    public CategoryProductRel() {
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getProductId() {
        return ProductId;
    }

    public void setProductId(Long productId) {
        ProductId = productId;
    }
}
