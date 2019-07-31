// https://dzone.com/articles/spring-boot-security-json-web-tokenjwt-hello-world

package com.deshmukhamit.udemyspringbootrest.jwt;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Password generated at https://bcrypt-generator.com/ for string "password"
        // Todo: instead of single value john.doe, use array with multiple user names, password can remain the same
        if("john.doe".equals(username)) {
            return new User(username,"$2a$10$mooHRvqfH.TNjQcQoi2RdeQ8xtbTHRCq9cRlAhjKvlNfessvJXDBW",
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
