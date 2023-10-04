package com.intuit.eventsvc.user.service;

import com.intuit.eventsvc.repository.repo.UserRepository;
import com.intuit.eventsvc.user.dto.UserCreationRequest;
import com.intuit.eventsvc.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void testCreateUser() {
        UserCreationRequest request = new UserCreationRequest();
        request.setUserId("testUser");

        User savedUser = User.builder()
                .userId(request.getUserId())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .updatedAt(new Timestamp(System.currentTimeMillis()))
                .build();

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User result = userService.createUser(request);

        verify(userRepository).save(argThat(user -> {
            assertEquals(request.getUserId(), user.getUserId());
            return true;
        }));

        assertEquals(savedUser, result);
    }

    @Test
    public void testGetUserByUserId() {
        String userId = "testUser";
        User user = User.builder()
                .userId(userId)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .updatedAt(new Timestamp(System.currentTimeMillis()))
                .build();

        when(userRepository.findByUserId(userId)).thenReturn(Collections.singletonList(user));

        User result = userService.getUserByUserId(userId);

        verify(userRepository).findByUserId(userId);
        assertEquals(user, result);
    }

    @Test
    public void testGetUserByUserIdNotFound() {
        String userId = "nonExistentUser";

        when(userRepository.findByUserId(userId)).thenReturn(Collections.emptyList());

        User result = userService.getUserByUserId(userId);

        verify(userRepository).findByUserId(userId);
        assertEquals(null, result);
    }
}
