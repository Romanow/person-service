package ru.romanow.inst.web

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
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
    fun createPerson(@Valid @RequestBody request: PersonRequest): ResponseEntity<Void> {
        val id = personService.createPerson(request)
        return ResponseEntity.created(
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri()
        ).build()
    }

    @PatchMapping("/{id}")
    fun editPerson(@PathVariable id: Int, @Valid @RequestBody request: PersonRequest) =
        personService.editPerson(id, request)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun editPerson(@PathVariable id: Int) = personService.deletePerson(id)
}