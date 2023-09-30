package com.intuit.eventsvc.registration.service;

import com.intuit.eventsvc.event.entity.Event;
import com.intuit.eventsvc.event.service.EventService;
import com.intuit.eventsvc.registration.dto.RegistrationRequest;
import com.intuit.eventsvc.registration.entity.UserRegistration;
import com.intuit.eventsvc.repository.repo.UserRegistrationRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class RegistrationService {
    private final UserRegistrationRepository userRegistrationRepository;
    private final EventService eventService;

    public RegistrationService(UserRegistrationRepository userRegistrationRepository, EventService eventService) {
        this.userRegistrationRepository = userRegistrationRepository;
        this.eventService = eventService;
    }


    public UserRegistration register(RegistrationRequest request) {
        List<UserRegistration> registrations = userRegistrationRepository.findByUserIdAndEventId(request.getUserId(), request.getEventId());
        if(registrations.isEmpty()) {
            return userRegistrationRepository.save(UserRegistration.builder()
                    .eventId(request.getEventId())
                    .userId(request.getUserId())
                    .created_at(new Timestamp(System.currentTimeMillis()))
                    .updated_at(new Timestamp(System.currentTimeMillis()))
                    .build());
        }
        return null;
    }


    // Get all events
    public void deregister(Long id) {
        userRegistrationRepository.deleteById(id);
    }

    public List<Event> getRegisteredEventsByUserId(Long userId) {
        return userRegistrationRepository
                .findByUserId(userId)
                .stream()
                .map(UserRegistration::getEventId)
                .toList()
                .stream()
                .map(eventService::fetchEventById)
                .toList();
    }
}
