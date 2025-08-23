package dev.miguel.pokecenter_hoenn_api.security;

import dev.miguel.pokecenter_hoenn_api.entity.User;
import dev.miguel.pokecenter_hoenn_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = this.userRepository.findByUsername(username);

        if(userOptional.isPresent()) {
            User user = userOptional.get();

            var authorities = Collections.singletonList(
                    new SimpleGrantedAuthority("ROLE_" + user.getRole().name())
            );
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    authorities
            );
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}