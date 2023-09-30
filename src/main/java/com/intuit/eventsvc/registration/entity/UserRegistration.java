package com.intuit.eventsvc.registration.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "user_registrations")
public class UserRegistration {

    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonProperty("event_id")
    private Long eventId;

    @Column(nullable = false)
    @JsonProperty("user_id")
    private Long userId;

    @Column(nullable = false)
    @JsonProperty("created_at")
    private Timestamp created_at;

    @Column(nullable = false)
    @JsonProperty("updated_at")
    private Timestamp updated_at;
}