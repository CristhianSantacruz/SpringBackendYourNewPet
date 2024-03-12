package com.klashz.petProject.adoption.interfaces

import com.klashz.petProject.adoption.AdoptionEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface IAdoptionJpaRepository : JpaRepository<AdoptionEntity,Long> {

    fun findByAdoptedByUserId(dni:String) : Optional<AdoptionEntity>
}