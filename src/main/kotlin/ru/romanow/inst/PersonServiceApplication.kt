package ru.romanow.inst

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication as SpringBootApplication1

@SpringBootApplication1
class PersonServiceApplication

fun main(args: Array<String>) {
    SpringApplication.run(PersonServiceApplication::class.java, *args)
}
