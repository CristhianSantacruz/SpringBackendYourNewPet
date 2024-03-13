package com.klashz.petProject.fileImage

import com.klashz.petProject.dto.FileDto
import com.klashz.petProject.dto.response.ResponseFileDto
import com.klashz.petProject.fileImage.interfaces.IFileService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RestController
@RequestMapping("/file")
class FileController(val iFileService: IFileService) {

    @PostMapping("/upload")
    fun uploadFile(@RequestParam("file") file: MultipartFile): ResponseEntity<String> {
        iFileService.store(file)
        return ResponseEntity.ok("Archivo Subido Correctamente")
    }
    @GetMapping("/{id}")
    fun getFile(@PathVariable id : UUID) : ResponseEntity<ByteArray>{
        val fileDtoO : Optional<FileDto> = iFileService.getFile(id)
        if( fileDtoO.isPresent){
         val fileDto  = fileDtoO.get()
         return ResponseEntity.status(HttpStatus.OK)
             .header(HttpHeaders.CONTENT_TYPE, fileDto.type)
             .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + fileDto.name + "\"")
             .body(fileDto.data)
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }

    }

    @GetMapping
    fun getFiles() : ResponseEntity<List<ResponseFileDto>>{
        return ResponseEntity.ok(iFileService.getStores())
    }
}