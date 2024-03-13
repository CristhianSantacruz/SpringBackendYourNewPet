package com.klashz.petProject.fileImage

import com.klashz.petProject.dto.FileDto
import com.klashz.petProject.dto.response.ResponseFileDto
import com.klashz.petProject.fileImage.interfaces.IFileJpaRepository
import com.klashz.petProject.fileImage.interfaces.IFileMapper
import com.klashz.petProject.fileImage.interfaces.IFileService
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.io.FileNotFoundException
import java.io.IOException
import java.util.*

@Service
class FileService(
    private val iFileJpaRepository: IFileJpaRepository,
    private val iFileMapper: IFileMapper)
    :IFileService{


    override fun store(file: MultipartFile): FileDto {
        val allowedTypes = setOf(MediaType.IMAGE_JPEG, MediaType.IMAGE_PNG) // Tipos de medios permitidos
        val contentType : MediaType? = file.contentType?.let { MediaType.parseMediaType(it) }

        if (!allowedTypes.contains(contentType)) {
            throw IllegalArgumentException()
        }
        val fileName: String? = file.originalFilename?.let { StringUtils.cleanPath(it) }
        val fileDto: FileDto = FileDto(null,name = fileName, contentType?.toString(), file.bytes)
        iFileJpaRepository.save(iFileMapper.toFileEntity(fileDto))
        return fileDto
    }
    @Throws(FileNotFoundException::class)
    override fun getFile(id: UUID): Optional<FileDto> {
        val file :Optional<FileEntity> = iFileJpaRepository.findById(id)
        if(file.isPresent){
            return Optional.of(iFileMapper.toFileDto(file.get()))
        }
        return throw FileNotFoundException()
    }

    override fun getStores(): List<ResponseFileDto> {
        val listFileResponseFileDto : List<ResponseFileDto> =
            iFileJpaRepository.findAll().map { fileEntity ->
                val fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/file/")
                    .path(fileEntity.id.toString())
                    .toUriString()
                ResponseFileDto(
                fileEntity.name ,fileDownloadUri ,fileEntity.type , fileEntity.data.size
            )  }
        return listFileResponseFileDto
    }
}