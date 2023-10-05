package com.intuit.eventsvc.user.controller;

import com.intuit.eventsvc.user.dto.UserCreationRequest;
import com.intuit.eventsvc.user.entity.User;
import com.intuit.eventsvc.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody UserCreationRequest request) {
        User user = userService.createUser(request);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<User> getUserByUserId(@PathVariable("user-id") String userId) {
        User user = userService.getUserByUserId(userId);
        return ResponseEntity.ok(user);
    }
}
