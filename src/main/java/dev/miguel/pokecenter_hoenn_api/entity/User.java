package dev.miguel.pokecenter_hoenn_api.entity;


import dev.miguel.pokecenter_hoenn_api.util.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(min = 3, max = 30)
    @Column(updatable = false)
    private String username;

    @NotBlank
    @Size(min = 6)
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull
    private UserRole role;

}
