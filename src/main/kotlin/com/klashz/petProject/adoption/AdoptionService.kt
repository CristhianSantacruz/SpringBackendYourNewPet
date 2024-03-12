package com.klashz.petProject.adoption

import com.klashz.petProject.adoption.interfaces.IAdoptionRepository
import com.klashz.petProject.adoption.interfaces.IAdoptionService
import com.klashz.petProject.dto.AdoptionDto
import com.klashz.petProject.dto.response.AdoptionResponseDto
import com.klashz.petProject.exceptions.PetNotFoundException
import com.klashz.petProject.exceptions.UserNotFoundException
import com.klashz.petProject.pet.interfaces.IPetService
import com.klashz.petProject.user.interfaces.IUserService
import com.klashz.petProject.utils.Status
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class AdoptionService(private val iAdoptionRepository: IAdoptionRepository,
    private val iPetService: IPetService,
    private val iUserService:  IUserService) : IAdoptionService {
    override fun getAdoptionById(id: Long): Optional<AdoptionDto> {
        return iAdoptionRepository.getAdoptionById(id)
    }

    override fun getAllAdoption(): List<AdoptionDto> {
       return iAdoptionRepository.getAllAdoption()
    }

    override fun getAllAdoptionByUser(dni: String): List<AdoptionResponseDto> {
       val adoptionResponseList = iAdoptionRepository.getAllAdoptionByUser(dni)
           .map { adoption -> AdoptionResponseDto(
               adoption.petAdopted,adoption.adoptionDate
           ) }
       return adoptionResponseList
    }

    override fun saveAdoption(adoptionDto: AdoptionDto): AdoptionResponseDto {

      val userExits  = iUserService.getUserById(adoptionDto.adoptedByUserId)
      val petExits =iPetService.getPetById(adoptionDto.petId)
      if( userExits.isEmpty){
          throw  UserNotFoundException(adoptionDto.adoptedByUserId)
      }
      if(petExits.isEmpty){
          throw  PetNotFoundException(adoptionDto.petId)
      }
      val pet = petExits.get()
      pet.status = Status.PROCESO
      iPetService.updatePet(pet)
      iAdoptionRepository.saveAdoption(adoptionDto)
      return AdoptionResponseDto(pet, LocalDateTime.now())
    }


    override fun deleteAdoption(id: Long) {
        iAdoptionRepository.deleteAdoption(id)
    }

}