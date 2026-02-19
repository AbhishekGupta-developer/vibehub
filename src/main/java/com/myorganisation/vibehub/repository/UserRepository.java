package com.myorganisation.vibehub.repository;

import com.myorganisation.vibehub.enums.Gender;
import com.myorganisation.vibehub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    // JPQL -> Java Persistence Query Language
    @Query(value = "SELECT u FROM User u WHERE u.name = :n AND u.gender = :g")
    List<User> searchUserByExactNameAndGender(@Param("n") String name, @Param("g") Gender gender);

    @Query(value = "SELECT u FROM User u WHERE u.email LIKE %:domain%")
    List<User> searchUserByEmailDomain(@Param("domain") String domain);

    // Native Query
    @Query(value = "SELECT * FROM users WHERE id = :id", nativeQuery = true)
    Optional<User> searchUserByIdUsingNativeQuery(@Param("id") long id);

    @Query(nativeQuery = true, value = "SELECT * FROM users u WHERE u.gender = :gender")
    List<User> searchUsersByGenderUsingNativeQuery(@Param("gender") String gender);
}
