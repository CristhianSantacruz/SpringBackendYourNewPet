package com.klashz.petProject.config

import com.klashz.petProject.security.JwtAuthFilter
import com.klashz.petProject.security.JwtAuthenticationProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
class ApplicationConfig(private val jwtAuthenticationProvider: JwtAuthenticationProvider) {
    @Bean
    fun passwordEncoder() : PasswordEncoder {
        return BCryptPasswordEncoder()
    }
    @Bean
    fun  jwtAuthFilter() : JwtAuthFilter {
        return JwtAuthFilter(jwtAuthenticationProvider)
    }
}