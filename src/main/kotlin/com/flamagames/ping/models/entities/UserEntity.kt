package com.flamagames.ping.models.entities

import javax.persistence.*

@Entity
@Table(name = "user")
data class UserEntity(
    @Column(name = "name", unique = true) val name: String,
    @Column(name = "password") val password: String,
    @Column(name = "role") val role: Byte,
    @Column(name = "active") val isActive: Boolean
) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", unique = true)
    var id: Long = 0
}