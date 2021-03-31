package com.example.pidthesstransport;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class stringHasher {

    public static String CreateHash(String pass){


        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] digest= md.digest(pass.getBytes());

        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i< digest.length; i++){
            sb.append(Integer.toString((digest[i]&0xff)+0x100, 16).substring(1));
        }

        return sb.toString();


    }

}
