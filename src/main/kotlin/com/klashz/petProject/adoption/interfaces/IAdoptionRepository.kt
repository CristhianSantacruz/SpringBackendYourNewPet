package com.klashz.petProject.adoption.interfaces

import com.klashz.petProject.dto.AdoptionDto
import java.util.Optional

interface IAdoptionRepository {
   fun getAdoptionById(id:Long) : Optional<AdoptionDto>
   fun getAdoptionByUserId(dni:String) : Optional<AdoptionDto>
   fun getAllAdoption() : List<AdoptionDto>
   fun getAllAdoptionByUser(dni:String) : List<AdoptionDto>
   fun saveAdoption(adoptionDto: AdoptionDto): AdoptionDto
   fun deleteAdoption(id:Long)
}