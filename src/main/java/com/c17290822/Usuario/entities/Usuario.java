package com.c17290822.Usuario.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Data;

@Entity(name = "usuarios_sb")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idusuario")  // Nombre de la columna en la base de datos
    private long IdUsuario;

    @Column(name = "nombre")  // Nombre de la columna en la base de datos
    private String nombre;

    @Column(name = "correo")  // Nombre de la columna en la base de datos
    private String correo;

    @Column(name = "password")  // Nombre de la columna en la base de datos
    private String password;
}
