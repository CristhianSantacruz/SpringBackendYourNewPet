package com.klashz.petProject.authenticationuser

import com.klashz.petProject.authenticationuser.interfaces.IAuthService
import com.klashz.petProject.dto.AuthCustomerDto
import com.klashz.petProject.dto.UserDto
import com.klashz.petProject.dto.response.JwtResponseDto
import com.klashz.petProject.dto.response.UserDtoResponse
import com.klashz.petProject.user.interfaces.IUserService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/auth")
class AuthController(private val iAuthService: IAuthService,
    private val iUserService: IUserService) {

    @PostMapping("/register")
    fun save(@RequestBody userDto: UserDto) : ResponseEntity<UserDtoResponse> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(iUserService.saveUser(userDto))
    }

    @PostMapping("/sign-in")
    fun signIn(@RequestBody userDto: AuthCustomerDto): ResponseEntity<JwtResponseDto> {
        return ResponseEntity.ok(iAuthService.signIn(userDto))
    }

    @PostMapping("/sign-out")
    fun signOut(@RequestHeader(name = HttpHeaders.AUTHORIZATION) jwt: String): ResponseEntity<JwtResponseDto> {
        return ResponseEntity.ok(iAuthService.signOut(jwt))
    }
}