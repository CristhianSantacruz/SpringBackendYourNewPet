package com.klashz.petProject.user

import com.klashz.petProject.dto.UserDto
import com.klashz.petProject.security.Roles
import com.klashz.petProject.user.interfaces.IUserRepository
import com.klashz.petProject.user.interfaces.IUserService
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val iUserRepository: IUserRepository) :IUserService {
    override fun getUserById(dni: String): Optional<UserDto> {
        return iUserRepository.getUserById(dni);
    }

    override fun getUserByEmail(email: String): Optional<UserDto> {
        return iUserRepository.getUserByEmail(email);
    }

    override fun updateUser(userDto: UserDto): Optional<UserDto> {
        if(iUserRepository.getUserById(userDto.dni).isEmpty) {
            return Optional.empty();
        }else {
            return Optional.of(iUserRepository.saveUser(userDto));
        }
    }

    override fun getAllUsers(): List<UserDto> {
       return iUserRepository.getAllUsers();
    }

    override fun saveUser(userDto: UserDto): UserDto {
        return iUserRepository.saveUser(userDto.copy(rol = Roles.USER))
    }

    override fun deleteUser(dni: String){
       if(!iUserRepository.getUserById(dni).isEmpty){
           iUserRepository.deleteUser(dni);
       }
    }

}