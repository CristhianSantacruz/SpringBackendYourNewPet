package com.klashz.petProject.authenticationuser.interfaces

import com.klashz.petProject.dto.AuthCustomerDto
import com.klashz.petProject.dto.response.JwtResponseDto

interface IAuthService {
    fun signIn(authCustomerDto: AuthCustomerDto): JwtResponseDto

    fun signOut(jwt: String): JwtResponseDto
}