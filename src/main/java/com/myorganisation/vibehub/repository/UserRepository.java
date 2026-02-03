package com.myorganisation.vibehub.repository;

import com.myorganisation.vibehub.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
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
}
