package med.vol.api.controller;

import jakarta.validation.Valid;
import med.vol.api.config.security.TokenService;
import med.vol.api.dto.JWTTokenDTO;
import med.vol.api.dto.UserLoginDTO;
import med.vol.api.model.User;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid UserLoginDTO userDetails) {

        var token = new UsernamePasswordAuthenticationToken(userDetails.login(), userDetails.password());
        var authentication = authenticationManager.authenticate(token);

        var JWToken = tokenService.getToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new JWTTokenDTO(JWToken));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserLoginDTO user) {

        User userCreated = tokenService.registerUser(user);

        return ResponseEntity.ok("User " + userCreated.getLogin() + "Created succesfuuly" );
    }
}
