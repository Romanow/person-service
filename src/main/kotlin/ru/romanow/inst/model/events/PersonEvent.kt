package ru.romanow.inst.model.events

abstract class PersonEvent(
    val eventType: EventType,
    val changedFields: Array<String>
)