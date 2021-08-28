package com.flamagames.ping.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.flamagames.ping.utils.JwtGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OtherInjections {

    @Bean(name = ["jwtGenerator"])
    fun jwtGenerator(): JwtGenerator {
        return JwtGenerator()
    }

    @Bean(name = ["objectMapper"])
    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
    }
}