package com.myorganisation.vibehub.repository;

import com.myorganisation.vibehub.enums.Gender;
import com.myorganisation.vibehub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Custom finder methods
    // find user by username
    Optional<User> findByUserName(String userName);

    List<User> findByNameContaining(String name);

    List<User> findByNameContainingAndGender(String name, Gender gender);
}
