package ru.romanow.inst.service

import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import ru.romanow.inst.domain.Person
import ru.romanow.inst.model.PersonRequest
import ru.romanow.inst.repository.PersonRepository
import java.util.Optional.of
import kotlin.random.Random.Default.nextInt

@ExtendWith(MockitoExtension::class)
internal class PersonServiceTest {
    companion object {
        private const val PERSON_ID = 100
    }

    private val personRepository: PersonRepository = mock(PersonRepository::class.java)
    private val notificationService: NotificationService = mock(NotificationService::class.java)
    // private val notificationService: NotificationService = LogNotificationService()
    private val personService: PersonService = PersonServiceImpl(personRepository, notificationService)

    @Test
    fun getPerson() {
        // Given
        val person = buildPerson(PERSON_ID)
        `when`(personRepository.findById(PERSON_ID)).thenReturn(of(person))

        // When
        val response = personService.getPerson(PERSON_ID)

        // Than
        assertThat(response)
            .usingRecursiveComparison()
            .isEqualTo(person)
    }

    @Test
    fun getPersons() {
    }

    @Test
    fun createPerson() {
        // Given
        val request = PersonRequest(
            name = randomAlphabetic(8),
            age = nextInt(18, 60),
            address = randomAlphabetic(12),
            work = randomAlphabetic(8)
        )

        val person = Person(
            id = PERSON_ID,
            name = request.name,
            address = request.address,
            work = request.work,
            age = request.age
        )
        `when`(personRepository.save(any(Person::class.java))).thenReturn(person)

        // When
        val response = personService.createPerson(request)

        // Than
        assertThat(response).isEqualTo(PERSON_ID)
    }

    @Test
    fun editPerson() {
        // Given
        val person = buildPerson(PERSON_ID)
        `when`(personRepository.findById(PERSON_ID)).thenReturn(of(person))
        val request = PersonRequest(name = randomAlphabetic(8), address = randomAlphabetic(12))
        person.name = request.name
        person.address = request.address
        `when`(personRepository.save(eq(person))).thenReturn(person)

        // When
        val response = personService.editPerson(PERSON_ID, request)

        // Than
        assertThat(response)
            .usingRecursiveComparison()
            .isEqualTo(person)
    }

    @Test
    fun deletePerson() {
        // Given
        doNothing().`when`(personRepository).deleteById(PERSON_ID)

        // When
        personService.deletePerson(PERSON_ID)

        // Than
    }

    private fun buildPerson(id: Int) =
        Person(
            id = id,
            name = randomAlphabetic(8),
            address = randomAlphabetic(12),
            work = randomAlphabetic(8),
            age = nextInt(18, 60)
        )
}