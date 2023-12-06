package ru.romanow.inst.service.spring

import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito.mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import ru.romanow.inst.model.events.PersonChangedEvent
import ru.romanow.inst.model.events.PersonCreatedEvent
import ru.romanow.inst.model.events.PersonEvent
import ru.romanow.inst.model.events.PersonRemovedEvent
import ru.romanow.inst.service.EmailNotificationService
import java.util.stream.Stream
import java.util.stream.Stream.of

@SpringBootTest(
    properties = ["audit.user.mail=romanowalex@mail.ru"],
    classes = [EmailNotificationServiceTest.TestConfiguration::class]
)
internal class EmailNotificationServiceTest {

    @Autowired
    private lateinit var emailNotificationService: EmailNotificationService

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

    @Configuration
    class TestConfiguration {

        @Bean
        fun javaMailSender(): JavaMailSender = mock(JavaMailSender::class.java)

        @Bean
        fun emailNotificationService(@Value("\${audit.user.mail}") auditUserMail: String) =
            EmailNotificationService(javaMailSender(), auditUserMail)
    }
}
