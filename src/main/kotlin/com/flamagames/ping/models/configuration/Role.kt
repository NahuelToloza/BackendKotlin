package com.flamagames.ping.models.configuration

enum class Role {
    READER, USER, ADMIN;

    companion object {
        fun getList(): List<Role> {
            return values().toList()
        }
    }
}