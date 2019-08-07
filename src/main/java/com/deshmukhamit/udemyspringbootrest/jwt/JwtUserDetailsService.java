// https://dzone.com/articles/spring-boot-security-json-web-tokenjwt-hello-world

package com.deshmukhamit.udemyspringbootrest.jwt;

import com.deshmukhamit.udemyspringbootrest.user.DAOUser;
import com.deshmukhamit.udemyspringbootrest.user.UserDetailsImpl;
import com.deshmukhamit.udemyspringbootrest.user.UserDetails;
import com.deshmukhamit.udemyspringbootrest.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        DAOUser user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("UserDetailsImpl not found with username: " + username);
        }

        return new UserDetailsImpl(user.getId(), user.getUsername(), user.getPassword(),
                new ArrayList<>());

    }

    public UserDetails loadUserById(int id) {
        // Todo: create a new ResourceNotFound Exception class and throw it here

        DAOUser user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));

        return new UserDetailsImpl(user.getId(), user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

}
