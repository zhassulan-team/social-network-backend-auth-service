package kata.academy.eurekaauthservice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import kata.academy.eurekaauthservice.exception.TokenValidationException;
import kata.academy.eurekaauthservice.model.dto.UserValidateDto;
import kata.academy.eurekaauthservice.model.entity.User;
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

    public UserValidateDto parse(String token) {
        DecodedJWT jwt = JWT.decode(token);
        if (jwt.getExpiresAt().before(new Date())) {
            throw new TokenValidationException("Срок действия токена истек");
        }
        return new UserValidateDto(jwt.getClaim("id").asLong(), jwt.getClaim("role").asString());
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("role", user.getRole().name());
        return doGenerateToken(claims, user.getEmail());
    }

    private String doGenerateToken(Map<String, Object> claims, String email) {
        Date date = new Date();
        return JWT.create()
                .withPayload(claims)
                .withSubject(email)
                .withIssuedAt(date)
                .withExpiresAt(new Date(date.getTime() + this.expirationTime))
                .sign(algorithm);
    }
}
