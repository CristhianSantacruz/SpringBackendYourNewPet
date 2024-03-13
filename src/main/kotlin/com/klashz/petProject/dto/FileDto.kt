package com.klashz.petProject.dto


import com.fasterxml.jackson.annotation.JsonIgnore
import com.klashz.petProject.pet.PetAnimalEntity
import jakarta.persistence.Lob
import lombok.Builder
import java.util.*
@Builder
data class FileDto(
    val id: UUID?,
    val name : String?,
    val type : String?,
    @Lob
    val data : ByteArray?,
    @JsonIgnore
    val petAnimal:PetAnimalDto?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FileDto

        return data.contentEquals(other.data)
    }

    override fun hashCode(): Int {
        return data.contentHashCode()
    }
}

