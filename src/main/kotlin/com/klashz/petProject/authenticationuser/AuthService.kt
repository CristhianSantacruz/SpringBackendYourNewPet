package com.klashz.petProject.authenticationuser

import com.klashz.petProject.authenticationuser.interfaces.IAuthService
import com.klashz.petProject.dto.AuthCustomerDto
import com.klashz.petProject.dto.UserDto
import com.klashz.petProject.dto.response.JwtResponseDto
import com.klashz.petProject.exceptions.PasswordIncorrectException
import com.klashz.petProject.exceptions.UserNotExistException
import com.klashz.petProject.security.JwtAuthenticationProvider
import com.klashz.petProject.user.interfaces.IUserRepository

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import jakarta.transaction.Transactional
import java.util.*


@Service
class AuthService(private val iUserRepository: IUserRepository,
                  private val passwordEncoder: PasswordEncoder
                  ,private val jwtAuthenticationProvider: JwtAuthenticationProvider) : IAuthService {
    @Transactional
    override fun signIn(authCustomerDto: AuthCustomerDto): JwtResponseDto {
        val user: Optional<UserDto> = iUserRepository.getUserByEmail(authCustomerDto.email)
        if (user.isEmpty) {
            throw UserNotExistException()
        }

        if (!passwordEncoder.matches(authCustomerDto.password, user.get().password)){
            throw PasswordIncorrectException()
        }


        return JwtResponseDto(jwtAuthenticationProvider.createToken(user.get()))
    }
    override fun signOut(token: String): JwtResponseDto {
        val authElements = token.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return JwtResponseDto(jwtAuthenticationProvider.deleteToken(authElements[1]))
    }
}