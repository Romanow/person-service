package ru.romanow.inst.config

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import ru.romanow.inst.repository.PersonRepository
import ru.romanow.inst.service.PersonServiceImpl

@TestConfiguration
class PersonServiceTestConfiguration {

    @MockBean
    lateinit var personRepository: PersonRepository

    @Bean
    fun personService() = PersonServiceImpl(personRepository)
}