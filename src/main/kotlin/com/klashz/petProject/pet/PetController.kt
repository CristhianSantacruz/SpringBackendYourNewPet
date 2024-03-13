package com.klashz.petProject.pet

import com.klashz.petProject.dto.PetAnimalDto
import com.klashz.petProject.dto.response.PetAnimalDtoResponse
import com.klashz.petProject.pet.interfaces.IPetService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
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
  @PostMapping()
  fun saveUser(@RequestBody petAnimalDto: PetAnimalDto) : ResponseEntity<PetAnimalDto>{
      return ResponseEntity.status(HttpStatus.CREATED).body(iPetService.savePet(petAnimalDto))
  }
  @PutMapping()
  fun updateUse(@RequestBody petAnimalDto: PetAnimalDto) : ResponseEntity<PetAnimalDto>{
      return ResponseEntity.of(iPetService.updatePet(petAnimalDto))
  }
  @DeleteMapping("/delete/{id}")
  fun deletePet(@PathVariable id : UUID) {
      iPetService.deletePet(id)
  }
}