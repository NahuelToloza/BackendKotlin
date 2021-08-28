package com.flamagames.ping.models.entities

import javax.persistence.*

@Entity
@Table(name = "person")
data class Person(
    val dni: Long,
    val name: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "person_id")
    var id: Long = 0
}