package com.klashz.petProject.fileImage.interfaces

import com.klashz.petProject.fileImage.FileEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID
@Repository
interface IFileJpaRepository  : JpaRepository<FileEntity,UUID>{
}