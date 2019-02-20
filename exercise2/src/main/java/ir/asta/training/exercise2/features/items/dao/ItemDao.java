package ir.asta.training.exercise2.features.items.dao;

import ir.asta.training.exercise2.entities.CategoryEntity;
import ir.asta.training.exercise2.entities.ItemEntity;
import ir.asta.training.exercise2.entities.OrderEntity;
import ir.asta.training.exercise2.entities.ProductEntity;
import ir.asta.training.exercise2.utils.UtilMethods;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Named("itemDao")
public class ItemDao {
    @PersistenceContext
    private EntityManager em;

    public List<ItemEntity> search(Map<String, String> searchCriteriaMap) throws ParseException {
        //setup basics
        CriteriaBuilder cb = em.getCriteriaBuilder();

        // create query
        CriteriaQuery<OrderEntity> criteriaQuery = cb.createQuery(OrderEntity.class);
        Root<OrderEntity> order = criteriaQuery.from(OrderEntity.class);

        List<Predicate> predicates = new ArrayList<>();
        // title
        String purchaseTimeFrom = searchCriteriaMap.get("purchaseTimeFrom");
        if (purchaseTimeFrom != null && !"".equals(purchaseTimeFrom)) {
            predicates.add(cb.greaterThanOrEqualTo(order.get("purchaseTime"), UtilMethods.parseToDate(purchaseTimeFrom)));
        }

        // endDateTimeFrom
        String purchaseTimeTo = searchCriteriaMap.get("purchaseTimeTo");
        if (purchaseTimeTo != null && !"".equals(purchaseTimeTo)) {
            predicates.add(cb.lessThanOrEqualTo(order.get("purchaseTime"), UtilMethods.parseToDate(purchaseTimeTo)));
        }

        Predicate[] predicateArray = predicates.stream().toArray(Predicate[]::new);
        CriteriaQuery<OrderEntity> filteredQuery = criteriaQuery.select(order)
                .where(predicateArray);
        //run and get result
        TypedQuery<OrderEntity> typedQuery = em.createQuery(filteredQuery);
        List<OrderEntity> orders = typedQuery.getResultList();
        List<ItemEntity> items = orders.stream().flatMap(o -> o.getItems().stream()).collect(Collectors.toList());
        return items;
    }

    public List<ItemEntity> totalSaleByCategory(String selectedCategory) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ItemEntity> itemQuery = cb.createQuery(ItemEntity.class);
        Root<ItemEntity> item = itemQuery.from(ItemEntity.class);

        Subquery<CategoryEntity> categorySubQuery = itemQuery.subquery(CategoryEntity.class);
        Root<ItemEntity> itemSubRoot = categorySubQuery.correlate(item);
        Join<ItemEntity, ProductEntity> product = itemSubRoot.join("product");
        Join<ProductEntity, CategoryEntity> category = product.join("categories");

        categorySubQuery.select(category);
        categorySubQuery.where(cb.equal(category.get("subject"), selectedCategory));
        itemQuery.where(cb.exists(categorySubQuery));
        TypedQuery<ItemEntity> aTypedQuery = em.createQuery(itemQuery);
        List<ItemEntity> items = aTypedQuery.getResultList();
        return items;
    }
}
