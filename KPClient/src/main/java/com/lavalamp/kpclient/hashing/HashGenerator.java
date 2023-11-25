package com.lavalamp.kpclient.hashing;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashGenerator {

    public static String GenerateSalt(){
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[8];
        random.nextBytes(saltBytes);
        return new String(saltBytes, StandardCharsets.UTF_8);
    }

    public static String GenerateHashedPassword(String password){
        StringBuilder generatedPassword = new StringBuilder();

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            byte[] bytes = messageDigest.digest();

            for (var i: bytes) {
                generatedPassword.append(Integer.toString((i & 0xff) + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword.toString();
    }
}
