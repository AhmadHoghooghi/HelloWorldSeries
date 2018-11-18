package caveatemptor.entities;

import javax.persistence.*;
import java.util.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR_NAME)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(optional = true)
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> children = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "CATEGORY_ITEM",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
    )
    private Set<Item> items = new HashSet<>();

    protected Category() {
    }

    public Category(String name) {
        this.parent = null;
        this.name = name;
    }

    public Category(String name, Category parent) {
        this.name = name;
        if (parent != null) {
            parent.getChildren().add(this);
        }
        this.parent = parent;
    }

    public void delete() {
        Category parent = getParent();
        if (parent != null) {
            parent.getChildren().remove(this);
        }
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public List<Category> getUnModifiableChildren() {
        return Collections.unmodifiableList(children);
    }

    public Set<Item> getItems() {
        return items;
    }

    public void addItem(Item item) throws Exception {
        if (!item.getCategories().contains(this)){
            throw new Exception("Add this category to category list of item, before this operation");
        }
        items.add(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return this.name.equalsIgnoreCase(category.getName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    public boolean isRoot() {
        return (parent == null);
    }

    protected List<Category> getChildren() {
        return children;
    }

    public void removeItem(Item item) throws Exception {
        if (item.getCategories().contains(this)){
            throw new Exception("Remove this category from category list of item, before this operation");
        }
        items.remove(item);
    }
}
