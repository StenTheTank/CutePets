package com.example.backend.model;

import com.example.backend.model.enumeration.Country;
import com.example.backend.model.enumeration.FurColor;
import com.example.backend.model.enumeration.PetType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    @Enumerated(EnumType.STRING)
    private PetType petType;
    @NotNull
    @Enumerated(EnumType.STRING)
    private FurColor furColor;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Country country;
    private String imageUrl;
    private String author;
}
