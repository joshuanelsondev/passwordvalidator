package org.passwordvalidator;

import java.util.Scanner;

public class UserRegistration {

    private PasswordValidator validator;

    public UserRegistration(PasswordValidator validator) {
        this.validator = validator;
    }

    public RegistrationResult registerUser(String password, String confirmPassword) {
        try {
            validator.validatePassword(password, confirmPassword);
            return new RegistrationResult(true, "User registered successfully");
        } catch (WeakPasswordException | PasswordMismatchException e) {
            return new RegistrationResult(false, e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PasswordValidator validator = new PasswordValidator();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        System.out.println("Confirm password: ");
        String confirmPassword = scanner.nextLine();

        UserRegistration registration = new UserRegistration(validator);
        RegistrationResult result = registration.registerUser(password, confirmPassword);

        if (result.isSuccess()) {
            System.out.println("Registration successful: " + result.getMessage());
        } else {
            System.out.println("Registration failed: " + result.getMessage());
        }

        scanner.close();
    }

}
