package com.klashz.petProject.exceptions

import jakarta.validation.constraints.Max
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.multipart.MaxUploadSizeExceededException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.io.FileNotFoundException


@ControllerAdvice
class FileManagerExceptionHandler : ResponseEntityExceptionHandler() {


    @ExceptionHandler(FileNotFoundException::class)
    fun handleFileNotFoundException(exc : FileNotFoundException) : ResponseEntity<String> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body("Archivo no se encuentra")
    }
    @ExceptionHandler( IllegalArgumentException::class)
    fun handleIllegalArgumentException(exc : IllegalArgumentException) : ResponseEntity<String>{
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("Solo se permite jpg y png")
    }
}