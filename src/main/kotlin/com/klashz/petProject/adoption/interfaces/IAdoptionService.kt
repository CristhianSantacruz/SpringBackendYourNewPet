package com.klashz.petProject.adoption.interfaces

import com.klashz.petProject.dto.AdoptionDto
import com.klashz.petProject.dto.response.AdoptionResponseDto
import java.util.*

interface IAdoptionService {
    fun getAdoptionById(id:Long) : Optional<AdoptionDto>
    fun getAllAdoption() : List<AdoptionDto>
    fun getAllAdoptionByUser(dni:String) : List<AdoptionResponseDto>
    fun saveAdoption(adoptionDto: AdoptionDto): AdoptionResponseDto
    fun deleteAdoption(id:Long)
}