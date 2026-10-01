package com.sntiago05.codearena.application.ports.out;

public interface PasswordHasherPort {
    boolean matches(String password, String hashedPassword);
    String encode(String password);
}
