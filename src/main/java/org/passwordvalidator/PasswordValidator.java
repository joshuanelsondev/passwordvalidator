package org.passwordvalidator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PasswordValidator {

    private static final Set<String> COMMON_WEAK_PASSWORDS = new HashSet<>(Arrays.asList(
            "Password1!",
            "Summer2024!",
            "Winter2023!",
            "Spring2025!",
            "Autumn2022!",
            "Welcome123!",
            "Football1!",
            "Baseball2!",
            "Password12!",
            "Qwerty!123",
            "Asdfg!1234",
            "12345678a!",
            "PasswordA!",
            "MySecret1!",
            "ILoveJava1!"));

    public void validatePassword(String password, String confirmPassword) throws WeakPasswordException, PasswordMismatchException {


        if (password.length() < 8) {
            throw new WeakPasswordException("Password must be at least 8 characters long.");
        }

        if (password.length() > 20) {
            throw new WeakPasswordException("Password must not exceed 20 characters.");
        }

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;
        String specialChars = "!@#$%^&*()-_=+\\|[]{};:/?.>";

        for (char ch: password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(ch)) {
               hasLowercase = true;
            } else if (Character.isDigit(ch)) {
               hasDigit = true;
            } else if (specialChars.indexOf(ch) >= 0) {
               hasSpecialChar = true;
            }
        }

        if (!hasUppercase || !hasLowercase || !hasDigit || !hasSpecialChar) {
            throw new WeakPasswordException("Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
        }

        if (!password.equals(confirmPassword)) {
            throw new PasswordMismatchException("Passwords do not match");
        }

        if (COMMON_WEAK_PASSWORDS.contains(password)) {
            throw new WeakPasswordException("Password is too common and weak.");
        }
    };
}
