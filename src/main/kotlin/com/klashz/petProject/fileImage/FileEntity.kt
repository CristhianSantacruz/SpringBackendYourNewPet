package com.klashz.petProject.fileImage

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "file_image")
data class FileEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID?,
    val name : String,
    val type : String,
    @Lob
    val data : ByteArray
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FileEntity

        return data.contentEquals(other.data)
    }

    override fun hashCode(): Int {
        return data.contentHashCode()
    }

}