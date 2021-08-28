package com.flamagames.ping.configuration

import com.flamagames.ping.business.user.UserService
import com.flamagames.ping.exception.LoginNullPointException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class WebSecurity : WebSecurityConfigurerAdapter() {

    @Autowired
    var userService: UserService? = null
    val service: UserService
        get() = userService!!


    override fun configure(auth: AuthenticationManagerBuilder?) {
        //TODO - Revisar que onda passwordEncoder
        try {
            super.configure(auth)
            auth!!.userDetailsService(service)
        } catch (e: Exception) {
            throw LoginNullPointException()
        }
    }

    override fun configure(http: HttpSecurity?) {
        try {
            super.configure(http)
            http!!.csrf().disable().authorizeRequests()
                .antMatchers(LOGIN_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(
                    LoginFilter(LOGIN_URL, authenticationManager()),
                    UsernamePasswordAuthenticationFilter::class.java
                )
                .addFilterBefore(
                    JwtFilter(),
                    UsernamePasswordAuthenticationFilter::class.java
                )
        } catch (e: Exception) {
            throw LoginNullPointException()
        }
    }

    companion object{
        private const val LOGIN_URL = "/login"
        const val AUTHORIZATION_HEADER = "Authorization"
    }
}