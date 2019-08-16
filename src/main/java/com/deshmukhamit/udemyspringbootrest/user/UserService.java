package com.deshmukhamit.udemyspringbootrest.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired UserRepository userRepository;

    public DAOUser findUserById(int id) throws UsernameNotFoundException {
        DAOUser user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));

        return user;
    }

    public DAOUser findUserByUserName(String userName) {
        DAOUser user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + userName));

        return user;
    }
}
