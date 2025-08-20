package org.VI__236.utill;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Hasher {
    private String hashes;

    public String hashStrings(String inputStrings) {

        if (inputStrings == null) {
            return new String ("No input String.");
        }

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

                md.update(inputStrings.getBytes());
                byte[] digest = md.digest();
                hashes = (bytesToHex(digest));
                md.reset();


        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not available", e);
        }

        return hashes;
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
