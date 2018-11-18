package caveatemptor.features.categories.repositories;

import caveatemptor.entities.Category;
import caveatemptor.features.categories.services.CategoryService;
import caveatemptor.features.items.repositories.ItemRepository;
import caveatemptor.features.items.services.ItemService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class CategoryRepositoryImp implements CategoryRepository {



    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Category> getRootCategories() {
        String query = "SELECT category  FROM Category AS category WHERE category.parent IS NULL ";
        return em.createQuery(query, Category.class).getResultList();
    }

    @Override
    public Category findByName(String name) {
        TypedQuery<Category> query = em.createQuery("SELECT category FROM Category AS category WHERE category.name= (:name) ", Category.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public Category fullyInitializedFindByName(String name) {
        TypedQuery<Category> query = em.createQuery(
                "SELECT category FROM Category AS category " +
                        "LEFT JOIN FETCH category.children " +
                        "WHERE category.name= lower(:name) ", Category.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public List<Category> getChildrenOfCategoryWithName(String name) {
        TypedQuery<Category> query = em.createQuery("SELECT category FROM Category AS category WHERE category.parent.name = :name", Category.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public Long getChildrenNumberOfCategoryWithName(String name) {
        Query query = em.createQuery("select count(category) from Category as category where category.parent.name=:name");
        query.setParameter("name", name);
        return (Long) query.getSingleResult();
    }

    @Override
    public void deleteCategoryByName(String name) throws Exception {

        Query query = em.createQuery("DELETE FROM Category AS category WHERE category.name = :name");
        query.setParameter("name", name);
        query.executeUpdate();


    }

    @Override
    public void persist(String name, Category parent) {
        Hibernate.initialize(parent);
        Category category = new Category(name, parent);
        em.persist(category);

    }

    @Override
    public void persist(Category category) {
        em.persist(category);
    }

    @Override
    public void update(Category category) {
        em.merge(category);
    }

    @Override
    public void editCategoryName(String oldName, String newName) {
        Category category = findByName(oldName);
        category.setName(newName);
    }
}
