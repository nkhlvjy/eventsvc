package com.intuit.eventsvc.event.service;

import com.intuit.eventsvc.event.dto.CreateEventRequest;
import com.intuit.eventsvc.event.entity.Event;
import com.intuit.eventsvc.repository.repo.EventRepository;
import org.springframework.stereotype.Service;

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
                .eventName(request.getEventName())
                .eventCategory(request.getEventCategory())
                .startTime(Timestamp.valueOf(request.getStartTime()))
                .endTime(Timestamp.valueOf(request.getEndTime()))
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
