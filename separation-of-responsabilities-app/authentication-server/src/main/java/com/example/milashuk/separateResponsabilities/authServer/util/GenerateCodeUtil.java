package com.example.milashuk.separateResponsabilities.authServer.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class GenerateCodeUtil {
    public static String generateCode() {
        String code;

        try {
            SecureRandom random = SecureRandom.getInstanceStrong();
            int randomInt = random.nextInt(9000) + 1000;
            code = String.valueOf(randomInt);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Problem when generating the random code.", e);
        }

        return code;
    }
}
