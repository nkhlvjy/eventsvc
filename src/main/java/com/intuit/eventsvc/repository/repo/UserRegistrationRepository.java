package com.intuit.eventsvc.repository.repo;

import com.intuit.eventsvc.registration.entity.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Long> {
    @Query("SELECT ur FROM UserRegistration ur WHERE ur.userId = :desiredValue")
    List<UserRegistration> findByUserId(@Param("desiredValue") Long desiredValue);

    @Query("SELECT ur FROM UserRegistration ur WHERE ur.userId = :desiredUserIdValue and ur.eventId = :desiredEventIdValue")
    List<UserRegistration> findByUserIdAndEventId(@Param("desiredUserIdValue") Long desiredUserIdValue, @Param("desiredEventIdValue") Long desiredEventIdValue);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserRegistration ur WHERE ur.userId = :userIdValue and ur.eventId = :eventIdValue")
    void deleteByUserIdAndEventId(@Param("userIdValue") Long desiredUserIdValue, @Param("eventIdValue") Long desiredEventIdValue);
}