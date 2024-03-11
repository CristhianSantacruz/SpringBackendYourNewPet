package com.klashz.petProject.user.interfaces

import com.klashz.petProject.user.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserJpaRepository : JpaRepository<UserEntity,String> {

    fun findByEmail(email:String) : Optional<UserEntity>;
}