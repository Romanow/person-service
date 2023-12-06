package ru.romanow.inst.model

data class ValidationErrorResponse(
    override var message: String?,
    var errors: Map<String, String>?
) : ErrorResponse(message)
