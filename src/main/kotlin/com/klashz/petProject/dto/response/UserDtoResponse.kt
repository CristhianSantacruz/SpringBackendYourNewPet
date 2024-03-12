package com.klashz.petProject.dto.response

import com.klashz.petProject.dto.AdoptionDto
import com.klashz.petProject.dto.PetAnimalDto

data class UserDtoResponse(
    val dni:String,
    val fullName : String,
    val email : String,
    val phone : String,
    val password : String,
    val rol : String?,
) {

}