package com.klashz.petProject.adoption.interfaces

import com.klashz.petProject.dto.AdoptionDto
import java.util.*

interface IAdoptionService {
    fun getAdoptionById(id:Long) : Optional<AdoptionDto>
    fun getAdoptionByUserId(dni:String) : Optional<AdoptionDto>
    fun getAllAdoption() : List<AdoptionDto>
    fun saveAdoption(adoptionDto: AdoptionDto): AdoptionDto
    fun updateAdoption(adoptionDto: AdoptionDto): Optional<AdoptionDto>
    fun deleteAdoption(id:Long)
}