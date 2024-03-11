package com.klashz.petProject.user

import com.klashz.petProject.dto.UserDto
import com.klashz.petProject.user.interfaces.IUserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customer")
class UserController(private val iUserService: IUserService) {


    @PostMapping
    fun save(@RequestBody userDto: UserDto): ResponseEntity<UserDto> {
        val user = iUserService.saveUser(userDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(user)
    }

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserDto>> {
        return ResponseEntity.ok(iUserService.getAllUsers())
    }

    @GetMapping("/id/{dni}")
    fun getUserById(@PathVariable dni: String): ResponseEntity<UserDto> {
        return ResponseEntity.of(iUserService.getUserById(dni))
    }

    @GetMapping("/{email}")
    fun getUserByEmail(@PathVariable email: String): ResponseEntity<UserDto> {
        return ResponseEntity.of(iUserService.getUserByEmail(email))
    }

    @PutMapping
    fun update(@RequestBody userDto: UserDto): ResponseEntity<UserDto> {
        return ResponseEntity.of(iUserService.updateUser(userDto))
    }
    @DeleteMapping("/{dni}")
    fun delete(@PathVariable dni: String): ResponseEntity<String> {
        return if (iUserService.getUserById(dni).isEmpty) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO EXISTE")
        } else {
            iUserService.deleteUser(dni)
            ResponseEntity.ok("ELIMINADO")
        }
    }
}

