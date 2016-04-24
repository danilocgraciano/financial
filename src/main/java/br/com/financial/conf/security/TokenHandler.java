package br.com.financial.conf.security;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import br.com.financial.model.Usuario;

public class TokenHandler {
    
    public static final String APP_WEB_ID = "financial_web";
    public static final String SECRET_KEY = "FINANCIAL_SECRET";

    public String create(String id, Usuario usuario, long ttlMin) {

        // The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(usuario.getEmail()).signWith(signatureAlgorithm, signingKey).claim("senha", usuario.getSenha());

        // if it has been specified, let's add the expiration
        if (ttlMin >= 0) {
            long expMillis = nowMillis + (ttlMin * 60000);
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();

    }

}
