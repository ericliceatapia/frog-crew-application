package edu.cs.tcu.frog_crew_application.service;

import edu.cs.tcu.frog_crew_application.entity.User;
import edu.cs.tcu.frog_crew_application.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        // Business logic can be added here (e.g., check if email exists)
        return userRepository.save(user);
    }
}
