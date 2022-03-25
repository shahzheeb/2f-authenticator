package com.shahzheeb.authen;

import org.apache.commons.codec.binary.Base32;

import java.security.SecureRandom;

public class GenerateSecretKey {
    public static void main(String[] args) {
        String secretKey = generateSecretKey();
        System.out.println("secretKey = "+secretKey);
    }

    public static String generateSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);

        Base32 base32 = new Base32();
        return base32.encodeToString(bytes);
    }
}
