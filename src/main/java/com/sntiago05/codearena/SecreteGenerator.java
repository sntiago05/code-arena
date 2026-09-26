package com.sntiago05.codearena;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;

import javax.crypto.SecretKey;

public class SecreteGenerator {public static void main(String[] args) {
    SecretKey key = Jwts.SIG.HS256.key().build();
    String secretString = Encoders.BASE64.encode(key.getEncoded());
    System.out.println("Your secret string is: " + secretString);
}
}
