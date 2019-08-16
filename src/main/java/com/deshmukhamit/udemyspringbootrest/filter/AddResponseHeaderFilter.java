package com.deshmukhamit.udemyspringbootrest.filter;

import com.deshmukhamit.udemyspringbootrest.globals.LoggedInUser;
import com.deshmukhamit.udemyspringbootrest.jwt.JwtTokenUtil;
import com.deshmukhamit.udemyspringbootrest.jwt.JwtUserDetailsService;
import com.deshmukhamit.udemyspringbootrest.user.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class AddResponseHeaderFilter extends OncePerRequestFilter {

    @Resource(name = "requestScopedBean")
    LoggedInUser loggedInUser;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;


        String jwtToken = loggedInUser.getJwtToken(); // current jwt token
        if(jwtToken != null) {

            // JWT is still valid, but we don't want a user to get kicked out in middle of active session after current JWT expires
            // so renew the JWT after every 5 mins, since the current one will expire after 1 hr, irrespective of whether user is active or not
            // this looks like a good compromise as we don't have to issue a new JWT after every request

            LocalDateTime currentDateTime = LocalDateTime.now(ZoneOffset.UTC);
            Long currentDateTimeSeconds = currentDateTime.atZone(ZoneOffset.UTC).toEpochSecond();

            // jwt issued time in seconds
            Long jwtIssuedTime = jwtTokenUtil.getIssueDateFromToken(jwtToken).getTime()/1000;

            if(((currentDateTimeSeconds - jwtIssuedTime)/60) >= 5) { // issue new jwt every 5 mins
                UserDetails userDetails = jwtUserDetailsService.loadUser(loggedInUser.getUser());
                String newJwtToken = jwtTokenUtil.generateToken(userDetails);
                httpServletResponse.setHeader("NEW-JWT-TOKEN", newJwtToken);
            }

        }
        chain.doFilter(request, response);
    }
}
