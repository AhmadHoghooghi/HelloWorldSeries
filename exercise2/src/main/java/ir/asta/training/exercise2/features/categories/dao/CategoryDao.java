package ir.asta.training.exercise2.features.categories.dao;

import ir.asta.training.exercise2.entities.CategoryEntity;
import ir.asta.training.exercise2.features.categories.manager.CategoryManager;
import ir.asta.training.exercise2.utils.exceptions.EComEntityNotFoundException;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Named("categoryDao")
public class CategoryDao {

    @PersistenceContext
    private EntityManager em;

    public void save(CategoryEntity entity) {
        em.persist(entity);
    }

    public CategoryEntity find(Long id) {
        CategoryEntity entity = em.find(CategoryEntity.class, id);
        if (entity == null) {
            throw new EComEntityNotFoundException(id, CategoryEntity.class);
        }
        return entity;
    }

    public List<CategoryEntity> list() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<CategoryEntity> criteriaQuery = criteriaBuilder.createQuery(CategoryEntity.class);
        Root<CategoryEntity> category = criteriaQuery.from(CategoryEntity.class);

        CriteriaQuery<CategoryEntity> filteredQuery = criteriaQuery.select(category);
        TypedQuery<CategoryEntity> typedQuery = em.createQuery(filteredQuery);
        List<CategoryEntity> resultList = typedQuery.getResultList();
        return resultList;
    }

    public void update(CategoryEntity entity) {
        CategoryEntity dbEntity = em.find(CategoryEntity.class, entity.getId());
        if(dbEntity==null){
            throw new EComEntityNotFoundException(entity.getId(),CategoryEntity.class);
        }
        // updateCategoryProductsHappyPath throws Unsupported operation with em.merge(entity);
        em.unwrap(Session.class).update(entity);
    }

    public void delete(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<CategoryEntity> delete = cb.createCriteriaDelete(CategoryEntity.class);
        Root<CategoryEntity> e = delete.from(CategoryEntity.class);
        delete.where(cb.equal(e.get("id"), id));
        Query query = em.createQuery(delete);
        int num = query.executeUpdate();
        if (num == 0) {
            throw new EComEntityNotFoundException(id, CategoryEntity.class);
        }
    }

    public List<CategoryEntity> search() {
        throw new RuntimeException("Not implemented yet");
        //todo implement search method in CategoryDao.
    }

}
