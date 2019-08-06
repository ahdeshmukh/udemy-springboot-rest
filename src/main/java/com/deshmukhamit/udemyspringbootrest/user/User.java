package com.deshmukhamit.udemyspringbootrest.user;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class User extends org.springframework.security.core.userdetails.User implements UserDetails {

    private final int id;

    public User(int id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        //this(username, password, true, true, true, true, authorities);
        super(username, password, true, true, true, true, authorities);
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
