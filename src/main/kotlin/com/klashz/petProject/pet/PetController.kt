package com.klashz.petProject.pet

import com.klashz.petProject.dto.PetAnimalDto
import com.klashz.petProject.pet.interfaces.IPetService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/pet")
class PetController(private val iPetService: IPetService) {

  @GetMapping()
  fun getAll() : ResponseEntity<List<PetAnimalDto>> {
      return ResponseEntity.ok(iPetService.getAllPet())
  }
  @GetMapping("/{id}")
  fun getById(@PathVariable id : UUID) : ResponseEntity<PetAnimalDto>{
      return ResponseEntity.of(iPetService.getPetById(id))
  }
}