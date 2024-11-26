package com.example.EvaM2.service

import com.example.EvaM2.dto.RegisterDto
import com.example.EvaM2.repository.UserRepository
import com.example.EvaM2.entity.UserEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserSecurityService: UserDetailsService {
    @Autowired
    lateinit var userRepository: UserRepository
    @Override
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails? {
        val userEntity = userRepository.findByUsername(username)
            ?: throw
            UsernameNotFoundException(
                "User $username not found."
            )

        val roles: Array<String?> = userEntity.roles?.map {
                role -> role.roles }!!.toTypedArray()

        return User.builder()
            .username(userEntity.username)
            .password(userEntity.password)
            .roles(*roles)
            .accountLocked(userEntity.locked!!)
            .disabled(userEntity.disabled!!)
            .build()
    }

    fun register(registerDto: RegisterDto):UserEntity{
        val newUserEntity = UserEntity()
        newUserEntity.apply {
            username = registerDto.username
            password = BCryptPasswordEncoder().encode(registerDto.password)
            email = registerDto.email
            locked = false
            disabled = false
        }
        userRepository.save(newUserEntity)

        return newUserEntity
    }

}



