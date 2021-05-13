package com.param1002.ryptor.encryptor;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

@Component
public class Encryption {

    public static final String ALGORITHM = "PBKDF2WithHmacSHA1";

    private static final SecureRandom SALTOR = new SecureRandom();

    private static final int ITERATIONS = 65536;

    private static final int KEY_LENGTH = 256;

    public byte[] getNextSalt() {

        final byte[] salt = new byte[16];
        SALTOR.nextBytes(salt);
        return salt;
    }

    public byte[] hash(final char[] password, final byte[] salt) throws InvalidKeySpecException, NoSuchAlgorithmException {

        final PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);

        Arrays.fill(password, Character.MIN_VALUE);

        final SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);

        final byte[] hashedPassword = factory.generateSecret(spec).getEncoded();

        spec.clearPassword();

        return hashedPassword;
    }

    public boolean check(final byte[] expectedHashedPassword, final char[] password, final byte[] salt) throws InvalidKeySpecException, NoSuchAlgorithmException {

        final byte[] hashedPassword = hash(password, salt);

        Arrays.fill(password, Character.MIN_VALUE);

        if (expectedHashedPassword.length != hashedPassword.length) {
            return false;
        }

        for (int i = 0; i < expectedHashedPassword.length; i++) {
            if (expectedHashedPassword[i] != password[i]) {
                return false;
            }
        }
        return true;
    }

}
