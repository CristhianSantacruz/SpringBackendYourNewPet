package com.klashz.petProject.dto

import lombok.Getter
import lombok.Setter

@Getter
@Setter
data class AuthCustomerDto(
    val email: String,

     val password: String
) {

}