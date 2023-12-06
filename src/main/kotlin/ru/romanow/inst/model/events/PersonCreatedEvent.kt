package ru.romanow.inst.model.events

class PersonCreatedEvent(changedFields: Array<String>) : PersonEvent(EventType.CREATED, changedFields)
