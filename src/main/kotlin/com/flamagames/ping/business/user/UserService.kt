package com.flamagames.ping.business.user

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetailsService

interface UserService: UserDetailsService{
    fun grantedRole(role: Byte): List<GrantedAuthority>
}