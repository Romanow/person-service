package ru.romanow.inst.service

import ru.romanow.inst.model.PersonRequest
import ru.romanow.inst.model.PersonResponse

interface PersonService {
    fun getPerson(id: Int): PersonResponse
    fun getPersons(): List<PersonResponse>
    fun createPerson(request: PersonRequest): Int
    fun editPerson(id: Int, request: PersonRequest): PersonResponse
    fun deletePerson(id: Int)
}
