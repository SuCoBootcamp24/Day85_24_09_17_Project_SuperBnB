package de.supercode.superbnb;

import de.supercode.superbnb.configuration.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
@EnableCaching
public class SuperBnBApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperBnBApplication.class, args);
    }

}
