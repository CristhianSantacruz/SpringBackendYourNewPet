package com.klashz.petProject.pet.interfaces

import com.klashz.petProject.dto.PetAnimalDto
import com.klashz.petProject.dto.response.PetAnimalDtoResponse
import java.util.*

interface IPetService {

    fun getPetById(id: UUID): Optional<PetAnimalDto>
    fun getAllPet() : List<PetAnimalDtoResponse>
    fun savePet(petAnimalDto: PetAnimalDto) : PetAnimalDto
    fun updatePet(petAnimalDto: PetAnimalDto) : Optional<PetAnimalDto>
    fun deletePet(id:UUID)
}