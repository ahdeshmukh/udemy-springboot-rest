// https://dzone.com/articles/spring-boot-security-json-web-tokenjwt-hello-world

package com.deshmukhamit.udemyspringbootrest.myjwtauth;

import java.io.Serializable;

public class MyJwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public MyJwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
