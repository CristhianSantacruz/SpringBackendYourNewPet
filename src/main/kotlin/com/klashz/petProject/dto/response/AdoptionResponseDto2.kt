package com.klashz.petProject.dto.response

import com.klashz.petProject.dto.PetAnimalDto
import com.klashz.petProject.dto.UserDto
import java.time.LocalDateTime

data class AdoptionResponseDto2(
    val id : Long?,
    val userByAdoption : UserDtoResponse,
    val petAdopted : PetAnimalDto,
    val adoptionDate : LocalDateTime?
){}
