package com.klashz.petProject.fileImage.interfaces

import com.klashz.petProject.dto.FileDto
import com.klashz.petProject.dto.response.ResponseFileDto
import com.klashz.petProject.fileImage.FileEntity
import org.springframework.web.multipart.MultipartFile
import java.util.*
import kotlin.jvm.Throws

interface IFileService {

    fun store(file:MultipartFile): FileDto
    fun getFile(id: UUID) : Optional<FileDto>
    fun getStores() : List<ResponseFileDto>
}