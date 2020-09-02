package ru.romanow.inst.web

import org.springframework.web.bind.annotation.*
import ru.romanow.inst.model.PersonRequest
import ru.romanow.inst.service.PersonService
import javax.validation.Valid

@RestController
@RequestMapping("/persons")
class PersonController(
    private val personService: PersonService
) {

    @GetMapping("/{id}")
    fun getPerson(@PathVariable id: Int) = personService.getPerson(id)

    @GetMapping
    fun listPersons() = personService.getPersons()

    @PostMapping
    fun createPerson(@Valid @RequestBody request: PersonRequest) = personService.createPerson(request)

    @PatchMapping("/{id}")
    fun editPerson(@PathVariable id: Int, @Valid @RequestBody request: PersonRequest) = personService.editPerson(id, request)

    @DeleteMapping("/{id}")
    fun editPerson(@PathVariable id: Int) = personService.deletePerson(id)
}