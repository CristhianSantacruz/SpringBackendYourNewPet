package com.klashz.petProject.adoption

import com.klashz.petProject.adoption.interfaces.IAdoptionJpaRepository
import com.klashz.petProject.adoption.interfaces.IAdoptionMapper
import com.klashz.petProject.adoption.interfaces.IAdoptionRepository
import com.klashz.petProject.dto.AdoptionDto
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class AdoptionRepository(private val iAdoptionMapper: IAdoptionMapper,
    private val iAdoptionJpaRepository: IAdoptionJpaRepository) : IAdoptionRepository {
    override fun getAdoptionById(id: Long): Optional<AdoptionDto> {
        return iAdoptionJpaRepository.findById(id)
            .map { iAdoptionMapper.toAdoptionDto(it) }
    }

    override fun getAdoptionByUserId(dni: String): Optional<AdoptionDto> {
        return iAdoptionJpaRepository.findByAdoptedByUserId(dni)
            .map { iAdoptionMapper.toAdoptionDto(it) }
    }

    override fun getAllAdoption(): List<AdoptionDto> {
        return iAdoptionMapper.toAdoptionDtoList(iAdoptionJpaRepository.findAll())
    }

    override fun getAllAdoptionByUser(dni:String): List<AdoptionDto> {
        return  iAdoptionMapper.toAdoptionDtoList(iAdoptionJpaRepository.findAllByAdoptedByUserId(dni))
    }

    override fun saveAdoption(adoptionDto: AdoptionDto) : AdoptionDto {
        val adoptionEntity : AdoptionEntity = iAdoptionMapper.toAdoptionEntity(adoptionDto)
        return iAdoptionMapper.toAdoptionDto(iAdoptionJpaRepository.save(adoptionEntity))
    }

    override fun deleteAdoption(id: Long) {
        iAdoptionJpaRepository.deleteById(id)
    }
}