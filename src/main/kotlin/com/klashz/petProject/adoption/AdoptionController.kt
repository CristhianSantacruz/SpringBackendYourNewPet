package com.klashz.petProject.adoption

import com.klashz.petProject.adoption.interfaces.IAdoptionService
import com.klashz.petProject.dto.AdoptionDto
import com.klashz.petProject.dto.response.AdoptionResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/adopt")
class AdoptionController(private val iAdoptionService: IAdoptionService) {

    @PostMapping
    fun saveAdoption(@RequestBody adoptionDto: AdoptionDto): ResponseEntity<AdoptionResponseDto> {
        val adoptionSave = iAdoptionService.saveAdoption(adoptionDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(adoptionSave)
    }

    @GetMapping("/id/{id}")
    fun getAdoptionById(@PathVariable id: Long): ResponseEntity<AdoptionDto> {
        return ResponseEntity.of(iAdoptionService.getAdoptionById(id))
    }

    @GetMapping
    fun getAllAdoption(): ResponseEntity<List<AdoptionDto>> {
        println("ESTAMOS LLAMANDO A TODOS ")
        return ResponseEntity.ok(iAdoptionService.getAllAdoption())
    }

    @GetMapping("/{dni}")
    fun getAllAdoptionByUserId(@PathVariable dni: String): ResponseEntity<List<AdoptionResponseDto>> {
        println("ESTAMOS EN ESTE ENDPOINT")
        return ResponseEntity.ok(iAdoptionService.getAllAdoptionByUser(dni))
    }

    @DeleteMapping("/{id}")
    fun deletedAdoption(@PathVariable id: Long): ResponseEntity<String> {
        val adoption = when {
            iAdoptionService.getAdoptionById(id).isPresent -> ResponseEntity.ok("Eliminado")
            else -> {
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe")}
        }
        iAdoptionService.deleteAdoption(id)
        return adoption
    }
}

