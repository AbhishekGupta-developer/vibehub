package com.myorganisation.vibehub.repository;

import com.myorganisation.vibehub.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    // Dummy Database
    private Map<Long, User> users = new HashMap<>();

    private static Long currentId = 0L;

    public User addUser(User user) {
        Long id = ++currentId;
        user.setId(id);

        users.put(id, user);

        return users.get(id);
    }

    public User getUser(Long id) {
        return users.get(id);
    }

    public List<User> getAllUsers() {
        return new LinkedList<>(users.values());
    }

    public User updateUser(User user) {
        if(user.getId() != null) {
            users.put(user.getId(), user);
        }

        return users.get(user.getId());
    }

    public void removeUser(Long id) {
        users.remove(id);
    }
}
