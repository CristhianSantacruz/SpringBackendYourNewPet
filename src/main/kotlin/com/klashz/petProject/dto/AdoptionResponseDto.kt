package com.klashz.petProject.dto

import com.klashz.petProject.pet.PetAnimalEntity
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class AdoptionResponseDto(
    val petAdopte: PetAnimalDto,
    val adoptionDate: LocalDateTime,
){}