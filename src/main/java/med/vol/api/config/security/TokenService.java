package med.vol.api.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import jakarta.validation.Valid;
import med.vol.api.dto.UserLoginDTO;
import med.vol.api.model.User;
import med.vol.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;


    @Value("${api.security.token.secret}")
    private String secret;

    public String getToken(User user) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Voll.med")
                    .withSubject(user.getLogin())
                    .withExpiresAt(expireDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("error generating token jwt", exception);
        }
    }

    private Instant expireDate() {
        return LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.of("-04:00"));
    }


    public User registerUser(@Valid UserLoginDTO user) {
        var passwordEncoded = passwordEncoder.encode(user.password());

        return userRepository.save(new User(user.login(), passwordEncoded));
    }
}
