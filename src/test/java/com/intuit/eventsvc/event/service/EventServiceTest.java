package com.intuit.eventsvc.event.service;

import com.intuit.eventsvc.event.dto.CreateEventRequest;
import com.intuit.eventsvc.event.entity.Event;
import com.intuit.eventsvc.repository.repo.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EventServiceTest {

    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        eventService = new EventService(eventRepository);
    }

    @Test
    public void testSaveEvent() {
        CreateEventRequest request = new CreateEventRequest();
        request.setEventName("Test Event");
        request.setEventCategory("TestCategory");
        Timestamp startTime = new Timestamp(System.currentTimeMillis());
        Timestamp endTime = new Timestamp(System.currentTimeMillis()+10000000);
        request.setStartTime(startTime.toString());
        request.setEndTime(endTime.toString());

        Event savedEvent = Event.builder()
                .eventName(request.getEventName())
                .eventCategory(request.getEventCategory())
                .startTime(Timestamp.valueOf(request.getStartTime()))
                .endTime(Timestamp.valueOf(request.getEndTime()))
                .build();

        when(eventRepository.save(any(Event.class))).thenReturn(savedEvent);

        Event result = eventService.saveEvent(request);

        verify(eventRepository).save(argThat(event -> {
            assertEquals(request.getEventName(), event.getEventName());
            assertEquals(request.getEventCategory(), event.getEventCategory());
            assertEquals(Timestamp.valueOf(request.getStartTime()), event.getStartTime());
            assertEquals(Timestamp.valueOf(request.getEndTime()), event.getEndTime());
            return true;
        }));

        assertEquals(savedEvent, result);
    }

    @Test
    public void testFetchAllEvents() {
        Event event1 = new Event();
        event1.setId(1L);
        event1.setEventName("Event 1");

        Event event2 = new Event();
        event2.setId(2L);
        event2.setEventName("Event 2");

        List<Event> events = Arrays.asList(event1, event2);

        when(eventRepository.findAll()).thenReturn(events);

        List<Event> result = eventService.fetchAllEvents();

        assertEquals(events, result);
    }

    @Test
    public void testFetchEventById() {
        Long eventId = 1L;
        Event event = new Event();
        event.setId(eventId);
        event.setEventName("Test Event");

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        Event result = eventService.fetchEventById(eventId);

        assertEquals(event, result);
    }

    @Test
    public void testFetchEventByIdNotFound() {
        Long eventId = 1L;

        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

        Event result = eventService.fetchEventById(eventId);

        assertEquals(null, result);
    }
}
