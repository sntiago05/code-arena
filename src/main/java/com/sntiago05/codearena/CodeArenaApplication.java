package com.sntiago05.codearena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CodeArenaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeArenaApplication.class, args);
    }

}
