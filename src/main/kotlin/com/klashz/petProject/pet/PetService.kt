package com.klashz.petProject.pet

import com.klashz.petProject.dto.PetAnimalDto
import com.klashz.petProject.pet.interfaces.IPetRepository
import com.klashz.petProject.pet.interfaces.IPetService
import com.klashz.petProject.utils.Status
import org.springframework.stereotype.Service
import java.util.*

@Service
class PetService(private val iPetRepository: IPetRepository) : IPetService{
    override fun getPetById(id: UUID): Optional<PetAnimalDto> {
        return iPetRepository.getPetById(id)
    }

    override fun getAllPet(): List<PetAnimalDto> {
        return iPetRepository.getAllPet()
    }

    override fun savePet(petAnimalDto: PetAnimalDto): PetAnimalDto {
        petAnimalDto.status = Status.DISPONIBLE
        petAnimalDto.name = petAnimalDto.name ?: "DESCONOCIDO"
        petAnimalDto.age = petAnimalDto.age ?: "DESCONOCIDO"
        petAnimalDto.imagePathUrl = petAnimalDto.imagePathUrl ?: "NONE"
        petAnimalDto.raze = petAnimalDto.raze ?: "DESCONOCIDO"
        petAnimalDto.petType = petAnimalDto.petType?: "DESCONOCIDO"
       return iPetRepository.savePet(petAnimalDto)
    }

    override fun updatePet(petAnimalDto: PetAnimalDto): Optional<PetAnimalDto> {

        if(iPetRepository.getPetById(UUID.fromString(petAnimalDto.id.toString())).isEmpty){
            return Optional.empty();
        }
        return Optional.of(iPetRepository.savePet(petAnimalDto))
    }

    override fun deletePet(id: UUID) {
       iPetRepository.deletePet(id)
    }

}