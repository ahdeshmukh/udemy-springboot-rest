package com.deshmukhamit.udemyspringbootrest.authentication;

import com.deshmukhamit.udemyspringbootrest.jwt.JwtRequest;
import com.deshmukhamit.udemyspringbootrest.jwt.JwtTokenUtil;
import com.deshmukhamit.udemyspringbootrest.jwt.JwtUserDetailsService;
import com.deshmukhamit.udemyspringbootrest.user.DAOUser;
import com.deshmukhamit.udemyspringbootrest.user.UserDetails;
import com.deshmukhamit.udemyspringbootrest.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Hashtable;

@RestController
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticationService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final DAOUser user = userService.findUserByUserName(authenticationRequest.getUsername());
        final UserDetails userDetails = jwtUserDetailsService.loadUser(user);
        final String jwtToken = jwtTokenUtil.generateToken(userDetails);

        Hashtable loggedInUserInfo = new Hashtable();
        loggedInUserInfo.put("user", user);
        loggedInUserInfo.put("jwtToken", jwtToken);

        return ResponseEntity.ok(loggedInUserInfo);
    }

}
