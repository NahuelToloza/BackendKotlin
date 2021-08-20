package com.flamagames.ping.business

import com.flamagames.ping.dao.PersonRepository
import com.flamagames.ping.exception.BusinessException
import com.flamagames.ping.exception.NotFoundException
import com.flamagames.ping.models.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonBusinessImpl : PersonBusiness {

    @Autowired
    val personRepository: PersonRepository? = null
    val repository: PersonRepository
        get() = personRepository!!

    @Throws(BusinessException::class)
    override fun list(): List<Person> {
        try {
            return repository.findAll()
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun load(personId: Long): Person {
        val optional: Optional<Person>
        try {
            optional = repository.findById(personId)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }

        if (optional.isPresent.not()) {
            throw NotFoundException("The id: $personId don't exist in the database")
        }

        return optional.get()
    }

    @Throws(BusinessException::class)
    override fun save(person: Person): Person {
        try {
            return repository.save(person)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun remove(personId: Long) {
        val optional: Optional<Person>
        try {
            optional = repository.findById(personId)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }

        if (optional.isPresent) {
            try {
                repository.deleteById(personId)
            } catch (e: Exception) {
                throw BusinessException(e.message)
            }
        } else {
            throw NotFoundException("The id: $personId don't exist in the database")
        }
    }

}