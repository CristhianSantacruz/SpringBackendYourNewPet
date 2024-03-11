package com.klashz.petProject.exceptions

class UserNotFoundException(dni:String) :
    RuntimeException("User with ID $dni not found") {
}