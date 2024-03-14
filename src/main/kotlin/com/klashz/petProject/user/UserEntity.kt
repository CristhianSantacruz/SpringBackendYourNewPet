package com.klashz.petProject.user

import com.klashz.petProject.adoption.AdoptionEntity
import com.klashz.petProject.dto.AdoptionDto
import com.klashz.petProject.pet.PetAnimalEntity
import jakarta.persistence.*
import jakarta.transaction.Transactional
import lombok.Getter
import lombok.Setter
import org.jetbrains.annotations.NotNull

@Getter
@Setter
@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @Column(name ="cedula")
    @NotNull
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
    @OneToMany(mappedBy = "userEntity", orphanRemoval = true)
    val petAnimalListRegister: List<PetAnimalEntity>?,
    @OneToMany(mappedBy = "adoptedByUser", cascade = [CascadeType.REMOVE])
    val adoptedPets : List<AdoptionEntity>? ,
)
