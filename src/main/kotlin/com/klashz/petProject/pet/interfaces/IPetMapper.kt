package com.klashz.petProject.pet.interfaces

import com.klashz.petProject.dto.PetAnimalDto
import com.klashz.petProject.pet.PetAnimalEntity
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper

@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface IPetMapper {

    fun toPetDto(petAnimalEntity: PetAnimalEntity) : PetAnimalDto;
    fun toPetEntity(petAnimalDto: PetAnimalDto) : PetAnimalEntity;
    fun toPetDtoList(petAnimalEntity: List<PetAnimalEntity>) : List<PetAnimalDto>
}