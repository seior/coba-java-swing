package com.buku.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Password {

    public static String generatePassword(String password) throws NoSuchAlgorithmException {
        String salt = getSalt();

        return generateSHA256Password(password, salt);
    }

    private static String generateSHA256Password(String password, String salt) {
        String generatePassword = "";

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            messageDigest.update(salt.getBytes());
            byte[] bytes = messageDigest.digest(password.getBytes());

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < bytes.length; i++) {
                stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));

                generatePassword = stringBuilder.toString();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return generatePassword;
    }

    private static String getSalt() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];

        secureRandom.nextBytes(salt);

        return salt.toString();
    }

}
