package com.flamagames.ping.business

import com.flamagames.ping.models.Person

interface PersonBusiness {
    fun list(): List<Person>
    fun load(personId: Long): Person
    fun save(person: Person): Person
    fun remove(personId: Long)
}