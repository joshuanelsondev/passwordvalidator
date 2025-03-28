package org.passwordvalidator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserRegistrationTest {
    private final PasswordValidator validator = new PasswordValidator();
    private final UserRegistration registration = new UserRegistration(validator);


    @Test
    public void testUserRegistrationSuccess() {
        RegistrationResult result = registration.registerUser("MyValidPassword1!", "MyValidPassword1!");
        assertTrue(result.isSuccess());
        assertEquals("User registered successfully", result.getMessage());
    }


    @Test
    public void testUserRegistrationFailure() {
        RegistrationResult result = registration.registerUser("short!", "short!");
        assertFalse(result.isSuccess());
        assertEquals("Password must be at least 8 characters long.", result.getMessage());
    }
}
