package com.klashz.petProject.pet

import com.klashz.petProject.dto.PetAnimalDto
import com.klashz.petProject.pet.interfaces.IPetMapper
import com.klashz.petProject.pet.interfaces.IPetRepository
import com.klashz.petProject.pet.interfaces.PetJpaRepository
import org.springframework.stereotype.Repository
import java.util.*
@Repository
class PetRepository(private val iPetJpaRepository: PetJpaRepository,
                    private val iPetMapper: IPetMapper):IPetRepository {
    override fun getPetById(id: UUID): Optional<PetAnimalDto> {
        return iPetJpaRepository.findById(id).map { iPetMapper.toPetDto(it) }
    }

    override fun getAllPet(): List<PetAnimalDto> {
        return iPetMapper.toPetDtoList(iPetJpaRepository.findAll())
    }

    override fun savePet(petAnimalDto: PetAnimalDto): PetAnimalDto {
        val petAnimalEntity : PetAnimalEntity = iPetMapper.toPetEntity(petAnimalDto)
        return iPetMapper.toPetDto(iPetJpaRepository.save(petAnimalEntity))
    }

    override fun deletePet(id: UUID) {
        iPetJpaRepository.deleteById(id)
    }
}