package com.klashz.petProject.security

import com.klashz.petProject.exceptions.UnauthorizedException
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthFilter(private val jwtAuthenticationProvider: JwtAuthenticationProvider) :
    OncePerRequestFilter() {

    private val urlsToSkip = listOf("/auth","/file","/user")


    @Throws(ServletException::class)
    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        println("en esta peticion se rompe")
        println(request.requestURI)
        return urlsToSkip.stream().anyMatch { url: String? ->
            request.requestURI.contains(
                url!!
            )
        }
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val header = request.getHeader(HttpHeaders.AUTHORIZATION) ?: throw UnauthorizedException();
        val authElements = header.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (authElements.size != 2 || "Bearer" != authElements.get(0)) {
            throw UnauthorizedException()
        }

        try {
            val auth = jwtAuthenticationProvider.validateToken(authElements[1])
            SecurityContextHolder.getContext().authentication = auth
        } catch (e: RuntimeException) {
            SecurityContextHolder.clearContext()
            println("se estalló")
            println(e)
            throw RuntimeException(e)
        }
        println("llegué aqui")

        filterChain.doFilter(request, response)
    }


}