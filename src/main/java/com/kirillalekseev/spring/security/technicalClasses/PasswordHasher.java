package com.kirillalekseev.spring.security.technicalClasses;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {
    public static String hashPassword(String plainTextPassword){
        String salt = BCrypt.gensalt(12);
        String hashedPassword = BCrypt.hashpw(plainTextPassword,salt);
        return hashedPassword;
    }
}
