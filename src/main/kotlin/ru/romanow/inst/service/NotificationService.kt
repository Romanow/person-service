package ru.romanow.inst.service

import ru.romanow.inst.model.events.PersonEvent

interface NotificationService {
    fun notify(event: PersonEvent)
}