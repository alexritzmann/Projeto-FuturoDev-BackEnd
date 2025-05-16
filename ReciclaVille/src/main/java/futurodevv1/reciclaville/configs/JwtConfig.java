package futurodevv1.reciclaville.configs;

import futurodevv1.reciclaville.entities.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtConfig
{
    private Key getSignInKey()
    {
        String SECRET_KEY = "MinhaSenhaehtaofortequeninguemnessecursopoderaoupensaemadivinharqualserria1";
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token)
    {
        try
        {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e)
        {
            return false;
        }
    }

    public String extractUsername(String token)
    {
        return parseClaims(token)
                .getBody()
                .getSubject();
    }

    public Jws<Claims> parseClaims(String token)
    {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token);
    }

    public String generateToken(User user)
    {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // -> 10 horas
                .claim("id", user.getId())
                .claim("name", user.getName())
                .claim("role", user.getRole())
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
