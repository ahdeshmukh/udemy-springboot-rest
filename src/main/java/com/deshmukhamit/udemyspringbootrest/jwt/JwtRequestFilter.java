// https://dzone.com/articles/spring-boot-security-json-web-tokenjwt-hello-world

package com.deshmukhamit.udemyspringbootrest.jwt;

import com.deshmukhamit.udemyspringbootrest.user.DAOUser;
import com.deshmukhamit.udemyspringbootrest.user.UserDetails;
import com.deshmukhamit.udemyspringbootrest.user.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//import java.util.Date;
//import java.util.concurrent.TimeUnit;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        int id = 0;
        String jwtToken = null;
        // JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                id = Integer.parseInt(jwtTokenUtil.getIdFromToken(jwtToken));
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }

        // Once we get the token validate it.
        if (id != 0 && SecurityContextHolder.getContext().getAuthentication() == null) {
            //UserDetails userDetails = this.jwtUserDetailsService.loadUserById(id);

            DAOUser user = userService.findUserById(id);

            UserDetails userDetails = jwtUserDetailsService.loadUser(user);

            // if token is valid configure Spring Security to manually set authentication
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // After setting the Authentication in the context, we specify that the current user is authenticated.
                // So it passes the Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);


                // JWT is still valid, but we don't want a user to get kicked out in middle of active session after current JWT expires
                // so renew the JWT after every 15 mins.
                // this looks like a good compromise and we don't have to issue a new JWT after every request

                //Date jwtExpDate = jwtTokenUtil.getExpirationDateFromToken(jwtToken);
                //Date curDate = new Date();

            }
        }

        chain.doFilter(request, response);
    }
}
