package com.klashz.petProject.exceptions

import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class AccessDeniedHandlerException : AccessDeniedHandler {

    @Throws(IOException::class, ServletException::class)
    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        accessDeniedException: AccessDeniedException
    ) {
        throw AccessDeniedException(accessDeniedException.message)
    }
}