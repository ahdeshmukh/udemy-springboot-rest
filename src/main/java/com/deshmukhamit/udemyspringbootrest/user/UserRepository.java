package com.deshmukhamit.udemyspringbootrest.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<DAOUser, Integer> {
    DAOUser findByUsername(String username);
}
