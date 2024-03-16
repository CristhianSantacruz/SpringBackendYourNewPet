package com.klashz.petProject.pet

import com.klashz.petProject.dto.PetAnimalDto
import com.klashz.petProject.dto.UserDto
import com.klashz.petProject.dto.response.PetAnimalDtoResponse
import com.klashz.petProject.pet.interfaces.IPetRepository
import com.klashz.petProject.pet.interfaces.IPetService
import com.klashz.petProject.user.interfaces.IUserRepository
import com.klashz.petProject.utils.Status
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class PetService(private val iPetRepository: IPetRepository, private val iUserRepository:IUserRepository) : IPetService{
    override fun getPetById(id: UUID): Optional<PetAnimalDto> {
        return iPetRepository.getPetById(id)
    }

    override fun getAllPet(): List<PetAnimalDtoResponse> {
        val lisResponsePetDto: List<PetAnimalDtoResponse> = iPetRepository.getAllPet()
            .map { petAnimalDto ->
                val user   = iUserRepository.getUserById(petAnimalDto.userId.toString())
                var userName : String = ""
                var userEmail : String = ""
                println(user)
                if(user.isPresent){
                    println(user.get())
                    userName = user.get().fullName
                    userEmail = user.get().email
                }

                PetAnimalDtoResponse(
                    contactPet = petAnimalDto.contactPet,
                    size = petAnimalDto.size,
                    raze = petAnimalDto.raze,
                    id = petAnimalDto.id,
                    userId = petAnimalDto.userId,
                    name = petAnimalDto.name,
                    age = petAnimalDto.age,
                    status = petAnimalDto.status,
                    imagePathUrl = petAnimalDto.imagePathUrl,
                    imageForPet = petAnimalDto.imageForPet,
                    description = petAnimalDto.description,
                    petType = petAnimalDto.petType,
                    userName = userName,
                    userEmail = userEmail
                )
            }
        return lisResponsePetDto
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