package com.kirillalekseev.spring.security.technicalClasses;


import java.util.Base64;

public class ImageService {
    public String convertToBase64(byte[] imageData) {
        return Base64.getEncoder().encodeToString(imageData);
    }
}
