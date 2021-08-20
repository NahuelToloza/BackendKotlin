package com.flamagames.ping.models

import javax.persistence.*

@Entity
@Table(name = "person")
data class Person(
    val dni: Long,
    val name: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
}