package com.flamagames.ping.web

import com.flamagames.ping.business.person.PersonBusiness
import com.flamagames.ping.exception.BusinessException
import com.flamagames.ping.exception.NotFoundException
import com.flamagames.ping.models.entities.Person
import com.flamagames.ping.utils.Constants.Companion.URL_BASE_PERSONAS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(URL_BASE_PERSONAS)
class PersonRestController {

    @Autowired
    val personBusiness: PersonBusiness? = null
    val business: PersonBusiness
        get() = personBusiness!!

    @GetMapping("")
    fun list(): ResponseEntity<List<Person>> {
        return try {
            return ResponseEntity(business.list(), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun load(@PathVariable("id") personId: Long): ResponseEntity<Person> {
        return try {
            return ResponseEntity(business.load(personId), HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("")
    fun insert(@RequestBody person: Person): ResponseEntity<Any> {
        return try {
            business.save(person)
            val httpHeaders = HttpHeaders()
            httpHeaders.set("location", URL_BASE_PERSONAS + "/" + person.id)

            ResponseEntity(httpHeaders, HttpStatus.CREATED)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody person: Person): ResponseEntity<Any> {
        return try {
            business.save(person)
            ResponseEntity(HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") personId: Long): ResponseEntity<Any> {
        return try {
            business.remove(personId)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}