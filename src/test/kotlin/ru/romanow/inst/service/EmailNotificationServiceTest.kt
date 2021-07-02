package ru.romanow.inst.service

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import ru.romanow.inst.model.events.PersonChangedEvent
import ru.romanow.inst.model.events.PersonCreatedEvent
import ru.romanow.inst.model.events.PersonEvent
import ru.romanow.inst.model.events.PersonRemovedEvent
import java.util.stream.Stream
import java.util.stream.Stream.of

internal class EmailNotificationServiceTest {
    private val emailNotificationService = EmailNotificationService()

    @ParameterizedTest
    @MethodSource("factory")
    fun testNotify(event: PersonEvent) {
        assertThrows(NotImplementedError::class.java) { emailNotificationService.notify(event) }
    }

    companion object {
        @JvmStatic
        fun factory(): Stream<PersonEvent> =
            of(PersonCreatedEvent(arrayOf()), PersonChangedEvent(arrayOf()), PersonRemovedEvent())
    }
}