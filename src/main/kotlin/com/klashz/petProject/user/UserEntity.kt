package com.klashz.petProject.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @Column(name ="cedula")
    val dni:String,
    @Column(name = "nombre_completo")
    val fullName : String,
    val email : String,
    val phone : String,
    val password : String,
    val rol : String,
)
