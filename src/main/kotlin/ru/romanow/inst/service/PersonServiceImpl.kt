package ru.romanow.inst.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.romanow.inst.domain.Person
import ru.romanow.inst.exceptions.PersonAlreadyExists
import ru.romanow.inst.model.PersonRequest
import ru.romanow.inst.model.PersonResponse
import ru.romanow.inst.repository.PersonRepository
import javax.persistence.EntityNotFoundException

@Service
class PersonServiceImpl(
    private val personRepository: PersonRepository
) : PersonService {

    @Transactional(readOnly = true)
    override fun getPerson(id: Int): PersonResponse =
        personRepository.findById(id)
            .map { buildPersonResponse(it) }
            .orElseThrow { throw EntityNotFoundException("Person $id not found") }

    @Transactional(readOnly = true)
    override fun getPersons(): List<PersonResponse> =
        personRepository.findAll()
            .map { buildPersonResponse(it) }

    @Transactional
    override fun createPerson(request: PersonRequest): Int {
        if (personRepository.findByName(request.name).isPresent) {
            throw PersonAlreadyExists("Person ${request.name} already exists")
        }
        val person = Person(
            name = request.name,
            age = request.age,
            work = request.work,
            address = request.address
        )
        val saved = personRepository.save(person)
        return saved.id!!
    }

    @Transactional
    override fun editPerson(id: Int, request: PersonRequest): PersonResponse {
        val person = personRepository
            .findById(id)
            .orElseThrow { throw EntityNotFoundException("Person $id not found") }

        person.name = request.name
        person.age = request.age ?: person.age
        person.address = request.address ?: person.address
        person.work = request.work ?: person.work
        personRepository.save(person)
        return buildPersonResponse(person)
    }

    @Transactional
    override fun deletePerson(id: Int) = personRepository.deleteById(id)

    private fun buildPersonResponse(person: Person) =
        PersonResponse(
            id = person.id!!,
            name = person.name!!,
            age = person.age,
            address = person.address,
            work = person.work
        )
}
