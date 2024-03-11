package com.klashz.petProject.user.interfaces

import com.klashz.petProject.dto.UserDto
import com.klashz.petProject.user.UserEntity
import java.util.Optional
import javax.swing.text.html.Option

interface IUserRepository {

    fun getUserById(dni:String) :Optional<UserDto>
    fun getUserByEmail(email:String) : Optional<UserDto>
    fun getAllUsers() : List<UserDto>
    fun deleteUser(dni:String)
    fun saveUser(userDto: UserDto) : UserDto
}