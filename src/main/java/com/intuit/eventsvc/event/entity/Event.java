package com.intuit.eventsvc.event.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "events")
public class Event {

    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonProperty("event_name")
    private String event_name;

    @Column(nullable = false)
    @JsonProperty("event_category")
    private String event_category;

    @Column(nullable = false)
    @JsonProperty("start_time")
    private Timestamp start_time;

    @Column(nullable = false)
    @JsonProperty("end_time")
    private Timestamp end_time;
}