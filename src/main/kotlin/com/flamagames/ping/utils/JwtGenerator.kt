package com.flamagames.ping.utils

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm

class JwtGenerator {
    fun generateUserJwt(userName: String): String? {
        return Jwts.builder()
            .setSubject(userName)
            .signWith(SignatureAlgorithm.HS512, System.getenv(EnvironmentVariable.JWT_GENERATOR_KEY.name))
            .compact()
    }

    fun revertUserJwt(header: String): String? {
        if (header.isNotEmpty()) {
            return Jwts.parser()
                .setSigningKey(System.getenv(EnvironmentVariable.JWT_GENERATOR_KEY.name))
                .parseClaimsJws(header.replace(PREFIX_TOKEN, ""))
                .body
                .subject
        }

        return null
    }

    companion object {
        const val PREFIX_TOKEN = "Bearer "
    }
}