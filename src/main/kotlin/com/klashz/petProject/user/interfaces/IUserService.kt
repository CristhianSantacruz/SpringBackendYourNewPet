package com.klashz.petProject.user.interfaces

import com.klashz.petProject.dto.UserDto
import org.apache.catalina.User
import java.util.*

interface IUserService  {
    fun getUserById(dni:String) : Optional<UserDto>
    fun getUserByEmail(email:String) : Optional<UserDto>
    fun updateUser(userDto: UserDto) : Optional<UserDto>
    fun getAllUsers() : List<UserDto>
    fun saveUser(userDto: UserDto) : UserDto
    fun deleteUser(dni:String)

}