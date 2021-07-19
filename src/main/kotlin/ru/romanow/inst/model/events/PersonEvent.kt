package ru.romanow.inst.model.events

abstract class PersonEvent(
    val eventType: EventType,
    val changedFields: Array<String>
) {
    override fun toString(): String {
        return "Event: $eventType, changed fields $changedFields"
    }
}