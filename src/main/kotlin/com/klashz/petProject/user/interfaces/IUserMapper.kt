package com.klashz.petProject.user.interfaces

import com.klashz.petProject.dto.UserDto
import com.klashz.petProject.user.UserEntity
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface IUserMapper {
    @Mapping(target = "dni", source = "dni")
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "rol", source = "rol")
    fun toUserDto(userEntity: UserEntity): UserDto

    @Mapping(target = "dni", source = "dni")
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "rol", source = "rol")
    fun toUserEntity(userDto: UserDto): UserEntity

    fun toUsersDtoList(userEntities: List<UserEntity>): List<UserDto>
}