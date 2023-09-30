package com.intuit.eventsvc.user.service;

import com.intuit.eventsvc.repository.repo.UserRepository;
import com.intuit.eventsvc.user.dto.UserCreationRequest;
import com.intuit.eventsvc.user.entity.User;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User createUser(UserCreationRequest request) {
        return userRepository.save(User.builder()
                .userId(request.getUserId())
                .created_at(new Timestamp(System.currentTimeMillis()))
                .updated_at(new Timestamp(System.currentTimeMillis()))
                .build());
    }

    public User getUserByUserId(String userId) {
        List<User> users = userRepository.findByUserId(userId);
        if(users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }
}
