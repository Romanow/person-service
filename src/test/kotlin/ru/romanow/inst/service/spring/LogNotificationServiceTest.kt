package ru.romanow.inst.service.spring

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.romanow.inst.model.events.PersonChangedEvent
import ru.romanow.inst.model.events.PersonCreatedEvent
import ru.romanow.inst.model.events.PersonEvent
import ru.romanow.inst.model.events.PersonRemovedEvent
import ru.romanow.inst.service.LogNotificationService
import java.util.stream.Stream

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [LogNotificationServiceTest.TestConfiguration::class])
//@SpringJUnitConfig(LogNotificationServiceTest.TestConfiguration::class)
internal class LogNotificationServiceTest {
    @Autowired
    private lateinit var logNotificationService: LogNotificationService

    @ParameterizedTest
    @MethodSource("factory")
    fun testNotify(event: PersonEvent) {
        logNotificationService.notify(event)
    }

    companion object {
        @JvmStatic
        fun factory(): Stream<PersonEvent> =
            Stream.of(PersonCreatedEvent(arrayOf()), PersonChangedEvent(arrayOf()), PersonRemovedEvent())
    }

    @Configuration
    internal class TestConfiguration {
        @Bean
        fun logNotificationService() = LogNotificationService()
    }
}
