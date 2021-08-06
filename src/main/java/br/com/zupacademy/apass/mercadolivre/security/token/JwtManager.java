package br.com.zupacademy.apass.mercadolivre.security.token;

import br.com.zupacademy.apass.mercadolivre.security.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtManager {

    @Value("${security.token.jwt.expiration}")
    private Long expiration;

    @Value("${security.token.jwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication) {
        var at = new Date();

        var exp = new Date();
        exp.setTime(at.getTime() + this.expiration);

        return Jwts.builder()
                .setIssuer("API do Forum da Alura")
                .setSubject(((User) authentication.getPrincipal()).getUsername())
                .setIssuedAt(at)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256,  this.secret)
                .compact();
    }

    public Boolean isValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody().getSubject();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     *
     * @param token
     * @return
     */
    public String getUsername(String token)  {
        return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody().getSubject();
    }
}
