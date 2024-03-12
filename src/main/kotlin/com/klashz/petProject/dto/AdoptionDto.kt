package com.klashz.petProject.dto

import com.klashz.petProject.pet.PetAnimalEntity
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class AdoptionDto(
    val id : Long?,
    val adoptedByUserId : String,
    val petId : UUID,
    val petAdopted : PetAnimalEntity?,
    val adoptionDate: LocalDateTime? = LocalDateTime.now()
) {
}