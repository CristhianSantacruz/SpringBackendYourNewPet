package com.klashz.petProject.pet.interfaces

import com.klashz.petProject.dto.PetAnimalDto
import java.util.*

interface IPetService {

    fun getPetById(id: UUID): Optional<PetAnimalDto>
    fun getAllPet() : List<PetAnimalDto>
    fun savePet(petAnimalDto: PetAnimalDto) : PetAnimalDto
    fun updatePet(petAnimalDto: PetAnimalDto) : Optional<PetAnimalDto>
    fun deletePet(id:UUID)
}