package com.klashz.petProject.dto.response

import com.klashz.petProject.pet.PetAnimalEntity

data class ResponseFileDto(
    val name : String,
    val url : String,
    val type : String,
    val size : Int,
    val petResult : PetAnimalEntity?,
) {
}