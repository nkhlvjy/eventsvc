package com.intuit.eventsvc.user.entity;

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
@Table(name = "users")
public class User {

    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @JsonProperty("user_id")
    private String userId;

    @Column(nullable = false)
    @JsonProperty("created_at")
    private Timestamp createdAt;

    @Column(nullable = false)
    @JsonProperty("updated_at")
    private Timestamp updatedAt;
}