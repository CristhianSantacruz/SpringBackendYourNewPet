package com.klashz.petProject.adoption.interfaces

import com.klashz.petProject.adoption.AdoptionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*
import javax.swing.text.html.Option

interface IAdoptionJpaRepository : JpaRepository<AdoptionEntity,Long> {

    fun findByAdoptedByUserId(dni:String) : Optional<AdoptionEntity>
    @Query("select a from AdoptionEntity a where a.adoptedByUserId=:dni")
    fun findAllByAdoptedByUserId(dni:String) : List<AdoptionEntity>
}