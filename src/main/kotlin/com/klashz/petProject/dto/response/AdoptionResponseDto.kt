package com.klashz.petProject.dto.response

import com.klashz.petProject.dto.PetAnimalDto
import java.time.LocalDateTime

data class AdoptionResponseDto(
    val petAdopte: PetAnimalDto?,
    val adoptionDate: LocalDateTime?,
){}