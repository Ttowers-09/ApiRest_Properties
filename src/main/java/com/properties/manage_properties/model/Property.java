package com.properties.manage_properties.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "properties")

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder

public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false , length = 100)
    private String address;

    @NotNull
    @Positive
    @Column(nullable = false)
    private int price;

    @NotNull
    @Positive
    @Column(nullable = false, length = 50)
    private Integer size;

    @NotBlank
    @Size(max = 200)
    @Column(nullable = false, length = 200)
    private String description;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String actualOwner;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String location;

}
