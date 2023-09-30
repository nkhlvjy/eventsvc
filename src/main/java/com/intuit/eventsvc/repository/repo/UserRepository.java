package com.intuit.eventsvc.repository.repo;

import com.intuit.eventsvc.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.userId = :desiredValue")
    List<User> findByUserId(@Param("desiredValue") String desiredValue);
}
