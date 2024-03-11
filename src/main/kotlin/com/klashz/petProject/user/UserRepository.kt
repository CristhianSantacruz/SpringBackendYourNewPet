package com.klashz.petProject.user

import com.klashz.petProject.dto.UserDto
import com.klashz.petProject.user.interfaces.IUserMapper
import com.klashz.petProject.user.interfaces.IUserRepository
import com.klashz.petProject.user.interfaces.UserJpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserRepository(private val iUserMapper: IUserMapper,
                     private val iUserJpaRepository: UserJpaRepository) : IUserRepository{
    override fun getUserById(dni: String): Optional<UserDto> {
       return  iUserJpaRepository.findById(dni).map { iUserMapper.toUserDto(it) }
    }

    override fun getUserByEmail(email: String): Optional<UserDto> {
        return iUserJpaRepository.findByEmail(email)
            .map { iUserMapper.toUserDto(it) }
    }

    override fun getAllUsers(): List<UserDto> {
        return iUserMapper.toUsersDtoList(iUserJpaRepository.findAll())
    }
    override fun saveUser(userDto: UserDto): UserDto {
        val userEntity:UserEntity = iUserMapper.toUserEntity(userDto)
        return iUserMapper.toUserDto(iUserJpaRepository.save(userEntity))
    }
    override fun deleteUser(dni: String) {
        iUserJpaRepository.deleteById(dni);
    }


}
