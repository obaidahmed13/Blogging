package genspark.blogging.Blogging.services;

import genspark.blogging.Blogging.models.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    User findByUsername(String username);
    User createUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    String deleteUser(Long id);
}
