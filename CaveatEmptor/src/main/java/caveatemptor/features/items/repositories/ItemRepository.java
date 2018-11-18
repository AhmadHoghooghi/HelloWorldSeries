package caveatemptor.features.items.repositories;

import caveatemptor.entities.Item;

import java.util.List;

public interface ItemRepository {
    void persist(Item item);
    void update(Item item);
    void remove(Item item);
    Item find(Long itemId);


}
