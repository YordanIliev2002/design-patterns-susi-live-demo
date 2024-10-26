package susi.repositories.user;

import susi.entities.User;

import java.util.HashMap;

public class HashMapUserRepository implements UserRepository {
    private final HashMap<String, User> storage = new HashMap<>();

    @Override
    public void save(User user) {
        storage.put(user.username(), user);
    }

    @Override
    public User findByUsername(String username) {
        return storage.get(username);
    }
}
