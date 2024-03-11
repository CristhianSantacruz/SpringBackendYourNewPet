package com.klashz.petProject.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.Getter
import lombok.Setter

@Getter
@Setter
@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @Column(name ="cedula")
    val dni:String,
    @Column(name = "nombre_completo")
    val fullName : String,
    @Column(name = "correo")
    val email : String,
    @Column(name = "celular")
    val phone : String,
    @Column(name = "password")
    val password : String,
    @Column(name = "rol")
    val rol : String,
)
