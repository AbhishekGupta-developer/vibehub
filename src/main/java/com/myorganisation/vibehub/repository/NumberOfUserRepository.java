package com.myorganisation.vibehub.repository;

import com.myorganisation.vibehub.model.NumberOfUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumberOfUserRepository extends JpaRepository<NumberOfUser, Long> {
}
