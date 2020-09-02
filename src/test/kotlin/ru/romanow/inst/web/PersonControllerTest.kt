package ru.romanow.inst.web

import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import org.apache.commons.lang3.RandomUtils.nextInt
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import ru.romanow.inst.config.PersonServiceTestConfiguration
import ru.romanow.inst.domain.Person
import ru.romanow.inst.repository.PersonRepository
import java.util.*
import java.util.Optional.of

@ExtendWith(SpringExtension::class)
@WebMvcTest(controllers = [PersonController::class])
@Import(PersonServiceTestConfiguration::class)
internal class PersonControllerTest {
    companion object {
        private const val PERSON_ID = 1
    }

    @Autowired
    lateinit var personRepository: PersonRepository

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun testGetPersonsSuccess() {
        `when`(personRepository.findById(PERSON_ID))
            .thenReturn(buildPerson(PERSON_ID))

        mockMvc.perform(get("/persons/$PERSON_ID"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    }

    private fun buildPerson(id: Int): Optional<Person> =
        of(
            Person(
                id = id,
                name = randomAlphabetic(10),
                age = nextInt(10, 30),
                address = randomAlphabetic(10),
                work = randomAlphabetic(10)
            )
        )
}