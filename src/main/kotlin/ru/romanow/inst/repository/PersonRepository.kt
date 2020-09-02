package ru.romanow.inst.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.romanow.inst.domain.Person

interface PersonRepository: JpaRepository<Person, Int>