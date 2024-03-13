package com.klashz.petProject.fileImage.interfaces

import com.klashz.petProject.dto.FileDto
import com.klashz.petProject.fileImage.FileEntity
import jakarta.persistence.Lob
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import java.util.*

@Mapper(componentModel = "spring")

interface IFileMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "data", source = "data")
    @Mapping(target = "petAnimal", source = "petAnimal")
    fun toFileDto(fileEntity: FileEntity): FileDto
    @InheritInverseConfiguration
    fun toFileEntity(fileDto: FileDto): FileEntity
    fun toFileDtoList(fileEntityList: List<FileEntity>) : List<FileDto>
}