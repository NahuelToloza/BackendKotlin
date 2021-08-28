package com.flamagames.ping

import com.flamagames.ping.dao.PersonRepository
import com.flamagames.ping.models.entities.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class PingApplication : CommandLineRunner {

    @Autowired
    val repository: PersonRepository? = null

    override fun run(vararg args: String?) {
        val person = Person(
            dni = 40768954,
            name = "Nahu"
        )
        repository!!.save(person)
    }
}

fun main(args: Array<String>) {
    runApplication<PingApplication>(*args)
}
