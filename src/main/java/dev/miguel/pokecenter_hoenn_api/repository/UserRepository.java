package dev.miguel.pokecenter_hoenn_api.repository;

import dev.miguel.pokecenter_hoenn_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
