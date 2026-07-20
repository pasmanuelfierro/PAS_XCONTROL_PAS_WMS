package com.pas.xcontrolwms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class XControlWMSMiddleware {

    public static void main(String[] args) {

        SpringApplication.run(XControlWMSMiddleware.class, args);
    }
}
