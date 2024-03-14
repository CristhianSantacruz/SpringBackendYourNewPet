package com.klashz.petProject.user

import com.klashz.petProject.dto.PetAnimalDto
import com.klashz.petProject.dto.UserDto
import com.klashz.petProject.dto.response.UserDtoResponse
import com.klashz.petProject.exceptions.UserNotFoundException
import com.klashz.petProject.security.Roles
import com.klashz.petProject.user.interfaces.IUserRepository
import com.klashz.petProject.user.interfaces.IUserService
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val iUserRepository: IUserRepository,
    private val passwordEncoder: PasswordEncoder) :IUserService {

    @Transactional
    override fun getUserById(dni: String): Optional<UserDto> {
        return iUserRepository.getUserById(dni);
    }
    @Transactional
    override fun getUserByEmail(email: String): Optional<UserDto> {
        return iUserRepository.getUserByEmail(email);
    }

    override fun getPetsByUser(dni: String): List<PetAnimalDto>? {
        val userDtoOptional: Optional<UserDto> = iUserRepository.getUserById(dni)
        if (userDtoOptional.isPresent) {
            val userDto: UserDto = userDtoOptional.get()
            return userDto.petAnimalListRegister ?: emptyList()
        } else {
            throw UserNotFoundException(dni)
        }
    }
    @Transactional
    override fun updateUser(userDto: UserDto): Optional<UserDto> {
        return if(iUserRepository.getUserById(userDto.dni).isEmpty) {
            Optional.empty();
        }else {
            val passwordUser : String = userDto.password
            Optional.of(iUserRepository.saveUser(userDto.copy(password = passwordEncoder.encode(passwordUser))));
        }
    }
    @Transactional
    override fun getAllUsers(): List<UserDto> {
       return iUserRepository.getAllUsers();
    }

    override fun saveUser(userDto: UserDto): UserDtoResponse {
        val user : UserDto =  iUserRepository.saveUser(userDto.copy(rol = Roles.USER, password = passwordEncoder.encode(userDto.password)))
        println("USER QUE SE GUARDA CON CONTRASENIA ENCRYPTADA $user")
        return UserDtoResponse(user.dni,user.fullName,user.email,user.phone,user.rol)
    }

    override fun deleteUser(dni: String){
       if(!iUserRepository.getUserById(dni).isEmpty){
           iUserRepository.deleteUser(dni);
       }
    }

}