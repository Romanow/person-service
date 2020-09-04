package ru.romanow.inst.web

import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
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

    @GetMapping("/{id}", produces = [APPLICATION_JSON_VALUE])
    fun getPerson(@PathVariable id: Int) = personService.getPerson(id)

    @GetMapping(produces = [APPLICATION_JSON_VALUE])
    fun listPersons() = personService.getPersons()

    @PostMapping(consumes = [APPLICATION_JSON_VALUE])
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

    @PatchMapping("/{id}", consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun editPerson(@PathVariable id: Int, @Valid @RequestBody request: PersonRequest) =
        personService.editPerson(id, request)

    @DeleteMapping("/{id}")
    fun editPerson(@PathVariable id: Int) = personService.deletePerson(id)
}