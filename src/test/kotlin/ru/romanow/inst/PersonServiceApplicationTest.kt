package ru.romanow.inst

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.romanow.inst.config.DatabaseTestConfiguration
import ru.romanow.inst.web.PersonController

@SpringBootTest(classes = [DatabaseTestConfiguration::class])
internal class PersonServiceApplicationTest {

    @Autowired
    private lateinit var personController: PersonController

    @Test
    fun runApp() {
        assertThat(personController).isNotNull
    }
}
