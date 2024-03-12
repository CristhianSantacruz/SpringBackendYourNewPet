package com.klashz.petProject.adoption.interfaces

import com.klashz.petProject.adoption.AdoptionEntity
import com.klashz.petProject.dto.AdoptionDto
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface IAdoptionMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "adoptedByUserId", source = "adoptedByUserId")
    @Mapping(target = "petId", source = "petId")
    @Mapping(target = "adoptionDate", source = "adoptionDate")
    fun toAdoptionDto(adoptionEntity: AdoptionEntity) : AdoptionDto
    @Mapping(target = "adoptedByUser", ignore = true)
   // @Mapping(target = "petAdopted", ignore = true)
    @InheritInverseConfiguration
    fun toAdoptionEntity(adoptionDto: AdoptionDto) : AdoptionEntity
    fun toAdoptionDtoList(adoptionEntity:List< AdoptionEntity>) :List<AdoptionDto>
}