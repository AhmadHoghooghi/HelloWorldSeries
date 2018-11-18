package caveatemptor.entities;

import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Check(constraints = "(sold=false) or (sold = true and biddable =false)")
public class Item {
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR_NAME)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal initialPrice;

    @Column(nullable = false)
    private LocalDateTime auctionEnd;

    @Column(nullable = false)
    private boolean biddable;

    @Column(nullable = false)
    private boolean sold;

    @ElementCollection
    @CollectionTable(name = "IMAGE")
    @AttributeOverrides({
            @AttributeOverride(
                    name = "fileName",
                    column = @Column(name = "FNAME", nullable = false)
            )})

    private Set<Image> images = new HashSet<>();

    @OneToMany(mappedBy = "item")
    private Set<Bid> bids = new HashSet<>();

    @ManyToOne(optional = false)
    private User seller;

    @ManyToMany(mappedBy = "items")
    private Set<Category> categories = new HashSet<>();

    protected Item() {
    }

    public Item(String name, LocalDateTime auctionEnd, User seller, Category category, BigDecimal initialPrice) {
        this.name = name;
        this.auctionEnd = auctionEnd;
        this.seller = seller;
        categories.add(category);
        this.initialPrice = initialPrice;
        this.biddable = true;
        this.sold = false;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getInitialPrice() {
        return initialPrice;
    }

    public LocalDateTime getAuctionEnd() {
        return auctionEnd;
    }

    public void setAuctionEnd(LocalDateTime newEndTime) throws Exception {
        if (newEndTime.isBefore(LocalDateTime.now())) {
            throw new Exception("New auction end time should be a point in the future");
        }
        auctionEnd = newEndTime;
    }

    public Set<Image> getImages() {
        return images;
    }

    public Set<Bid> getBids() {
        return bids;
    }

    public User getSeller() {
        return seller;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public boolean isBiddable() {
        return biddable;
    }

    public void setBiddable(boolean newBiddableValue) throws Exception {
        boolean requestToMakeSoldItemAsBiddableIsArrived = newBiddableValue == true && this.isSold();
        if (requestToMakeSoldItemAsBiddableIsArrived) {
            throw new Exception("It's not possible to make sold item biddable");
        }

        boolean auctionEndHasBeenPassed = LocalDateTime.now().isAfter(auctionEnd);
        if (newBiddableValue == true && auctionEndHasBeenPassed) {
            throw new Exception("Auction time for this item is finished and" +
                    " it's not possible to make it biddable");
        }
        this.biddable = newBiddableValue;
    }

    public boolean isSold() {
        return sold;
    }

    public void setAsSold() {
        try {
            setBiddable(false);
        } catch (Exception e) {
        }
        this.sold = true;
    }

    public void addCategory(Category newCategory) throws Exception {
        if (isParentOfOneOfItemCategoriesInCategoryList(newCategory)) {
            throw new Exception("Sub category of this category is " +
                    "In Category list of item and this addition is redundant");
        } else {
            categories.add(newCategory);
        }
    }

    private boolean isParentOfOneOfItemCategoriesInCategoryList(Category newCategory) {
        boolean newCategoryIsParentOfOneCategoryInCategoryList = false;
        for (Category category : getCategories()) {
            Category categoryHierarchyIndex = category;
            while(!categoryHierarchyIndex.isRoot()){
                if(categoryHierarchyIndex.getParent().equals(newCategory)){
                    newCategoryIsParentOfOneCategoryInCategoryList = true;
                    break;
                }
                categoryHierarchyIndex = categoryHierarchyIndex.getParent();
            }

            if(newCategoryIsParentOfOneCategoryInCategoryList){
                break;
            }
        }
        return newCategoryIsParentOfOneCategoryInCategoryList;
    }

    public void removeCategory(Category category) throws Exception {
        if(!categories.contains(category)){
            return;
        }
        boolean thisIsTheOnlyCategoryInCategorySet = categories.size() == 1 && categories.contains(category);
        if (thisIsTheOnlyCategoryInCategorySet) {
            throw new Exception("each item should have one category at least");
        } else {
            categories.remove(category);
        }
    }

    public void addImage(Image image){
        images.add(image);
    }

    public void removeImage(Image image){
        images.remove(image);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        if (initialPrice != null ? !initialPrice.equals(item.initialPrice) : item.initialPrice != null) return false;
        if (auctionEnd != null ? !auctionEnd.equals(item.auctionEnd) : item.auctionEnd != null) return false;
        return seller != null ? seller.getUserName().equals(item.seller.getUserName()) : item.seller == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (initialPrice != null ? initialPrice.hashCode() : 0);
        result = 31 * result + (auctionEnd != null ? auctionEnd.hashCode() : 0);
        result = 31 * result + (seller != null ? seller.getUserName().hashCode() : 0);
        return result;
    }
}
