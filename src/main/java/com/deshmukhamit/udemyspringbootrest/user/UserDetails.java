package com.deshmukhamit.udemyspringbootrest.user;

public interface UserDetails extends org.springframework.security.core.userdetails.UserDetails {

    int getId();
}
