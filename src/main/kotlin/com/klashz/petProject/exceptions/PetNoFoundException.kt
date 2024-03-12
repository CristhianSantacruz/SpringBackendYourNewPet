package com.klashz.petProject.exceptions

import java.util.*

class PetNotFoundException(id: UUID) :
    RuntimeException("User with ID $id not found") {
}