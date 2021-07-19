package ru.romanow.inst.service

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import ru.romanow.inst.model.events.PersonEvent

@Profile("!heroku")
@Service
class LogNotificationService : NotificationService {
    private val logger = LoggerFactory.getLogger(LogNotificationService::class.java)

    override fun notify(event: PersonEvent) {
        logger.info(event.toString())
    }
}