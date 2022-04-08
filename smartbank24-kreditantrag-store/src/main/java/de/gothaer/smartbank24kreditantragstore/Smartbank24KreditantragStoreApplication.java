package de.gothaer.smartbank24kreditantragstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Smartbank24KreditantragStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(Smartbank24KreditantragStoreApplication.class, args);
    }

}
