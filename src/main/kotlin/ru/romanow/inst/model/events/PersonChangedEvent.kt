package ru.romanow.inst.model.events

class PersonChangedEvent(changedFields: Array<String>) : PersonEvent(EventType.CHANGED, changedFields)