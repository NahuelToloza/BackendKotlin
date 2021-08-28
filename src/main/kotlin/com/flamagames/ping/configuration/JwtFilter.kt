package com.flamagames.ping.configuration

import com.flamagames.ping.configuration.WebSecurity.Companion.AUTHORIZATION_HEADER
import com.flamagames.ping.utils.JwtGenerator
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.annotation.Resource
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JwtFilter : GenericFilterBean() {

    @Resource
    private var jwtGenerator: JwtGenerator? = null

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, filter: FilterChain?) {
        if (request is HttpServletRequest) {
            val header = request.getHeader(AUTHORIZATION_HEADER)
            val token = jwtGenerator!!.revertUserJwt(header)
            val authentication = token?.let { UsernamePasswordAuthenticationToken(it, null) } ?: run { null }

            SecurityContextHolder.getContext().authentication = authentication

            filter!!.doFilter(request, response)
        }
    }
}
