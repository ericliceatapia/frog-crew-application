package edu.cs.tcu.frog_crew_application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserRegistrationDTO {

    @NotBlank(message = "First Name is required")
    private String firstName;

    @NotBlank(message = "Last Name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Phone Number is required")
    @Pattern(regexp = "\\d{3}-\\d{3}-\\d{4}", message = "Phone number must match the format 999-999-9999")
    private String phoneNumber;

    @NotBlank(message = "Role is required")
    private String role;

    @NotBlank(message = "Qualified Position is required")
    private String qualifiedPosition;

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getQualifiedPosition() {
        return qualifiedPosition;
    }
    public void setQualifiedPosition(String qualifiedPosition) {
        this.qualifiedPosition = qualifiedPosition;
    }
}
