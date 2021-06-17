package ru.romanow.inst.service

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import ru.romanow.inst.model.events.PersonEvent

@Profile("prod")
@Service
class EmailNotificationService : NotificationService {
    override fun notify(event: PersonEvent) {
        throw NotImplementedError("EmailNotificationService :: notify not implemented yet")
    }
}