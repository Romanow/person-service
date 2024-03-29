package ru.romanow.inst.service.simple

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ru.romanow.inst.model.events.PersonChangedEvent
import ru.romanow.inst.model.events.PersonCreatedEvent
import ru.romanow.inst.model.events.PersonEvent
import ru.romanow.inst.model.events.PersonRemovedEvent
import ru.romanow.inst.service.LogNotificationService
import java.util.stream.Stream

@ExtendWith(MockitoExtension::class)
internal class LogNotificationServiceTest {

    @ParameterizedTest
    @MethodSource("factory")
    fun testNotify(event: PersonEvent) {
        mockStatic(LoggerFactory::class.java).use {
            val logger: Logger = mock(Logger::class.java)
            it.`when`<Logger> { LoggerFactory.getLogger(any(Class::class.java)) }
                .thenReturn(logger)

            LogNotificationService().notify(event)
            verify(logger, times(1)).info("Event: ${event.eventType}, changed fields ${event.changedFields}")
        }
    }

    companion object {
        @JvmStatic
        fun factory(): Stream<PersonEvent> =
            Stream.of(PersonCreatedEvent(arrayOf()), PersonChangedEvent(arrayOf()), PersonRemovedEvent())
    }
}
