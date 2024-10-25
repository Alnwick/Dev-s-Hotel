package com.aluracurso.devshotel.Security;

import com.aluracurso.devshotel.Domain.User.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret")
    private String apiSecret;

    public String generateToken(User user) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("Devs hotel")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withExpiresAt(generateDate())
                    .sign(algorithm);
        }catch (JWTCreationException ex){
            throw new RuntimeException();
        }
    }
    public String getSubject(String token) {
        if(token == null){
            throw new RuntimeException("Token is null");
        }

        DecodedJWT verifier = null;

        try {
            System.out.println("API Secret: " + apiSecret);
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("Devs hotel")
                    .build()
                    .verify(token);
            verifier.getSubject();
        }catch (JWTVerificationException ex){
            System.out.println(ex.toString());
        }
        if(verifier.getSubject() == null){
            System.out.println("Verifier invalid");
        }
        System.out.println("Verified Token: " + verifier.getSubject());
        return verifier.getSubject();
    }
    private Instant generateDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
