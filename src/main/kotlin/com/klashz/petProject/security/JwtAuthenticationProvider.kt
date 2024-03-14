package com.klashz.petProject.security


import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.klashz.petProject.dto.UserDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtAuthenticationProvider {

    @Value("\${jwt.secret.key}")
    private lateinit var secretKey: String

    private val listToken: HashMap<String, UserDto> = HashMap()

    fun createToken(userDto: UserDto) : String{
        val dataNow = Date()
        val dataValidity = Date(dataNow.time + 3600000)
        val algorithm = Algorithm.HMAC256(secretKey);
        val tokenCreated = JWT.create()
            .withClaim("userId",userDto.dni)
            .withClaim("fullName",userDto.fullName)
            .withClaim("email",userDto.email)
            .withClaim("rol",userDto.rol)
            .withIssuedAt(dataNow)
            .withExpiresAt(dataValidity)
            .sign(algorithm);
        listToken[tokenCreated] = userDto
        return tokenCreated
    }
    @Throws(AuthenticationException::class)
    fun validateToken(token:String) : Authentication {

        println("Entro a validar el token")
        JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token)

        val existUser : UserDto = listToken[token] ?: throw BadCredentialsException("Usuario no registrado")
        val rolesAndAuthorities :   HashSet<SimpleGrantedAuthority> = HashSet()
        rolesAndAuthorities.add(SimpleGrantedAuthority("ROLE_" + existUser.rol))
        return UsernamePasswordAuthenticationToken(existUser,token,rolesAndAuthorities)
    }

    fun deleteToken(jwt:String) :String{
        if (!listToken.containsKey(jwt)) {
            return "No existe token"
        }

        listToken.remove(jwt)
        return "Sesión cerrada exitosamente"
    }
}