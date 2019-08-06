package com.deshmukhamit.udemyspringbootrest.websecurityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
@PropertySource("classpath:cors.application.properties")
public class CorsAllowedDomains {

    @Autowired
    private Environment env;

    @Bean
    public ArrayList<String> getCorsAllowedDomains() {
        //TODO: take care of white space after split
        String corsAllowedDomains = env.getProperty("mycors.allowed.domains");
        return new ArrayList<>(
            Arrays.asList(corsAllowedDomains.split(","))
        );
    }
}
