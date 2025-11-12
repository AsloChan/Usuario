package com.javanauta.Usuario.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "telefone")
@Builder
public class Telefone {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "numero", length = 10)
    private  String numero ;
    @Column (name = "ddd", length = 3)
    private String ddd;

}
