package com.mohammad.solution1.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class Message {

    private final UUID id;
    private final String message;
    private final String digest;

    public UUID getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getDigest() {
        return digest;
    }



    public Message(@JsonProperty("id") UUID id,
                   @JsonProperty("message") String message) {
        this.id = id;
        this.message = message;
        this.digest = getSHA(message);
    }


    private String getSHA(String input)
    {

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        }

        catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown"
                    + " for incorrect algorithm: " + e);

            return null;
        }
    }



}
