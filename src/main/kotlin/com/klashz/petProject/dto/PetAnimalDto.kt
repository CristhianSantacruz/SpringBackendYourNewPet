package com.klashz.petProject.dto


import com.fasterxml.jackson.annotation.JsonIgnore
import com.klashz.petProject.user.UserEntity
import com.klashz.petProject.utils.Size
import com.klashz.petProject.utils.Status
import java.util.*

data class PetAnimalDto(
    val id: UUID?,
    var name : String?,
    @JsonIgnore
    val userId : String?,
    var age: String?,
    var raze : String?,
    var size : Size?,
    var status : Status = Status.DISPONIBLE,
    val description : String,
    var petType: String?,
    var imagePathUrl : String?,
    val contactPet : String,

)
