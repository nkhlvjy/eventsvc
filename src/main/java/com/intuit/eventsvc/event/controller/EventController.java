package com.intuit.eventsvc.event.controller;

import com.intuit.eventsvc.event.dto.CreateEventRequest;
import com.intuit.eventsvc.event.entity.Event;
import com.intuit.eventsvc.event.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService)
    {
        this.eventService = eventService;
    }


    @PostMapping("/events")
    public ResponseEntity<Event> saveEvent(@RequestBody CreateEventRequest request)
    {
        Event newEvent = eventService.saveEvent(request);
        return ResponseEntity.ok(newEvent);
    }

    // Get all events
    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllProducts()
    {
        return ResponseEntity.ok(eventService.fetchAllEvents());
    }

    // Get a event by ID
    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getProductById(@PathVariable Long id)
    {
        Event product = eventService.fetchEventById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
