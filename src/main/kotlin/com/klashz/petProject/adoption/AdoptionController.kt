package com.klashz.petProject.adoption

import com.klashz.petProject.adoption.interfaces.IAdoptionService
import com.klashz.petProject.dto.AdoptionDto
import com.klashz.petProject.dto.AdoptionResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/adopt")
class AdoptionController(private val iAdoptionService: IAdoptionService) {

    @PostMapping()
    fun saveAdoption(@RequestBody adoptionDto: AdoptionDto): ResponseEntity<AdoptionResponseDto>  {
        val adoptionSave = iAdoptionService.saveAdoption(adoptionDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(adoptionSave)
    }
}