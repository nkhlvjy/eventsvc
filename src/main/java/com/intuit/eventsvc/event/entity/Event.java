package com.intuit.eventsvc.event.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "events")
public class Event {

    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonProperty("event_name")
    private String eventName;

    @Column(nullable = false)
    @JsonProperty("event_category")
    private String eventCategory;

    @Column(nullable = false)
    @JsonProperty("start_time")
    private Timestamp startTime;

    @Column(nullable = false)
    @JsonProperty("end_time")
    private Timestamp endTime;
}