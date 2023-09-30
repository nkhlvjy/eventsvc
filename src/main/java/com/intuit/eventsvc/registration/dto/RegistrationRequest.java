package com.intuit.eventsvc.registration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class RegistrationRequest {
    @JsonProperty("event_id")
    private Long eventId;

    @JsonProperty("user_id")
    private Long userId;
}
