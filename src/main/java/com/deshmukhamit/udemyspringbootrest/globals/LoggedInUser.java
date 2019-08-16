package com.deshmukhamit.udemyspringbootrest.globals;

// currently logged in user

import com.deshmukhamit.udemyspringbootrest.user.DAOUser;

public class LoggedInUser {

    private DAOUser user;
    private String jwtToken;

    public LoggedInUser() {}

    public void setUser(DAOUser user) {
        this.user = user;
    }

    public DAOUser getUser() {
        return this.user;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return this.jwtToken;
    }

}
