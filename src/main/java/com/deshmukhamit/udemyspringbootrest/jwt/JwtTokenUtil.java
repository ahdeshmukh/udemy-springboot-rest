// https://dzone.com/articles/spring-boot-security-json-web-tokenjwt-hello-world

package com.deshmukhamit.udemyspringbootrest.jwt;

import com.deshmukhamit.udemyspringbootrest.user.UserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
// Util class to create and validate JWT Token
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;
    public static final long JWT_TOKEN_VALIDITY = 1 * 60 * 60; // hr * min * sec


    @Value("${jwt.secret}")
    private String secret;

    // retrieve id from JWT token
    public String getIdFromToken(String token) {
        return getClaimFromToken(token, "uid");
    }

    // retrieve subject from JWT token
    public String getSubjectFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from JWT token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }


    // get specific Claim from token
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public String getClaimFromToken(String token, String key) {
        final Claims claims = getAllClaimsFromToken(token);
        return (String) claims.get(key);
    }

    //get all Claims from token
    // for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }


    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails);
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. compact the JWT to a URL-safe string (https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    private String doGenerateToken(Map<String, Object> claims, UserDetails userDetails) {
        HashMap<String, Object> customClaims = new HashMap<>();
        customClaims.put("uid", Integer.toString(userDetails.getId()));
        customClaims.put("cts", new Date(System.currentTimeMillis()/1000L)); // cts = current time stamp

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(Integer.toString(userDetails.getId()))
                .addClaims(customClaims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (JWT_TOKEN_VALIDITY * 1000)))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String id = getIdFromToken(token);
        return (id.equals(Integer.toString(userDetails.getId())) && !isTokenExpired(token));
    }
}
