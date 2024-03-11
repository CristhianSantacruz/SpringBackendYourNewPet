package com.klashz.petProject.pet.interfaces

import com.klashz.petProject.pet.PetAnimalEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PetJpaRepository : JpaRepository<PetAnimalEntity,UUID> {
}