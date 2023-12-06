package ru.romanow.inst.repository

import org.apache.commons.lang3.RandomUtils.nextInt
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import ru.romanow.inst.config.DatabaseTestConfiguration
import ru.romanow.inst.domain.Person
import java.util.stream.Stream

@DataJpaTest(properties = ["logging.level.org.testcontainers=debug"])
@ContextConfiguration(classes = [DatabaseTestConfiguration::class])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class PersonRepositoryTest {

    @Autowired
    private lateinit var personRepository: PersonRepository

    @ParameterizedTest
    @MethodSource("factory")
    fun findByNameTest(name: String) {
        // given
        factory().map {
            Person(
                name = it,
                age = nextInt(18, 60),
                work = "Moscow",
                address = "IT"
            )
        }
            .forEach { personRepository.save(it) }

        // when
        val person = personRepository.findByName(name)

        // than
        assertThat(person.isPresent).isTrue
        assertThat(person.get()).extracting("name").isEqualTo(name)
    }

    companion object {
        @JvmStatic
        fun factory(): Stream<String> = Stream.of("Alex", "Max", "Andrey")
    }
}
