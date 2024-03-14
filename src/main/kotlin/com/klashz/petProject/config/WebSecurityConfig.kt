package com.klashz.petProject.config

import com.klashz.petProject.exceptions.AccessDeniedHandlerException
import com.klashz.petProject.security.JwtAuthFilter
import com.klashz.petProject.security.Roles
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.*
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.*

import org.springframework.security.config.Customizer.withDefaults

@Configuration
@EnableWebSecurity
class WebSecurityConfig(
    private val jwtAuthFilter: JwtAuthFilter,
    private val accessDeniedHandlerException: AccessDeniedHandlerException
) {

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? {
        http
            .cors(Customizer.withDefaults<CorsConfigurer<HttpSecurity>>())
            .exceptionHandling { exception: ExceptionHandlingConfigurer<HttpSecurity?> ->
                exception.accessDeniedHandler(
                    accessDeniedHandlerException
                )
            }
            .csrf { crf: CsrfConfigurer<HttpSecurity> -> crf.disable() }
            .sessionManagement { session: SessionManagementConfigurer<HttpSecurity?> ->
                session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            }
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
            .authorizeHttpRequests { requests ->
                requests
                    .requestMatchers(
                        "/auth/**",
                    ).permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/user/**").hasRole(Roles.ADMIN)
                    .requestMatchers(HttpMethod.DELETE, "/pet/**").hasRole(Roles.ADMIN)
                    .requestMatchers(HttpMethod.DELETE, "/adopt/**").hasRole(Roles.ADMIN)
                    .requestMatchers(HttpMethod.DELETE, "/file/**").hasRole(Roles.ADMIN)
                    .requestMatchers(HttpMethod.DELETE, "/pet/**").hasRole(Roles.ADMIN)
                    .requestMatchers(HttpMethod.GET, "/pet/**").hasAnyRole(Roles.USER, Roles.ADMIN)
                    .requestMatchers(HttpMethod.POST, "/pet/**").hasAnyRole(Roles.USER, Roles.ADMIN)
                    .requestMatchers(HttpMethod.PUT, "/pet/**").hasAnyRole(Roles.USER, Roles.ADMIN)
                    .requestMatchers(HttpMethod.GET, "/user/**").hasAnyRole(Roles.USER, Roles.ADMIN)
                    .requestMatchers(HttpMethod.POST, "/user/**").hasAnyRole(Roles.USER, Roles.ADMIN)
                    .requestMatchers(HttpMethod.PUT,"/user/**").hasAnyRole(Roles.USER,Roles.ADMIN)
                    .requestMatchers(HttpMethod.GET, "/file/**").hasAnyRole(Roles.USER, Roles.ADMIN)
                    .requestMatchers(HttpMethod.POST, "/file/**").hasAnyRole(Roles.USER, Roles.ADMIN)
                    .requestMatchers(HttpMethod.GET, "/adopt/**").hasAnyRole(Roles.USER, Roles.ADMIN)
                    .requestMatchers(HttpMethod.POST, "/adopt/**").hasAnyRole(Roles.USER, Roles.ADMIN)

                    .anyRequest().authenticated()
            }





        return http.build()
    }


    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration: CorsConfiguration = CorsConfiguration()
        configuration.allowedOrigins = listOf(CorsConfiguration.ALL)
        configuration.allowedMethods = listOf(CorsConfiguration.ALL)
        configuration.allowedHeaders = listOf(CorsConfiguration.ALL)
        val source: UrlBasedCorsConfigurationSource = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}