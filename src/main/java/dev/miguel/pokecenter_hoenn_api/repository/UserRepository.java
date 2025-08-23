package dev.miguel.pokecenter_hoenn_api.repository;

import dev.miguel.pokecenter_hoenn_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
}
