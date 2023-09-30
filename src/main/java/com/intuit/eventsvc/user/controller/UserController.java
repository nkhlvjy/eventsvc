package com.intuit.eventsvc.user.controller;

import com.intuit.eventsvc.user.dto.UserCreationRequest;
import com.intuit.eventsvc.user.entity.User;
import com.intuit.eventsvc.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody UserCreationRequest request) {
        User user = userService.createUser(request);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/{user-id}")
    public ResponseEntity<User> getUserByUserId(@PathVariable("user-id") String userId) {
        User user = userService.getUserByUserId(userId);
        if(user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);
    }
}
