package com.klashz.petProject.dto.response

import com.klashz.petProject.fileImage.FileEntity
import com.klashz.petProject.utils.Size
import com.klashz.petProject.utils.Status
import java.util.*

data class PetAnimalDtoResponse(

    val id: UUID?,
    var name : String?,
    val userId : String?,
    var age: String?,
    var raze : String?,
    var size : Size?,
    var status : Status?,
    val description : String,
    var petType: String?,
    var imagePathUrl : String?,
    val contactPet : String,
    val uriImage: String?

)

