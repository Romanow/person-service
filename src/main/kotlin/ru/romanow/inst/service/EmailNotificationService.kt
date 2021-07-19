package ru.romanow.inst.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Profile
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import ru.romanow.inst.model.events.PersonEvent

@Profile("heroku")
@Service
class EmailNotificationService(
    private val mailSender: JavaMailSender,
    @Value("\${audit.user.mail}") private val auditUserMail: String
) : NotificationService {

    override fun notify(event: PersonEvent) {
        val message = SimpleMailMessage()
        message.setTo(auditUserMail)
        message.subject = "Audit mail"
        message.text = event.toString()
        mailSender.send(message)
    }
}