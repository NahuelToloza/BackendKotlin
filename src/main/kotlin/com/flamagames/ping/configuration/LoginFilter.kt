package com.flamagames.ping.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.flamagames.ping.configuration.WebSecurity.Companion.AUTHORIZATION_HEADER
import com.flamagames.ping.models.configuration.UserSecurity
import com.flamagames.ping.utils.JwtGenerator
import com.flamagames.ping.utils.JwtGenerator.Companion.PREFIX_TOKEN
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import javax.annotation.Resource
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LoginFilter(
    url: String,
    authenticationManager: AuthenticationManager
) : AbstractAuthenticationProcessingFilter(url, authenticationManager) {

    @Resource
    private var objectMapper: ObjectMapper? = null
    private val mapper: ObjectMapper
        get() = objectMapper!!

    @Resource
    private var jwtGenerator: JwtGenerator? = null

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val body = request!!.inputStream

        val user = mapper.readValue(body, UserSecurity::class.java)

        val token = UsernamePasswordAuthenticationToken(
            user.user,
            user.password
        )
        return authenticationManager.authenticate(token)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        super.successfulAuthentication(request, response, chain, authResult)
        val token = jwtGenerator!!.generateUserJwt(authResult!!.name)
        response!!.addHeader(AUTHORIZATION_HEADER, "$PREFIX_TOKEN$token")
    }
}
