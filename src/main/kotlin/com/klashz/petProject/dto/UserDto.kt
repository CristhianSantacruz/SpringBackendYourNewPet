package com.klashz.petProject.dto
import com.klashz.petProject.pet.PetAnimalEntity
import lombok.Getter
import lombok.Setter

@Getter
@Setter
data class UserDto(

    val dni:String,
    val fullName : String,
    val email : String,
    val phone : String,
    val password : String,
    val rol : String?,
    val petAnimalListRegister : List<PetAnimalDto>?,
    var adoptedPets: List<AdoptionDto>?,
)