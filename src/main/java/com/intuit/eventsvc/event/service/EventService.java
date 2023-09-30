package com.intuit.eventsvc.event.service;

import com.intuit.eventsvc.event.dto.CreateEventRequest;
import com.intuit.eventsvc.event.entity.Event;
import com.intuit.eventsvc.repository.repo.EventRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    public Event saveEvent(CreateEventRequest request) {
        return eventRepository.save(Event.builder()
                .event_name(request.getEventName())
                .event_category(request.getEventCategory())
                .start_time(Timestamp.valueOf(request.getStartTime()))
                .end_time(Timestamp.valueOf(request.getEndTime()))
                .build());
    }


    // Get all events
    public List<Event> fetchAllEvents() {
        return eventRepository.findAll();
    }

    public Event fetchEventById(Long id) {
        Optional<Event> event
                = eventRepository.findById(id);
        return event.orElse(null);
    }
}
