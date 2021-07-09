package ru.romanow.inst.service

import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import ru.romanow.inst.domain.Person
import ru.romanow.inst.model.PersonRequest
import ru.romanow.inst.model.events.PersonChangedEvent
import ru.romanow.inst.model.events.PersonCreatedEvent
import ru.romanow.inst.model.events.PersonRemovedEvent
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
    private val personService: PersonService = PersonServiceImpl(personRepository, notificationService)

    @Test
    @DisplayName("given: person in DB; when: find person by ID; then: return person")
    fun getPerson() {
        // given
        val person = buildPerson(PERSON_ID)
        `when`(personRepository.findById(PERSON_ID)).thenReturn(of(person))

        // when
        val response = personService.getPerson(PERSON_ID)

        // than
        assertThat(response)
            .usingRecursiveComparison()
            .isEqualTo(person)
    }

    @Test
    fun getPersons() {
        // given
        val persons = listOf(
            buildPerson(PERSON_ID),
            buildPerson(PERSON_ID + 1),
            buildPerson(PERSON_ID + 2)
        )
        `when`(personRepository.findAll()).thenReturn(persons)

        // when
        val response = personService.getPersons()

        // than
        assertThat(response).hasSameSizeAs(persons)
        for (item in response) {
            assertThat(item)
                .usingRecursiveComparison()
                .isEqualTo(persons.find { it.id == item.id })
        }
    }

    @Test
    fun createPerson() {
        // given
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
        `when`(personRepository.save(any(Person::class.java)))
            .thenReturn(person)

        // when
        val response = personService.createPerson(request)

        // than
        assertThat(response).isEqualTo(PERSON_ID)
        verify(notificationService, times(1)).notify(this.any(PersonCreatedEvent::class.java))
    }

    @Test
    fun editPerson() {
        // given
        val person = buildPerson(PERSON_ID)
        `when`(personRepository.findById(PERSON_ID)).thenReturn(of(person))
        val request = PersonRequest(name = randomAlphabetic(8), address = randomAlphabetic(12))
        person.name = request.name
        person.address = request.address
        `when`(personRepository.save(eq(person))).thenReturn(person)

        // when
        val response = personService.editPerson(PERSON_ID, request)

        // than
        assertThat(response)
            .usingRecursiveComparison()
            .isEqualTo(person)
        verify(notificationService, times(1)).notify(this.any(PersonChangedEvent::class.java))
    }

    @Test
    fun deletePerson() {
        // given
        doNothing().`when`(personRepository).deleteById(PERSON_ID)

        // when
        personService.deletePerson(PERSON_ID)

        // than
        verify(notificationService, times(1)).notify(this.any(PersonRemovedEvent::class.java))
    }

    private fun buildPerson(id: Int) =
        Person(
            id = id,
            name = randomAlphabetic(8),
            address = randomAlphabetic(12),
            work = randomAlphabetic(8),
            age = nextInt(18, 60)
        )

    private fun <T> any(): T = Mockito.any()
    private fun <T> any(cls: Class<T>): T = Mockito.any(cls)
}