package com.flamagames.ping.business.person

import com.flamagames.ping.models.entities.Person

interface PersonBusiness {
    fun list(): List<Person>
    fun load(personId: Long): Person
    fun save(person: Person): Person
    fun remove(personId: Long)
}