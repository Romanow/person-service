package ru.romanow.inst.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.romanow.inst.domain.Person
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
            .orElseThrow { EntityNotFoundException("Person $id not found") }

    @Transactional(readOnly = true)
    override fun getPersons(): List<PersonResponse> =
        personRepository.findAll()
            .map { buildPersonResponse(it) }

    @Transactional
    override fun createPerson(request: PersonRequest): Int {
        TODO("not implemented")
    }

    @Transactional
    override fun editPerson(id: Int, request: PersonRequest): PersonResponse {
        TODO("not implemented")
    }

    @Transactional
    override fun deletePerson(id: Int) = personRepository.deleteById(id)

    private fun buildPersonResponse(person: Person) =
        PersonResponse(
            id = person.id,
            name = person.name,
            age = person.age,
            address = person.address,
            work = person.work
        )
}
