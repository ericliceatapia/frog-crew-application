package edu.cs.tcu.frog_crew_application.service;

import edu.cs.tcu.frog_crew_application.dto.CrewMemberProfileDTO;
import edu.cs.tcu.frog_crew_application.dto.UserRegistrationDTO;
import edu.cs.tcu.frog_crew_application.dto.UserUpdateDTO;
import edu.cs.tcu.frog_crew_application.entity.User;
import edu.cs.tcu.frog_crew_application.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserRegistrationDTO userRegistrationDTO) {
        if (userRepository.findByEmail(userRegistrationDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already registered. Please login instead.");
        }

        User user = new User();
        user.setFirstName(userRegistrationDTO.getFirstName());
        user.setLastName(userRegistrationDTO.getLastName());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setPhoneNumber(userRegistrationDTO.getPhoneNumber());
        user.setRole(userRegistrationDTO.getRole());
        user.setQualifiedPosition(userRegistrationDTO.getQualifiedPosition());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword())); // Encrypt password

        return userRepository.save(user);
    }

    public User updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));

        user.setFirstName(userUpdateDTO.getFirstName());
        user.setLastName(userUpdateDTO.getLastName());
        user.setEmail(userUpdateDTO.getEmail());
        user.setPhoneNumber(userUpdateDTO.getPhoneNumber());
        user.setRole(userUpdateDTO.getRole());
        user.setQualifiedPosition(userUpdateDTO.getQualifiedPosition());
        user.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword())); // Encrypt password on update

        return userRepository.save(user);
    }

    // ⭐️ New method for Use Case 3
    public CrewMemberProfileDTO getCrewMemberProfile(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Crew Member not found with ID: " + id));

        return new CrewMemberProfileDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getRole(),
                user.getQualifiedPosition()
        );
    }
}
