package com.klashz.petProject.fileImage

import com.fasterxml.jackson.annotation.JsonIgnore
import com.klashz.petProject.pet.PetAnimalEntity
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "file_image")
data class FileEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID?,
    val name : String,
    @JsonIgnore
    val type : String,
    @JsonIgnore
    @Lob
    val data : ByteArray,
    @OneToOne
    @JoinColumn(name = "pet_animal_id")
    @JsonIgnore
    val petAnimal: PetAnimalEntity?

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