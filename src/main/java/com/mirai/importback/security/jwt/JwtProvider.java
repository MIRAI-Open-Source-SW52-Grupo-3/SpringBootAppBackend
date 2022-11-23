package com.mirai.importback.security.jwt;

import com.mirai.importback.security.entity.MainUser;
import io.jsonwebtoken.*;
import org.jboss.jandex.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


import javax.validation.Valid;
import java.util.Date;

@Component
public class JwtProvider {

    private final static Logger logger= LoggerFactory.getLogger(JwtProvider.class);

    @Value("Secret")
    private String secret;
    @Value("36000")
    private int expiration;

    public String generateToken(Authentication authentication){
        MainUser mainUser=(MainUser) authentication.getPrincipal();

        return Jwts.builder().setSubject(mainUser.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+expiration*1000))
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }
    public String getUserNameFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e){
            logger.error("Malformed Token");
        }catch (UnsupportedJwtException e){
            logger.error("Token not supported");
        }catch (ExpiredJwtException e){
            logger.error("Expired Token");
        }catch (IllegalArgumentException e){
            logger.error("Empty Token");
        }catch (SignatureException e){
            logger.error("Signature Failure");
        }
        return false;
    }
}

