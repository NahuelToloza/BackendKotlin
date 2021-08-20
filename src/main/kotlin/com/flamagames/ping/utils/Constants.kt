package com.flamagames.ping.utils

class Constants {
    companion object{
        private const val URL_API_BASE = "/api"
        private const val URL_API_VERSIONS = "/v1"
        private const val URL_BASE = URL_API_BASE + URL_API_VERSIONS
        const val URL_BASE_PERSONAS = "$URL_BASE/persons"
    }
}