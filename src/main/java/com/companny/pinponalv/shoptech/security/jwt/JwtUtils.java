package com.companny.pinponalv.shoptech.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    @Value("${security.jwt.private.key}")
    private String privateKey;
    @Value("${security.jwt.user.generator}")
    private String userGenerator;

    private static final long accessTokenExpiration = 1800000; //30minutes
    private static final long refreshTokenExpiration = 604800000; //7days

    public String generateAccessToken(Authentication authentication) {
        return buildToken(authentication, accessTokenExpiration, "access");
    }

    public String generateRefreshToken(Authentication authentication) {
        return buildToken(authentication, refreshTokenExpiration, "refresh");
    }

    private String buildToken(Authentication authentication, long expirationMs, String tokenType) {
        Algorithm algorithm = Algorithm.HMAC256(privateKey);
        String getUser = authentication.getPrincipal().toString();

        String getAuthorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        return JWT.create()
                .withIssuer(userGenerator)//ususario que emite el token
                .withSubject(getUser)//usuario que genera el token
                .withClaim("authorities", getAuthorities)//roles y permisos que tiene el usuario
                .withClaim("type", tokenType)//distingue entre access o refresh segun el metodo a llamar
                .withIssuedAt(new Date())//fecha de creacion
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationMs))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date())//valido para usar cuando se crea o despues de cierto tiempo
                .sign(algorithm);

    }


    public DecodedJWT verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(privateKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(userGenerator).build();

            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT;
        }catch (JWTVerificationException exception){
            throw new JWTVerificationException("Invalid token" + exception.getMessage());
        }
    }

    public String extractEmailOfToken(DecodedJWT decodedJWT){
        //subject es el usuario que establecimos para generar el token
        return decodedJWT.getSubject().toString();
    }

    public Claim getSpecificClaim(DecodedJWT decodedJWT, String claim){
        return decodedJWT.getClaim(claim);
    }

    public Map<String, Claim> getAllClaims(DecodedJWT decodedJWT){
        return decodedJWT.getClaims();
    }

}
