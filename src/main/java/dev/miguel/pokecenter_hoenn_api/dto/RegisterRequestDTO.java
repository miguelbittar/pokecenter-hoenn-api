package dev.miguel.pokecenter_hoenn_api.dto;

import dev.miguel.pokecenter_hoenn_api.util.UserRole;

public record RegisterRequestDTO(String name, String username, String password, UserRole role) {
}