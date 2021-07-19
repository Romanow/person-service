package ru.romanow.inst.service.simple

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import ru.romanow.inst.service.ReflectionUtils
import java.util.stream.Stream

internal class ReflectionUtilsTest {

    @ParameterizedTest
    @MethodSource("factory")
    fun test(testObject: TestObject, matchNulls: Boolean, results: Array<String>) {
        val fieldValues: Array<String> = ReflectionUtils.getFieldValues(testObject, matchNulls)
        assertThat(fieldValues).containsExactlyInAnyOrder(*results)
    }

    companion object {
        @JvmStatic
        fun factory(): Stream<Arguments> =
            Stream.of(
                Arguments.of(TestObject("Hello", 10), false, arrayOf("a -> Hello", "b -> 10")),
                Arguments.of(TestObject("Hello", 10), true, arrayOf("a -> Hello", "b -> 10", "c -> null")),
                Arguments.of(TestObject("Hello", 10, "Test"), false, arrayOf("a -> Hello", "b -> 10", "c -> Test")),
            )

    }

    data class TestObject(
        val a: String,
        val b: Int,
        val c: String? = null
    )
}