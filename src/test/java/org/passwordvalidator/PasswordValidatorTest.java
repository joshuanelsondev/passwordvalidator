package org.passwordvalidator;

import org.junit.Test;
import static org.junit.Assert.*;

public class PasswordValidatorTest {

    private final PasswordValidator validator = new PasswordValidator();

    @Test
    public void testValidPassword() throws WeakPasswordException, PasswordMismatchException {
        validator.validatePassword("xY7!pL9$zR2#qA5&", "xY7!pL9$zR2#qA5&");
    }

    @Test
    public void testShortPassword() {
        try {
            validator.validatePassword("Short1!", "Short1!");
            fail("Expected WeakPasswordException");
        } catch (WeakPasswordException e) {
            assertEquals("Password must be at least 8 characters long.", e.getMessage());
        } catch (PasswordMismatchException e) {
            fail("Did not expect password mismatch exception");
        }
    }

    @Test
    public void testLongPassword() {
        try {
            validator.validatePassword("ThisPasswordIsWayTooLong1!", "ThisPasswordIsWayTooLong1!");
            fail("Expected WeakPasswordException");
        } catch (WeakPasswordException e) {
            assertEquals("Password must not exceed 20 characters.", e.getMessage());
        } catch (PasswordMismatchException e) {
            fail("Did not expect password mismatch exception");
        }
    }


    @Test
    public void testMissingUppercase() {
        try {
            validator.validatePassword("lowercase!", "lowercase!");
            fail("Expected WeakPasswordException");
        } catch (WeakPasswordException e) {
            assertEquals("Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character.", e.getMessage());
        } catch (PasswordMismatchException e) {
            fail("Did not expect password mismatch exception");
        }
    }


    @Test
    public void testMissingLowercase() {
        try {
            validator.validatePassword("UPPERCASE!", "UPPERCASE!");
            fail("Expected WeakPasswordException");
        } catch (WeakPasswordException e) {
            assertEquals("Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character.", e.getMessage());
        } catch (PasswordMismatchException e) {
            fail("Did not expect password mismatch exception");
        }
    }



    @Test
    public void testMissingDigit() {
        try {
            validator.validatePassword("NoDigit!", "NoDigit!");
            fail("Expected WeakPasswordException");
        } catch (WeakPasswordException e) {
            assertEquals("Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character.", e.getMessage());
        } catch (PasswordMismatchException e) {
            fail("Did not expect password mismatch exception");
        }
    }


    @Test
    public void testMissingSpecialCharacter() {
        try {
            validator.validatePassword("NoSpecial!", "NoSpecial!");
            fail("Expected WeakPasswordException");
        } catch (WeakPasswordException e) {
            assertEquals("Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character.", e.getMessage());
        } catch (PasswordMismatchException e) {
            fail("Did not expect password mismatch exception");
        }
    }


    @Test
    public void testPasswordMismatch() {
        try {
            validator.validatePassword("ThisIsMyPassword1!", "PasswordNotTheSame!");
            fail("Expected PasswordMismatchException");
        } catch (PasswordMismatchException e) {
            assertEquals("Passwords do not match.", e.getMessage());
        } catch (WeakPasswordException e) {
            fail("Did not expect WeakPasswordException");
        }
    }


    @Test
    public void testCommonWeakPassword() {
        try {
            validator.validatePassword("Password1!", "Password1!");
            fail("Expected WeakPasswordException");
        } catch (WeakPasswordException e) {
            assertEquals("Passwords is too common and weak.", e.getMessage());
        } catch (PasswordMismatchException e) {
            fail("Did not expect password mismatch exception");
        }
    }

    @Test
    public void testPasswordHashing() {
        String hashedPassword = PasswordHasher.hashPassword("TestPassword123!");
        assertNotNull(hashedPassword);
        assertNotEquals("TestPassword123!", hashedPassword);
    }
}
