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

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        System.out.println("Confirm password: ");
        String confirmPassword = scanner.nextLine();

        UserRegistration registration = new UserRegistration();
        registration.registerUser(password, confirmPassword);
    }

}
