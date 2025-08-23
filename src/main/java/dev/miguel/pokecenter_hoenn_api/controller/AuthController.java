package dev.miguel.pokecenter_hoenn_api.controller;

import dev.miguel.pokecenter_hoenn_api.dto.LoginRequestDTO;
import dev.miguel.pokecenter_hoenn_api.dto.RegisterRequestDTO;
import dev.miguel.pokecenter_hoenn_api.dto.ResponseDTO;
import dev.miguel.pokecenter_hoenn_api.entity.User;
import dev.miguel.pokecenter_hoenn_api.repository.UserRepository;
import dev.miguel.pokecenter_hoenn_api.security.TokenService;
import dev.miguel.pokecenter_hoenn_api.util.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Autowired
    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body) {
        Optional<User> userOptional = this.userRepository.findByUsername(body.username());

        if(userOptional.isPresent()) {
            User user = userOptional.get();
            if(passwordEncoder.matches(body.password(), user.getPassword())) {
                String token = this.tokenService.generateToken(user);
                return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body) {
        Optional<User> userOptional = this.userRepository.findByUsername(body.username());

        if (userOptional.isEmpty()){
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setUsername(body.username());
            newUser.setName(body.name());
            newUser.setRole(body.role());
            this.userRepository.save(newUser);

            String token = this.tokenService.generateToken(newUser);

            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    };
}
