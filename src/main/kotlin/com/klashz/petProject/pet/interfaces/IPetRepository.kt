package com.klashz.petProject.pet.interfaces

import com.klashz.petProject.dto.PetAnimalDto
import java.util.Optional
import java.util.UUID

interface IPetRepository {

    fun getPetById(id:UUID) : Optional<PetAnimalDto>
    fun getAllPet() : List<PetAnimalDto>
    fun savePet(petAnimalDto: PetAnimalDto) : PetAnimalDto
    fun deletePet(id:UUID)

}