package ru.romanow.inst.service.simple

import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito.mock
import org.springframework.mail.javamail.JavaMailSender
import ru.romanow.inst.model.events.PersonChangedEvent
import ru.romanow.inst.model.events.PersonCreatedEvent
import ru.romanow.inst.model.events.PersonEvent
import ru.romanow.inst.model.events.PersonRemovedEvent
import ru.romanow.inst.service.EmailNotificationService
import java.util.stream.Stream
import java.util.stream.Stream.of

internal class EmailNotificationServiceTest {
    private lateinit var emailNotificationService: EmailNotificationService

    @BeforeEach
    fun init() {
        val mailSender = mock(JavaMailSender::class.java)
        emailNotificationService = EmailNotificationService(mailSender, "test@mail.ru")
    }

    @ParameterizedTest
    @MethodSource("factory")
    fun testNotify(event: PersonEvent) {
        assertThatNoException().isThrownBy { emailNotificationService.notify(event) }
    }

    companion object {
        @JvmStatic
        fun factory(): Stream<PersonEvent> =
            of(PersonCreatedEvent(arrayOf()), PersonChangedEvent(arrayOf()), PersonRemovedEvent())
    }
}
