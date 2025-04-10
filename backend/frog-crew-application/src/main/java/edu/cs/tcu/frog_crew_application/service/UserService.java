package edu.cs.tcu.frog_crew_application.service;

import edu.cs.tcu.frog_crew_application.dto.UserRegistrationDTO;
import edu.cs.tcu.frog_crew_application.dto.UserUpdateDTO;
import edu.cs.tcu.frog_crew_application.entity.User;
import edu.cs.tcu.frog_crew_application.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder; //Important import
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Add PasswordEncoder field

    // Constructor to inject both UserRepository and PasswordEncoder
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserRegistrationDTO userRegistrationDTO) {
        // Check if email already exists
        if (userRepository.findByEmail(userRegistrationDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already registered. Please login instead.");
        }

        // Map DTO to Entity
        User user = new User();
        user.setFirstName(userRegistrationDTO.getFirstName());
        user.setLastName(userRegistrationDTO.getLastName());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setPhoneNumber(userRegistrationDTO.getPhoneNumber());
        user.setRole(userRegistrationDTO.getRole());
        user.setQualifiedPosition(userRegistrationDTO.getQualifiedPosition());

        // Encrypt password before saving
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

        // Save User
        return userRepository.save(user);
    }

    public User updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));

        // Update fields
        user.setFirstName(userUpdateDTO.getFirstName());
        user.setLastName(userUpdateDTO.getLastName());
        user.setEmail(userUpdateDTO.getEmail());
        user.setPhoneNumber(userUpdateDTO.getPhoneNumber());
        user.setRole(userUpdateDTO.getRole());
        user.setQualifiedPosition(userUpdateDTO.getQualifiedPosition());

        // Encrypt password again when updating
        user.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));

        // Save updated user
        return userRepository.save(user);
    }
}
