package caveatemptor.features.items.repositories;

import caveatemptor.entities.Item;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class ItemRepositoryImpl implements ItemRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void persist(Item item) {
        em.persist(item);
    }

    @Override
    public void update(Item item) {
        em.merge(item);
    }

    @Override
    public void remove(Item item) {
        em.remove(item);
    }


    @Override
    public Item find(Long itemId) {
        return em.find(Item.class, itemId);
    }
}
