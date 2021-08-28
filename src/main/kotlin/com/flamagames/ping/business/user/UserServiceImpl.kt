package com.flamagames.ping.business.user

import com.flamagames.ping.dao.UserRepository
import com.flamagames.ping.exception.NotFoundException
import com.flamagames.ping.models.configuration.Role
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class UserServiceImpl: UserService {

    @Autowired
    val userRepository: UserRepository? = null
    val repository: UserRepository
        get() = userRepository!!

    override fun loadUserByUsername(userName: String?): UserDetails {
        userName?.let {
            val user = repository.findByUserName(userName)
            return User(user.name, user.password, user.isActive, user.isActive, user.isActive, user.isActive, grantedRole(user.role))
        } ?: run {
            throw NotFoundException("The user is invalid")
        }
    }

    override fun grantedRole(role: Byte): List<GrantedAuthority> {
        return Role.getList().map { SimpleGrantedAuthority(it.name) }
    }
}