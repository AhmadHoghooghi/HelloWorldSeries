package caveatemptor.features.users.services;

import caveatemptor.entities.User;
import caveatemptor.features.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void persist(User user) {
        userRepository.persist(user);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User create(String userName, String firstName, String lastName) {
        return new User(userName, firstName, lastName, Collections.emptyList());
    }
}
