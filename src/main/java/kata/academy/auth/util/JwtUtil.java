package kata.academy.auth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import kata.academy.auth.model.entity.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.token.singing.key}")
    private String secret;

    @Value("${jwt.token.validate.ms}")
    private Long expirationTime;

    private Algorithm algorithm;

    @PostConstruct
    public void init() {
        this.algorithm = Algorithm.HMAC512(secret);
    }

    public boolean validateToken(String token) {
        return JWT.decode(token).getExpiresAt().after(new Date());
    }

    public Map<String, Object> parseToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        Map<String, Object> claims = new HashMap<>();
        claims.put("accountId", jwt.getClaim("accountId").asLong());
        claims.put("role", jwt.getClaim("role").asString());
        return claims;
    }

    public String generateToken(Account account) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("accountId", account.getId());
        claims.put("role", account.getRole().name());
        Date date = new Date();
        return JWT.create()
                .withPayload(claims)
                .withSubject(account.getUsername())
                .withIssuedAt(date)
                .withExpiresAt(new Date(date.getTime() + expirationTime))
                .sign(algorithm);
    }
}
