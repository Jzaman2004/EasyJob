package com.mycompany.easyjob;

import java.util.ArrayList;

public class Auth {

    private ArrayList<User> users = new ArrayList<>();
    private static final String[] VALID_ROLES = {"Student", "Employer"};

    public Auth() {
        register("Alice Student", "alice@example.com", "password123", "Student");
        register("Bob Employer", "bob@company.com", "password123", "Employer");
    }

    public User login(String email, String password) {
        if (email == null || password == null) {
            return null;
        }

        String normalizedEmail = email.trim().toLowerCase();

        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(normalizedEmail) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    public boolean register(String name, String email, String password, String role) {
        // Use validateRegistration to check all rules
        String error = validateRegistration(name, email, password, role);
        if (error != null) {
            return false;
        }

        String sanitizedName = name.trim().replaceAll("[\\p{Cntrl}]", "");
        String sanitizedEmail = email.trim().toLowerCase();
        String sanitizedRole = role.trim();

        users.add(new User(sanitizedName, sanitizedEmail, password, sanitizedRole));
        return true;
    }

    // ===== VALIDATION HELPERS =====

    public boolean isValidEmail(String email) {
        return validateEmail(email) == null;
    }

    /**
     * Returns specific error message for email, or null if valid.
     */
    public String validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return "Email address is required.";
        }
        String trimmed = email.trim();
        if (!trimmed.contains("@")) {
            return "Email must contain '@'.";
        }
        int atIndex = trimmed.indexOf('@');
        if (atIndex <= 0 || atIndex >= trimmed.length() - 1) {
            return "Email must have text before and after '@'.";
        }
        String domainPart = trimmed.substring(atIndex + 1);
        if (!domainPart.contains(".") || domainPart.startsWith(".") || domainPart.endsWith(".")) {
            return "Email domain must contain a '.' (e.g., example.com).";
        }
        return null;
    }

    public boolean isValidPassword(String password) {
        return validatePassword(password) == null;
    }

    /**
     * Returns specific error message for password, or null if valid.
     */
    public String validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return "Password is required.";
        }
        if (password.length() < 6) {
            return "Password must be at least 6 characters.";
        }
        boolean hasLetter = false, hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) hasLetter = true;
            if (Character.isDigit(c)) hasDigit = true;
        }
        if (!hasLetter && !hasDigit) {
            return "Password must contain at least one letter and one number.";
        }
        if (!hasLetter) {
            return "Password must contain at least one letter.";
        }
        if (!hasDigit) {
            return "Password must contain at least one number.";
        }
        return null;
    }

    public boolean isValidRole(String role) {
        if (role == null || role.trim().isEmpty()) {
            return false;
        }
        String trimmed = role.trim();
        for (String valid : VALID_ROLES) {
            if (valid.equalsIgnoreCase(trimmed)) {
                return true;
            }
        }
        return false;
    }

    public boolean emailExists(String email) {
        if (email == null) return false;
        String normalized = email.trim().toLowerCase();
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(normalized)) {
                return true;
            }
        }
        return false;
    }

    public String validateRegistration(String name, String email, String password, String role) {
        if (name == null || name.trim().length() < 2) {
            return "Name must be at least 2 characters.";
        }
        String emailError = validateEmail(email);
        if (emailError != null) {
            return emailError;
        }
        String passwordError = validatePassword(password);
        if (passwordError != null) {
            return passwordError;
        }
        if (role == null || !isValidRole(role)) {
            return "Please select a valid role: Student or Employer.";
        }
        if (emailExists(email)) {
            return "An account with that email already exists.";
        }
        return null; 
    }
}