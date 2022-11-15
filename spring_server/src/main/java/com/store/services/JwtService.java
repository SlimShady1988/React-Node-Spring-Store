package com.store.services;

import io.jsonwebtoken.SignatureAlgorithm;

import javax.xml.bind.DatatypeConverter;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@PropertySource("classpath:application.yml")
public class JwtService {
    @Value("${SECRET_KEY}")
    private String apiKey;
    protected String generateJWT(Long id, String email, String role) {

        Map<String, Object>  fields= new LinkedHashMap<>();
        fields.put("id", id);
        fields.put("email", email);
        fields.put("role", role);

        var lifeTimeToken = Calendar.getInstance();
        lifeTimeToken.add(Calendar.HOUR_OF_DAY, 24);    //set token life limit to 24 hour
        Date exp = new Date(lifeTimeToken.getTimeInMillis());

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .setClaims(fields)
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, signingKey)
                .setExpiration(exp);


        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }
}
