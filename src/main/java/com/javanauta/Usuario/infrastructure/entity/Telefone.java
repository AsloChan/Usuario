package com.javanauta.Usuario.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "telefone")

public class Telefone {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "numero", length = 10)
    private  String numero ;
    @Column (name = "ddd", length = 3)
    private String ddd;

}
