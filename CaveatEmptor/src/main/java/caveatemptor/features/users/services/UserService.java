package caveatemptor.features.users.services;

import caveatemptor.entities.User;

public interface UserService {
    User create(String userName, String firstName, String lastName);
    void persist(User user);
    User findByUserName(String UserName);
}
