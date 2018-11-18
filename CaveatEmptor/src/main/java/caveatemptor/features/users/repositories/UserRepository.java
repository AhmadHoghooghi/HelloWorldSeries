package caveatemptor.features.users.repositories;

import caveatemptor.entities.User;

public interface UserRepository {

    void persist(User user);
    User findByUserName(String userName);
}
