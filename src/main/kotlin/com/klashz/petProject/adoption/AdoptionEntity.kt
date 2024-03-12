package com.klashz.petProject.adoption

import com.klashz.petProject.dto.PetAnimalDto
import com.klashz.petProject.pet.PetAnimalEntity
import com.klashz.petProject.user.UserEntity
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "adoptions")
data class AdoptionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long?,
    @Column(name = "adoptedby_user_id")
    val adoptedByUserId : String,
    @Column(name="adopted_pet_id")
    val petId : UUID,
    @JoinColumn(name = "adoptedby_user_id", insertable = false, updatable = false)
    @ManyToOne
    val adoptedByUser : UserEntity?,
    @JoinColumn(name = "adopted_pet_id", insertable = false, updatable = false)
    @ManyToOne
    val petAdopted : PetAnimalEntity?,
    @Column(name = "fecha_adopcion")
    val adoptionDate: LocalDateTime?,

) {
}