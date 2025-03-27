package org.passwordvalidator;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class PasswordHasher {

    private static final Argon2 ARGON2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
    private static final int ITERATIONS = 2;
    private static final int MEMORY = 65536;
    private static final int PARALLELISM = 1;

    public static String hashPassword(String password) {
        return ARGON2.hash(ITERATIONS, MEMORY, PARALLELISM, password.toCharArray());
    }

    public static boolean checkPassword(String candidatePassword, String hashedPassword) {
        return ARGON2.verify(hashedPassword, candidatePassword.toCharArray());
    }

    public static void main(String[] args) {
    }
}
