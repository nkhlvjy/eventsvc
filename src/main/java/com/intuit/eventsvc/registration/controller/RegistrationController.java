package com.intuit.eventsvc.registration.controller;

import com.intuit.eventsvc.event.entity.Event;
import com.intuit.eventsvc.registration.dto.RegistrationRequest;
import com.intuit.eventsvc.registration.entity.UserRegistration;
import com.intuit.eventsvc.registration.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService)
    {
        this.registrationService = registrationService;
    }

    @PostMapping("/registration")
    public ResponseEntity<UserRegistration> register(@RequestBody RegistrationRequest request) {
        UserRegistration registration = registrationService.register(request);
        if(registration == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(registration);
    }

    @PostMapping("/deregistration")
    public ResponseEntity<Void> deregister(@RequestBody RegistrationRequest request) {
        registrationService.deregister(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/registered-events/{user-id}")
    public ResponseEntity<List<Event>> getRegisteredEventsByUserId(@PathVariable("user-id") Long userId) {
        List<Event> events = registrationService.getRegisteredEventsByUserId(userId);
        return ResponseEntity.ok(events);
    }
}
