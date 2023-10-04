package com.intuit.eventsvc.registration.service;

import com.intuit.eventsvc.event.service.EventService;
import com.intuit.eventsvc.registration.dto.RegistrationRequest;
import com.intuit.eventsvc.registration.entity.UserRegistration;
import com.intuit.eventsvc.repository.repo.UserRegistrationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RegistrationServiceTest {

    private RegistrationService registrationService;

    @Mock
    private UserRegistrationRepository userRegistrationRepository;

    @Mock
    private EventService eventService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        registrationService = new RegistrationService(userRegistrationRepository, eventService);
    }

    @Test
    public void testRegisterUserSuccessfully() {
        RegistrationRequest request = new RegistrationRequest(1L, 1L);

        when(userRegistrationRepository.findByUserIdAndEventId(request.getUserId(), request.getEventId()))
                .thenReturn(Collections.emptyList());

        UserRegistration savedRegistration = UserRegistration.builder()
                .eventId(request.getEventId())
                .userId(request.getUserId())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .updatedAt(new Timestamp(System.currentTimeMillis()))
                .build();
        when(userRegistrationRepository.save(any(UserRegistration.class)))
                .thenReturn(savedRegistration);

        UserRegistration result = registrationService.register(request);

        verify(userRegistrationRepository).save(argThat(registration -> {
            assertEquals(request.getEventId(), registration.getEventId());
            assertEquals(request.getUserId(), registration.getUserId());
            return true;
        }));

        assertEquals(savedRegistration, result);
    }

    @Test
    public void testDeregisterUser() {
        RegistrationRequest request = new RegistrationRequest(1L, 1L);

        registrationService.deregister(request);

        verify(userRegistrationRepository).deleteByUserIdAndEventId(request.getUserId(), request.getEventId());
    }
}
