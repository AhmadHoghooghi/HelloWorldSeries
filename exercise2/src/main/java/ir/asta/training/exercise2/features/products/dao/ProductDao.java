package ir.asta.training.exercise2.features.products.dao;


import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ir.asta.training.exercise2.entities.ProductEntity;
import ir.asta.training.exercise2.utils.exceptions.EComEntityNotFoundException;

import java.util.List;

@Named("productDao")
public class ProductDao {
    @PersistenceContext
    private EntityManager em;

    public void save(ProductEntity entity) {
        em.persist(entity);
    }

    public ProductEntity find(Long id) {
        ProductEntity entity = em.find(ProductEntity.class, id);
        if (entity == null) {
            throw new EComEntityNotFoundException(id, ProductEntity.class);
        }
        return entity;
    }

    public List<ProductEntity> list() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> criteriaQuery = criteriaBuilder.createQuery(ProductEntity.class);
        Root<ProductEntity> category = criteriaQuery.from(ProductEntity.class);

        CriteriaQuery<ProductEntity> filteredQuery = criteriaQuery.select(category);
        TypedQuery<ProductEntity> typedQuery = em.createQuery(filteredQuery);
        List<ProductEntity> resultList = typedQuery.getResultList();
        return resultList;
    }

    public void update(ProductEntity entity) {
        ProductEntity dbProduct = em.find(ProductEntity.class, entity.getId());
        if (dbProduct == null) {
            throw new EComEntityNotFoundException(entity.getId(), ProductEntity.class);
        }
        em.merge(entity);
    }

    public void delete(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<ProductEntity> delete = cb.createCriteriaDelete(ProductEntity.class);
        Root<ProductEntity> e = delete.from(ProductEntity.class);
        delete.where(cb.equal(e.get("id"), id));
        Query query = em.createQuery(delete);
        int num = query.executeUpdate();
        if (num == 0) {
            throw new EComEntityNotFoundException(id, ProductEntity.class);
        }
    }

    public List<ProductEntity> search() {
        throw new RuntimeException("Not implemented yet");
        //todo implement search method in CategoryDao.
    }
}
