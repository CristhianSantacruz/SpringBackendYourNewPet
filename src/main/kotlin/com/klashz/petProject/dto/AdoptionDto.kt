package com.klashz.petProject.dto

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class AdoptionDto(
    val id : Long?,
    val adoptedByUserId : String,
    val petId : UUID,
    val adoptionDate: LocalDateTime?
) {
}