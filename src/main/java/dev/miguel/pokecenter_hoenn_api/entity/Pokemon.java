package dev.miguel.pokecenter_hoenn_api.entity;

import dev.miguel.pokecenter_hoenn_api.util.PokemonStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pokemon")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 30)
    @Column(length = 30, nullable = false)
    private String name;

    @NotBlank
    @Size(max = 20)
    @Column(length = 20, nullable = false)
    private String specie;

    @Min(1)
    @Max(100)
    @Column(nullable = false)
    private int level;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private PokemonStatus status;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime dataEntry;

    private LocalDateTime dataExit;

    @NotBlank
    @Size(max = 50)
    @Column(length = 50, nullable = false)
    private String trainerId;



}
