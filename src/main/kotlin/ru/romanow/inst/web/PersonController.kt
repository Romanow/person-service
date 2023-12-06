package ru.romanow.inst.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.headers.Header
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import ru.romanow.inst.model.ErrorResponse
import ru.romanow.inst.model.PersonRequest
import ru.romanow.inst.model.PersonResponse
import ru.romanow.inst.model.ValidationErrorResponse
import ru.romanow.inst.service.PersonService

@Tag(name = "Person REST API operations")
@RestController
@RequestMapping("/api/v1/persons")
class PersonController(
    private val personService: PersonService
) {

    @Operation(
        summary = "Get Person by ID",
        responses = [
            ApiResponse(responseCode = "200", description = "Person for ID", content = [Content(schema = Schema(implementation = PersonResponse::class))]),
            ApiResponse(responseCode = "404", description = "Not found Person for ID", content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
        ]
    )
    @GetMapping("/{id}", produces = [APPLICATION_JSON_VALUE])
    fun getPerson(@PathVariable id: Int) = personService.getPerson(id)

    @Operation(
        summary = "Get all Persons",
        responses = [
            ApiResponse(responseCode = "200", description = "All Persons", content = [Content(array = ArraySchema(schema = Schema(implementation = PersonResponse::class)))])
        ]
    )
    @GetMapping(produces = [APPLICATION_JSON_VALUE])
    fun listPersons() = personService.getPersons()

    @Operation(
        summary = "Create new Person",
        responses = [
            ApiResponse(responseCode = "201", description = "Created new Person", headers = [Header(name = "Location", description = "Path to new Person")]),
            ApiResponse(responseCode = "400", description = "Invalid data", content = [Content(schema = Schema(implementation = ValidationErrorResponse::class))])
        ]
    )
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

    @Operation(
        summary = "Update Person by ID",
        responses = [
            ApiResponse(responseCode = "200", description = "Person for ID was updated", content = [Content(schema = Schema(implementation = PersonResponse::class))]),
            ApiResponse(responseCode = "400", description = "Invalid data", content = [Content(schema = Schema(implementation = ValidationErrorResponse::class))]),
            ApiResponse(responseCode = "404", description = "Not found Person for ID", content = [Content(schema = Schema(implementation = ErrorResponse::class))])
        ]
    )
    @PatchMapping("/{id}", consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun editPerson(@PathVariable id: Int, @Valid @RequestBody request: PersonRequest) =
        personService.editPerson(id, request)

    @Operation(
        summary = "Remove Person by ID",
        responses = [
            ApiResponse(responseCode = "204", description = "Person for ID was removed")
        ]
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun editPerson(@PathVariable id: Int) = personService.deletePerson(id)
}
