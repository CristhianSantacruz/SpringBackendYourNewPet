package com.klashz.petProject.pet
import com.klashz.petProject.user.UserEntity
import com.klashz.petProject.utils.Size
import com.klashz.petProject.utils.Status
import jakarta.persistence.*
import java.util.UUID;

@Entity
@Table(name = "pet_animals")
data class PetAnimalEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID?,
    @Column(name = "user_id")
    val userId : String,
    @Column(name = "nombre")
    val name : String,
    @Column(name = "edad")
    val age: String,
    @Column(name = "raza")
    val raze : String,
    @Column(name = "tamanio")
    @Enumerated(EnumType.STRING)
    val size : Size,
    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    val status : Status,
    @Column(name = "tipo_mascota")
    val petType : String,
    @Column(name = "description", length = 1024)
    val description : String,
    @Column(name = "utl_image", length = 1024)
    val imagePathUrl : String,
    @Column(name = "numero_contacto")
    val contactPet : String,
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    val userEntity: UserEntity?
)
