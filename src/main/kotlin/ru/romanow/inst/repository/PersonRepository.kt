package ru.romanow.inst.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.romanow.inst.domain.Person
import java.util.*

interface PersonRepository : JpaRepository<Person, Int> {
    fun findByName(name: String): Optional<Person>
}
