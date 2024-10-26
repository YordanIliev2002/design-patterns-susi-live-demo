package susi.repositories.user;

import susi.entities.User;

public interface UserRepository {
    void save(User user);
    User findByUsername(String username);
}
