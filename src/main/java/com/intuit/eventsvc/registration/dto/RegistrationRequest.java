package com.intuit.eventsvc.registration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    @JsonProperty("event_id")
    private Long eventId;

    @JsonProperty("user_id")
    private Long userId;
}
